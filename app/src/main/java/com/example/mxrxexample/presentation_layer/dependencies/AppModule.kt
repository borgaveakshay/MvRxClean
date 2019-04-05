package com.example.mxrxexample.presentation_layer.dependencies

import com.example.mxrxexample.BuildConfig.BASE_URL
import com.example.mxrxexample.data_layer.api.UserApi
import com.example.mxrxexample.data_layer.repository_impl.UserRepoImpl
import com.example.mxrxexample.domain_layer.repository.UserRepository
import com.example.mxrxexample.domain_layer.use_cases.UserListUseCase
import com.example.mxrxexample.presentation_layer.utils.AsyncTransformer
import org.koin.dsl.module
import retrofit2.Retrofit

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {


    val retrofit = module {

        single<Retrofit> {

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        single<UserApi> {

            val retrofit = get<Retrofit>()
            retrofit.create(UserApi::class.java)
        }

    }

    val repositories = module {

        single<UserRepository> {
            UserRepoImpl()
        }
    }


    val useCases = module {

        single {

            UserListUseCase(AsyncTransformer())
        }
    }

}