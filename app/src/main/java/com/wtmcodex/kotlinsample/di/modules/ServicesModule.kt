package com.wtmcodex.kotlinsample.di.modules

import com.wtmcodex.kotlinsample.data.services.GithubService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

// this class is used to provide service
@Module
class ServicesModule {

    @Provides
    @Singleton
    fun provideRemoteJobsService(retrofit: Retrofit): GithubService = retrofit.create(GithubService::class.java)
}