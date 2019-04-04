package com.example.mxrxexample.presentation_layer.states

import com.airbnb.mvrx.MvRxState
import com.example.mxrxexample.data_layer.models.UserList

data class UserListState(val userList: UserList = UserList()) : MvRxState {

    val updateUserList = userList
}