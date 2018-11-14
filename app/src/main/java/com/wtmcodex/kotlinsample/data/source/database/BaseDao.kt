package com.wtmcodex.kotlinsample.data.source.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

@Dao
interface BaseDao<T> {

    /**
     * Insert a list in the database. If the task already exists, replace it.
     *
     * @param list the category to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertAll(List: List<T>)


}