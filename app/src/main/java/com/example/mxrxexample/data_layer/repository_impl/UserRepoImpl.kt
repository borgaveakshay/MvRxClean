package com.example.mxrxexample.data_layer.repository_impl

import com.example.mxrxexample.data_layer.api.UserApi
import com.example.mxrxexample.data_layer.models.UserList
import com.example.mxrxexample.domain_layer.repository.UserRepository
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserRepoImpl : UserRepository, KoinComponent {

    private val mUserApi: UserApi by inject()

    override fun getUserList(pageNo: Int): Observable<UserList> {
        return mUserApi.getUserList(pageNo)
    }

}