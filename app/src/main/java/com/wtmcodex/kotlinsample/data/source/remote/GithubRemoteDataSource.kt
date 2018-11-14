package com.wtmcodex.kotlinsample.data.source.remote

import com.wtmcodex.kotlinsample.data.entities.GithubUserModel
import com.wtmcodex.kotlinsample.data.services.GithubService
import com.wtmcodex.kotlinsample.data.source.datasource.GithubDataSource
import com.wtmcodex.kotlinsample.entities.baseClass.GithubResponse
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import javax.inject.Inject
import javax.inject.Singleton

// this class is used to make request
@Singleton
class GithubRemoteDataSource @Inject constructor(@NonNull val mGithubService: GithubService) : GithubDataSource {

    override fun getGithubUsers(query: String, pageNumber: Int): Single<GithubResponse<GithubUserModel>> {
        return mGithubService.getGithubUsers(query, pageNumber)
    }
}