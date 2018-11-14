package com.wtmcodex.kotlinsample.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.support.annotation.NonNull
import com.wtmcodex.kotlinsample.constants.AppConstants
import com.wtmcodex.kotlinsample.data.source.database.GithubContract
import com.wtmcodex.kotlinsample.data.source.database.GithubDao
import com.wtmcodex.kotlinsample.data.source.database.GithubDatabase
import com.wtmcodex.kotlinsample.infrastructure.LanguageManager
import com.wtmcodex.kotlinsample.util.AppExecutors
import com.wtmcodex.kotlinsample.util.DiskIOThreadExecutor
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

// this class is used to create database, provide database dao and language manager instances
@Module
class DatabaseModule {

    @Singleton
    @Provides
    @NonNull
    fun provideDb(app: Application): GithubDatabase {
        return Room.databaseBuilder(app, GithubDatabase::class.java, GithubContract.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    @NonNull
    fun provideJobDao(db: GithubDatabase): GithubDao {
        return db.jobDao()
    }

    @Singleton
    @Provides
    @NonNull
    fun provideAppExecutors(): AppExecutors {
        return AppExecutors(DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(AppConstants.THREAD_COUNT),
                AppExecutors.MainThreadExecutor())
    }

    @Provides
    @Singleton
    fun provideLanguageManager(context: Context): LanguageManager {
        return LanguageManager(context)
    }
}