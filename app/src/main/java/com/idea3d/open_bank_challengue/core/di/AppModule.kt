package com.idea3d.open_bank_challengue.core.di

import com.google.gson.GsonBuilder
import com.idea3d.open_bank_challengue.core.Constants.Companion.BASE_URL
import com.idea3d.open_bank_challengue.domain.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideWebService(retrofit:Retrofit) = retrofit.create(WebService::class.java)
}