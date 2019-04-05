package com.example.mxrxexample.presentation_layer.view.activities

import android.os.Bundle
import com.airbnb.mvrx.BaseMvRxActivity
import com.example.mxrxexample.R

class MainActivity : BaseMvRxActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
