package com.wtmcodex.kotlinsample.di.modules

import com.wtmcodex.kotlinsample.di.ActivityScoped
import com.wtmcodex.kotlinsample.home.HomeActivity
import com.wtmcodex.kotlinsample.home.HomeModule
import com.wtmcodex.kotlinsample.splash.SplashActivity
import com.wtmcodex.kotlinsample.splash.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(SplashModule::class))
    abstract fun splashActivity(): SplashActivity


    @ActivityScoped
    @ContributesAndroidInjector(modules = arrayOf(HomeModule::class))
    abstract fun homeActivity(): HomeActivity


}
