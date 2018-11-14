package com.wtmcodex.kotlinsample.data.source.datasource

import com.wtmcodex.kotlinsample.data.entities.GithubUserModel
import com.wtmcodex.kotlinsample.entities.baseClass.GithubResponse
import io.reactivex.Single

// this is an interface to get GitHub users
interface GithubDataSource : DataSource {

    fun getGithubUsers(query: String, pageNumber: Int): Single<GithubResponse<GithubUserModel>>


}