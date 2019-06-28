package com.presldn.empowerment.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private const val BASE_URL = "https://my-json-server.typicode.com/presldn/quotes/"

        fun loadData(): QuotesApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(QuotesApi::class.java)

        }
    }
}