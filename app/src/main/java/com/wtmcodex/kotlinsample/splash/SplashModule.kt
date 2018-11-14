package com.wtmcodex.kotlinsample.splash

import com.wtmcodex.kotlinsample.di.ActivityScoped
import com.wtmcodex.kotlinsample.di.FragmentScoped
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * This is a Dagger module. We use this to pass in the View dependency to the
 * [SplashPresenter].
 */
@Module
abstract class SplashModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun splashFragment(): SplashFragment

    @ActivityScoped
    @Binds
    abstract fun splashPresenter(presenter: SplashPresenter): SplashContract.Presenter
}
