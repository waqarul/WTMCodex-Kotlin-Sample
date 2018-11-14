package com.wtmcodex.kotlinsample.splash

import com.wtmcodex.kotlinsample.base.RootPresenter
import com.wtmcodex.kotlinsample.di.ActivityScoped
import javax.inject.Inject

/**
 * Listens to user actions from the UI ({@link TasksFragment}), retrieves the data and updates the
 * UI as required.
 * <p/>
 * By marking the constructor with {@code @Inject}, Dagger injects the dependencies required to
 * create an instance of the TasksPresenter (if it fails, it emits a compiler error).  It uses
 * {@link TasksModule} to do so.
 * <p/>
 * Dagger generated code doesn't require public access to the constructor or class, and
 * therefore, to ensure the developer doesn't instantiate the class manually and bypasses Dagger,
 * it's good practice minimise the visibility of the class/constructor as much as possible.
 **/
@ActivityScoped
class SplashPresenter @Inject constructor() : RootPresenter(), SplashContract.Presenter {

    // fields
    private var mFragmentView: SplashContract.View? = null


    override fun start() {
        loadView()
    }

    override fun takeView(view: SplashContract.View) {
        mFragmentView = view
        start()
    }

    override fun dropView() {
        mFragmentView = null

        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
    }

    // open home activity after 2 sec
    override fun loadView() {

        val handler = android.os.Handler()
        handler.postDelayed({
            mFragmentView!!.openHomeActivity()
        }, 2000)
    }


}