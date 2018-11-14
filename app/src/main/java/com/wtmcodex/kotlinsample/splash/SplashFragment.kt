package com.wtmcodex.kotlinsample.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.wtmcodex.kotlinsample.R
import com.wtmcodex.kotlinsample.base.RootFragment
import com.wtmcodex.kotlinsample.di.ActivityScoped
import com.wtmcodex.kotlinsample.home.HomeActivity
import javax.inject.Inject

@ActivityScoped
class SplashFragment @Inject constructor() : RootFragment<SplashContract.Presenter>(), SplashContract.View {

    // fields
    @Inject
    lateinit var mPresenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun getFragmentResourceId(): Int {
        return R.layout.frag_splash
    }

    override fun onFragmentInitializationComplete(view: View) {
        mPresenter.takeView(this)
    }

    override fun attachListeners() {
    }

    override fun setViewToPresenter() {
        mPresenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.dropView()  //prevent leaking activity in
    }


    // open home activity
    override fun openHomeActivity() {
        startIntent(Intent(activity, HomeActivity::class.java))
    }

    fun startIntent(intent: Intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        activity!!.finish()
    }

    override fun updateUI() {
    }
}