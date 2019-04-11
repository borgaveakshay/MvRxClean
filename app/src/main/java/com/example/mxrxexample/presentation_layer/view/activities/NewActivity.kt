package com.example.mxrxexample.presentation_layer.view.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mxrxexample.R
import com.roscopeco.deelang.compiler.Compiler
import com.roscopeco.deelang.compiler.dex.DexCompilationUnit
import com.roscopeco.deelang.runtime.dex.CompiledScript
import com.roscopeco.deelang.runtime.dex.DexBinding
import dee.lang.*
import java.security.AccessController.getContext


class NewActivity : Activity() {
    private var codeText: EditText? = null
    private var activityOutput: TextView? = null
    private var compileButton: Button? = null
    private var runButton: Button? = null

    private var binding: Binding? = null

    private var compiledScript: Class<out CompiledScript>? = null

    /**
     * This is a basic Deelang-Java interface class. We'll set
     * this as the 'Activity' variable in our binding, so it
     * can be accessed from Deelang code.
     */
    inner class Activity(binding: Binding) : DeelangObject(binding) {

        var aString: DeelangString? = null

        fun setOutput(str: DeelangObject) {
            activityOutput!!.text = str.toString()
        }

        fun log(str: DeelangObject) {
            Log.i("<deelang>", str.toString())
        }

        fun moveToNextScreen(screenName: DeelangObject) {

            if (screenName.toString() == "MainActivity") {
                startActivity(Intent(this@NewActivity, MainActivity::class.java))
            }
            if (screenName.toString() == "NewActivity") {
                startActivity(Intent(this@NewActivity, NewActivity::class.java))
            }
        }

        fun blockTest(b: Block, i: DeelangInteger) {
            Log.d("ACTIVITY", "Integer is: " + i.integer)
            Log.d("ACTIVITY", "Will call block")
            b.invoke()
            Log.d("ACTIVITY", "Back from block")
        }
    }

    inner class Self(arg0: Binding) : DeelangObject(arg0) {

        fun timesTwo(i: DeelangInteger): DeelangInteger {
            return DeelangInteger(binding, i.integer * 2)
        }
    }

    private fun setupBinding() {
        val b = DexBinding()
        b.self = Self(b)
        b.setLocal("Activity", Activity(b))
        binding = b
    }

    private fun updateUiStates() {
        compileButton!!.setEnabled(compiledScript == null)
        runButton!!.setEnabled(compiledScript != null)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        System.setProperty("dexmaker.dexcache", cacheDir.path);
        codeText = findViewById(R.id.codeText) as EditText
        activityOutput = findViewById(R.id.activityOutput) as TextView
        compileButton = findViewById(R.id.compileButton) as Button
        runButton = findViewById(R.id.runButton) as Button

        setupBinding()
        updateUiStates()

        compileButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                try {
                    val c = Compiler()
                    val unit = c.compile(
                        DexCompilationUnit("<EditText.text>", binding!!),
                        codeText!!.text.toString()
                    )

                    compiledScript = unit.getScript()
                } catch (e: Throwable) {
                    activityOutput!!.text = "ERROR: " + e.message
                    Log.e("DL", "Exception while compiling", e)
                    compiledScript = null
                } finally {
                    updateUiStates()
                }
            }
        })

        runButton!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                try {
                    CompiledScript.newInstance(compiledScript, binding).run()
                } catch (e: Throwable) {
                    activityOutput!!.text = "ERROR: " + e.message
                    Log.e("DL", "Exception while running", e)
                    compiledScript = null
                } finally {
                    updateUiStates()
                }
            }
        })

        codeText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(arg0: Editable) {}
            override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
            override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {
                if (compiledScript != null) {
                    compiledScript = null
                    updateUiStates()
                }
            }
        })
    }
}