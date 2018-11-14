package com.wtmcodex.kotlinsample.home

import com.wtmcodex.kotlinsample.base.BasePresenter
import com.wtmcodex.kotlinsample.base.BaseView
import com.wtmcodex.kotlinsample.data.entities.GithubUserModel


interface HomeContract {
    //usually this should only contain methods with prefixes like show , hide , animate, invoke and populate.
    //If you find yourself being lead to implement a method other than the prefixes above, you might be making a mistake.


    interface View : BaseView<Presenter> {
        fun populateGithubUsers(list: List<GithubUserModel>, clearPrevious: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun loadGithubUsers()
        fun makeGithubUserRequest(query: String, pageNumber: Int)
        fun onSearch(query: String)
    }
}