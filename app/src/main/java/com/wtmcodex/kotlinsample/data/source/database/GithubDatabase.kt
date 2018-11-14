package com.wtmcodex.kotlinsample.data.source.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.wtmcodex.kotlinsample.data.entities.GithubUserModel

// the Room Database that contains all the table.
@Database(entities = arrayOf(GithubUserModel::class), version = 1)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun jobDao(): GithubDao
}
