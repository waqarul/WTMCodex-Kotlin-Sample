package com.wtmcodex.kotlinsample.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.wtmcodex.kotlinsample.R
import com.wtmcodex.kotlinsample.base.ToolbarActivity
import com.wtmcodex.kotlinsample.util.ActivityUtils
import dagger.Lazy
import javax.inject.Inject

class HomeActivity : ToolbarActivity() {

    // fields
    @Inject
    lateinit var mHomeFragmentProvider: Lazy<HomeFragment>
    @Inject
    lateinit var mHomePresenter: HomeContract.Presenter


    override fun onScreenInitializationComplete(activityArgs: Intent, savedInstanceState: Bundle?) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun getMenuResourceId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addFragment() {
        var homeFragment: HomeFragment? = (supportFragmentManager.findFragmentById(R.id.content_frame) as? HomeFragment)
        if (homeFragment == null) {
            // Get the fragment from dagger
            homeFragment = mHomeFragmentProvider.get()
            ActivityUtils.addFragmentToActivity(
                    supportFragmentManager, homeFragment, R.id.content_frame)
        }
    }


    override fun onSearch(query: String) {
        Log.e("onSearch", query)

        mHomePresenter.onSearch(query)

    }


    // when application recretaed this method is called
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val operation = intent.getStringExtra(EXTRA_OPERATION)
        if (operation == EXTRA_CHANGE_LANGUAGE) {
            changeLanguage(intent.getStringExtra(EXTRA_CHANGE_LANGUAGE_VALUE))
        }
    }

    // change device language programmatically
    private fun changeLanguage(language: String) {

        //toggle current language and set it in preferences
        mLanguageManager.setLocale(language)
        // fresh new news and media for user when language changes
        restartActivity()
    }

    private fun restartActivity() {
        //restart activity
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }


}