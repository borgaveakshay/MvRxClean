package com.example.mxrxexample.data_layer.api

import com.example.mxrxexample.data_layer.models.UserList
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/users")
    fun getUserList(@Query("page") pageNo: Int): Observable<UserList>
}