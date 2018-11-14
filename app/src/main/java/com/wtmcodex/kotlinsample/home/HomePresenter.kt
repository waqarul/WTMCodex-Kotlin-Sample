package com.wtmcodex.kotlinsample.home

import android.content.Context
import com.wtmcodex.kotlinsample.base.RootPresenter
import com.wtmcodex.kotlinsample.data.entities.GithubUserModel
import com.wtmcodex.kotlinsample.data.network.ErrorMessage
import com.wtmcodex.kotlinsample.data.network.Message
import com.wtmcodex.kotlinsample.data.source.repository.GithubRepository
import com.wtmcodex.kotlinsample.entities.baseClass.GithubResponse
import com.wtmcodex.kotlinsample.managers.SchedulerProviderManager
import com.wtmcodex.kotlinsample.util.Utils
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.HttpException
import java.util.*
import javax.inject.Inject

class HomePresenter @Inject constructor(@NonNull val mGithubRepository: GithubRepository, @NonNull val mContext: Context) : RootPresenter(), HomeContract.Presenter {

    // fields
    private var mFragmentView: HomeContract.View? = null

    var mCurrentPage = 1
    private var mClearPreviousList = false
    var mQuery: String = ""

    override fun start() {

    }

    // calling this method from view to set fragment
    override fun takeView(view: HomeContract.View) {
        mFragmentView = view
        start()
    }

    // calling this method from view from onDestroy to prevent memory leak
    override fun dropView() {
        mFragmentView = null

        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
    }


    override fun loadGithubUsers() {
        loadGithubUsers(mQuery)
    }

    // this method add current page and request for pai
    fun loadGithubUsers(query: String) {
        mCurrentPage++;
        makeGithubUserRequest(query, mCurrentPage)
        mClearPreviousList = false;
    }

    // make request to fetch user with query
    override fun makeGithubUserRequest(query: String, pageNumber: Int) {
        mQuery = query

        // check for internet connection
        if (!Utils.isNetworkAvailable(mContext)) {
            mFragmentView!!.showErrors(Arrays.asList(Message(ErrorMessage.NO_CONNECTION_ERROR)));
            return
        }

        mFragmentView!!.showProgress(true, true)
        mCompositeDisposable.add(mGithubRepository.getGithubUsers(query, pageNumber)
                .observeOn(SchedulerProviderManager.getObserverMainThread())
                .subscribeOn(SchedulerProviderManager.getSubscribeNewThread())
                .subscribeWith(object : DisposableSingleObserver<GithubResponse<GithubUserModel>>() {
                    override fun onSuccess(users: GithubResponse<GithubUserModel>) {
                        mFragmentView!!.showProgress(false, false)
                        mFragmentView!!.populateGithubUsers(users.items!!, mClearPreviousList)

                        // check if there is any user
                        if (users.items != null && users.items!!.size == 0 && mCurrentPage < 2) {
                            mFragmentView!!.showErrors(Arrays.asList(Message(ErrorMessage.NO_GITHUB_USER_FOUND)));
                        }

                    }

                    override fun onError(e: Throwable) {
                        mFragmentView!!.showProgress(false, false)
                        val code: Int = (e as HttpException).code()
                        if (code == 422) { // nothing entered
                            mFragmentView!!.showErrors(Arrays.asList(Message(ErrorMessage.TYPE_FOR_SEARCH)));
                        } else if (code == 403) { // limit exceeded
                            mFragmentView!!.showErrors(Arrays.asList(Message(ErrorMessage.TYPE_FORBIDDEN)));
                        } else { // default error
                            mFragmentView!!.showErrors(Arrays.asList(Message(ErrorMessage.CANNOT_REACH_SERVER)));
                        }
                    }
                }))
    }

    // this method will be invoked when we search anything
    override fun onSearch(query: String) {
        mCurrentPage = 1
        mClearPreviousList = true;
        makeGithubUserRequest(query, mCurrentPage)
    }
}