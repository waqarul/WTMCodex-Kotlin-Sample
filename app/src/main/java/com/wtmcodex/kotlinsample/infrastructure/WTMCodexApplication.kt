package com.wtmcodex.kotlinsample.infrastructure

import android.content.Context
import android.content.res.Configuration
import com.wtmcodex.kotlinsample.R
import com.wtmcodex.kotlinsample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

class WTMCodexApplication : DaggerApplication() {
    // fields
    @Inject
    lateinit var mLanguageManager: LanguageManager

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build();
    }

    override fun onCreate() {
        super.onCreate()
        initializeApplication()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mLanguageManager.initialize(baseContext)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        mLanguageManager = LanguageManager(this)
    }

    fun initializeApplication() {
        setDefaultFont();
    }

    // initialize default fonts for application
    fun setDefaultFont() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }


}