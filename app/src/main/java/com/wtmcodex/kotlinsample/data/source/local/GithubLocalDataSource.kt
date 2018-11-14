package com.wtmcodex.kotlinsample.data.source.local.datasource.datasource.local

import android.support.annotation.NonNull
import com.wtmcodex.kotlinsample.data.entities.GithubUserModel
import com.wtmcodex.kotlinsample.data.source.database.GithubDao
import com.wtmcodex.kotlinsample.data.source.datasource.GithubDataSource
import com.wtmcodex.kotlinsample.data.source.local.LocalDataSource
import com.wtmcodex.kotlinsample.entities.baseClass.GithubResponse
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

// this is used to store data
@Singleton
class GithubLocalDataSource @Inject constructor(@NonNull val mJobDao: GithubDao) : LocalDataSource(), GithubDataSource {

    override fun getGithubUsers(query: String, pageNumber: Int): Single<GithubResponse<GithubUserModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}