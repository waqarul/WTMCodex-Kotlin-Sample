package com.wtmcodex.kotlinsample.di.modules

import com.wtmcodex.kotlinsample.data.Local
import com.wtmcodex.kotlinsample.data.Remote
import com.wtmcodex.kotlinsample.data.services.GithubService
import com.wtmcodex.kotlinsample.data.source.database.GithubDao
import com.wtmcodex.kotlinsample.data.source.datasource.GithubDataSource
import com.wtmcodex.kotlinsample.data.source.local.datasource.datasource.local.GithubLocalDataSource
import com.wtmcodex.kotlinsample.data.source.remote.GithubRemoteDataSource
import dagger.Module
import dagger.Provides
import io.reactivex.annotations.NonNull
import javax.inject.Singleton

// this class is used to provide github local/remote data sources
@Module
class GithubRepositoryModule {


    @Singleton
    @Provides
    @Local
    @NonNull
    fun provideGithubLocalDataSource(dao: GithubDao): GithubDataSource = GithubLocalDataSource(dao)

    @Singleton
    @Provides
    @Remote
    @NonNull
    fun provideGithubRemoteDataSource(jobServices: GithubService): GithubDataSource = GithubRemoteDataSource(jobServices)


}