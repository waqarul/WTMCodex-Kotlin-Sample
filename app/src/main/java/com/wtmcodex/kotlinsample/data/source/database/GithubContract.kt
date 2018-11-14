package com.wtmcodex.kotlinsample.data.source.database

// this class is used for database
object GithubContract {

    val DATABASE_NAME = "GithubDatabase.db"

    /**
     * Entity job info & queries
     */
    const val TABLE_GITHUB_USERS = "GithubUsers"
    const val QUERY_SELECT_GITHUB_USERS = "SELECT * FROM " + TABLE_GITHUB_USERS


}