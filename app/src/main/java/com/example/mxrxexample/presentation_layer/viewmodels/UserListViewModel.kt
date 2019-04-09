package com.example.mxrxexample.presentation_layer.viewmodels

import android.annotation.SuppressLint
import com.example.mxrxexample.domain_layer.use_cases.UserListUseCase
import com.example.mxrxexample.presentation_layer.states.UserListState
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserListViewModel(initialState: UserListState) : BaseViewModel<UserListState>(initialState), KoinComponent {

    private val mUserListUserCase: UserListUseCase by inject()

    @SuppressLint("CheckResult")
    fun getUserList(pageNo: Int) {
        this.mUserListUserCase.observable(pageNo).execute { userList ->
            copy(userList = userList)
        }
    }

}