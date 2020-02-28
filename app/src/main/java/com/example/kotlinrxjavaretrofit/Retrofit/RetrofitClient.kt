package com.example.kotlinrxjavaretrofit.Retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit?=null
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    val instance:Retrofit
        get() {
        if (retrofit == null)
        {
            retrofit = Retrofit.Builder()

                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
        return retrofit!!
    }
}