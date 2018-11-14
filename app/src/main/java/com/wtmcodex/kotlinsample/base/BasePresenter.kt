package com.wtmcodex.kotlinsample.base


interface BasePresenter<T> {

    fun start()

    /**
     * Binds presenter with a view when resumed. The Presenter will perform initialization here.
     *
     * @param view the view associated with this presenter
     */
    abstract fun takeView(view: T)

    /**
     * Drops the reference to the view when destroyed
     */
    abstract fun dropView()

}
