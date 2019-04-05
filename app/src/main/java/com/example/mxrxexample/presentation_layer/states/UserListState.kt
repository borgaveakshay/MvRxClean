package com.example.mxrxexample.presentation_layer.states

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.Uninitialized
import com.example.mxrxexample.data_layer.models.UserList

data class UserListState(val userList: Async<UserList> = Uninitialized, val loading: Async<Boolean> = Uninitialized) :
    BaseState(loading)