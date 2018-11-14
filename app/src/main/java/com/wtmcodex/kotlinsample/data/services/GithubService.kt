package com.wtmcodex.kotlinsample.data.services

import com.wtmcodex.kotlinsample.constants.APIConstants
import com.wtmcodex.kotlinsample.data.entities.GithubUserModel
import com.wtmcodex.kotlinsample.entities.baseClass.GithubResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

// this is service class for GitHub
interface GithubService {
    @GET(APIConstants.SEARCH_USERS)
    fun getGithubUsers(@Query("q") search: String, @Query("page") pageNumber: Int): Single<GithubResponse<GithubUserModel>>

}