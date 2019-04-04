package com.example.mxrxexample.domain_layer.repository

import com.example.mxrxexample.data_layer.models.UserList
import io.reactivex.Observable

interface UserRepository {

    fun getUserList(pageNo: Int): Observable<UserList>
}