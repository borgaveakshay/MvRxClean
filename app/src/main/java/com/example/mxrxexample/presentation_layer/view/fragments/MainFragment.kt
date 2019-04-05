package com.example.mxrxexample.presentation_layer.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.airbnb.mvrx.*
import com.example.mxrxexample.R
import com.example.mxrxexample.presentation_layer.states.UserListState
import com.example.mxrxexample.presentation_layer.viewmodels.UserListViewModel

class MainFragment : BaseFragment<UserListState, UserListViewModel>() {


    private val mUserListViewModel: UserListViewModel by fragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_main, container, false)

        return mView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mUserListViewModel.getUserList(2)
    }


    override fun invalidate() {

        withState(mUserListViewModel) { state ->

            when (state.userList) {
                is Uninitialized -> showProgressBar()

                is Loading -> showProgressBar()

                is Success -> {
                    hideProgressBar()
                    Toast.makeText(context, "Value Received: ${state.userList()?.page}", Toast.LENGTH_LONG).show()
                }
                is Fail -> {
                    hideProgressBar()
                    Toast.makeText(context, "API failed to execute", Toast.LENGTH_LONG).show()
                }

            }
        }
    }


}
