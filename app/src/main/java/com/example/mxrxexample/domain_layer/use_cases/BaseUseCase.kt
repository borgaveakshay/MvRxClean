package com.example.mxrxexample.domain_layer.use_cases

import com.example.mxrxexample.domain_layer.utils.Transformer
import io.reactivex.Observable


abstract class BaseUseCase<RES, REQ>(private val transformer: Transformer<RES>) {


    abstract fun createObservable(req: REQ?): Observable<RES>

    fun observable(req: REQ?): Observable<RES> {

        return createObservable(req).compose(transformer)
    }
}