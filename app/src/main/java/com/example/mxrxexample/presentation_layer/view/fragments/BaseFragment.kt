package com.example.mxrxexample.presentation_layer.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.BaseMvRxViewModel
import com.example.mxrxexample.R
import com.example.mxrxexample.presentation_layer.states.BaseState

abstract class BaseFragment<S : BaseState, V : BaseMvRxViewModel<S>> : BaseMvRxFragment() {

    protected lateinit var mView: View
    private lateinit var mProgressBar: ProgressBar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mProgressBar = mView.findViewById(R.id.progressbar)
    }

    fun hideProgressBar() {
        mProgressBar.visibility = View.GONE
    }

    fun showProgressBar() {
        mProgressBar.visibility = View.VISIBLE
    }


}