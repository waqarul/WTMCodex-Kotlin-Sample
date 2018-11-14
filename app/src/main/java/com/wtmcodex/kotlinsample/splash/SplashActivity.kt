package com.wtmcodex.kotlinsample.splash

import android.content.Intent
import android.os.Bundle
import com.wtmcodex.kotlinsample.R
import com.wtmcodex.kotlinsample.di.ActivityScoped
import com.wtmcodex.kotlinsample.base.BaseActivity
import com.wtmcodex.kotlinsample.util.ActivityUtils
import dagger.Lazy
import javax.inject.Inject


@ActivityScoped
class SplashActivity : BaseActivity() {

    @Inject
    lateinit var mSplashFragmentProvider: Lazy<SplashFragment>


    override fun getLayoutResourceId(): Int {
        return R.layout.activity
    }

    override fun onScreenInitializationComplete(activityArgs: Intent, savedInstanceState: Bundle?) {

    }

    override fun addFragment() {
        var splashFragment: SplashFragment? = (supportFragmentManager.findFragmentById(R.id.content_frame) as? SplashFragment)
        if (splashFragment == null) {
            // Get the fragment from dagger
            splashFragment = mSplashFragmentProvider.get()
            ActivityUtils.addFragmentToActivity(
                    supportFragmentManager, splashFragment, R.id.content_frame)
        }
    }

    override fun getMenuResourceId(): Int {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return 0;
    }

    override fun setListeners() {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}