package com.wtmcodex.kotlinsample.di.modules

import dagger.Module

// this class is used to include repository modules
@Module(includes = arrayOf(GithubRepositoryModule::class))
abstract class RepositoryManagerModule {

}