package com.example.mxrxexample.domain_layer.use_cases

import com.example.mxrxexample.data_layer.models.UserList
import com.example.mxrxexample.domain_layer.repository.UserRepository
import com.example.mxrxexample.domain_layer.utils.Transformer
import io.reactivex.Observable
import org.koin.core.KoinComponent
import org.koin.core.inject

class UserListUseCase(transformer: Transformer<UserList>) : BaseUseCase<UserList, Int>(transformer), KoinComponent {

    private val mUserRepository: UserRepository by inject()

    override fun createObservable(req: Int?): Observable<UserList> {
        return mUserRepository.getUserList(req!!)
    }
}