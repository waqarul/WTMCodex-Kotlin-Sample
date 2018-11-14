package com.wtmcodex.kotlinsample.base

import io.reactivex.disposables.CompositeDisposable


open class RootPresenter {

    // it will hold the references of observables
    protected var mCompositeDisposable = CompositeDisposable()

}
