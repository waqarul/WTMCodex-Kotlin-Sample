package com.wtmcodex.kotlinsample.home

import com.wtmcodex.kotlinsample.di.ActivityScoped
import com.wtmcodex.kotlinsample.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ActivityScoped
    @Binds
    abstract fun homePresenter(presenter: HomePresenter): HomeContract.Presenter
}
