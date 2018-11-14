package com.wtmcodex.kotlinsample.base

import com.wtmcodex.kotlinsample.data.network.Message


interface BaseView<T> {

    /**
     * This function is used to set fragment to presenter
     */
    fun setViewToPresenter()

    /**
     * Displays the circular progress bar , additionally you should give the option to lock the screen
     * @param show
     * @param lockScreen
     */
    fun showProgress(show: Boolean, lockScreen: Boolean)

    // show error
    fun showErrors(messages: List<Message>)

}
