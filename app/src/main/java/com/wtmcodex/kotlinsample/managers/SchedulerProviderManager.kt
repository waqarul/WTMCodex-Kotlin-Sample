package com.wtmcodex.kotlinsample.managers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulerProviderManager {

    fun getSubscribeNewThread(): Scheduler = Schedulers.newThread()

    fun getObserverMainThread(): Scheduler = AndroidSchedulers.mainThread()


}