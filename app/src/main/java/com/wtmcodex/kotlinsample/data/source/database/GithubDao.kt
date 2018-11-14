package com.wtmcodex.kotlinsample.data.source.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.wtmcodex.kotlinsample.data.entities.GithubUserModel
import io.reactivex.Single


@Dao
interface GithubDao : BaseDao<GithubUserModel> {

    /**
     * Select all users from the users table.
     *
     * @return all users.
     */
    @Query(GithubContract.QUERY_SELECT_GITHUB_USERS)
    abstract fun getAllGithubUsers(): Single<List<GithubUserModel>>

}