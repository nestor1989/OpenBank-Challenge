package com.idea3d.open_bank_challengue.core.vo

import com.google.gson.GsonBuilder
import com.idea3d.open_bank_challengue.core.Constants.Companion.BASE_URL
import com.idea3d.open_bank_challengue.domain.WebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val webService by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)

    }

}