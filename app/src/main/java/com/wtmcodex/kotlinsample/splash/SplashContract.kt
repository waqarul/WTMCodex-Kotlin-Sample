package com.wtmcodex.kotlinsample.splash

import com.wtmcodex.kotlinsample.base.BasePresenter
import com.wtmcodex.kotlinsample.base.BaseView


interface SplashContract {
    //usually this should only contain methods with prefixes like show , hide , animate, invoke and populate.
    //If you find yourself being lead to implement a method other than the prefixes above, you might be making a mistake.

    interface View : BaseView<Presenter> {
        fun openHomeActivity()
    }

    interface Presenter : BasePresenter<View> {

        fun loadView()
    }
}