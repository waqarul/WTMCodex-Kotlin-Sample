package com.wtmcodex.kotlinsample.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.wtmcodex.kotlinsample.infrastructure.LanguageManager
import dagger.android.support.DaggerAppCompatActivity
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject


open abstract class BaseActivity : DaggerAppCompatActivity() {

    // fields
    @Inject
    lateinit var mLanguageManager: LanguageManager

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(context))

        mLanguageManager = LanguageManager(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set layout for view
        setContentView(getLayoutResourceId())

        // initialize screen
        onScreenInitializationComplete(intent, savedInstanceState)

        // setting listeneres
        setListeners()

        // add fragment
        addFragment()

    }


    //region Abstracts
    //the four operations that would take place in every activity.
    protected abstract fun getLayoutResourceId(): Int

    // Use this to restore the state of the activity. We could have also used restoreInstanceState .
    protected abstract fun onScreenInitializationComplete(activityArgs: Intent, savedInstanceState: Bundle?)

    // set listeners
    protected abstract fun setListeners()

    // get menu layout if any
    protected abstract fun getMenuResourceId(): Int

    // this method is used to add fragment
    protected abstract fun addFragment()

    //endregion Abstracts
    //region unused
}