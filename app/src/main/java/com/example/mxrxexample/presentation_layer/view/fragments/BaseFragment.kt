package com.example.mxrxexample.presentation_layer.view.fragments

import android.view.View
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.BaseMvRxViewModel
import com.example.mxrxexample.presentation_layer.states.BaseState
import kotlinx.android.synthetic.main.progress_dailog.*

abstract class BaseFragment<S : BaseState, V : BaseMvRxViewModel<S>> : BaseMvRxFragment() {

    protected lateinit var mView: View

    fun hideProgressBar() = {
        progressbar.visibility = View.INVISIBLE
    }

    fun showProgressBar() = {

        progressbar.visibility = View.VISIBLE
    }


}