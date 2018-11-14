package com.wtmcodex.kotlinsample.home

import android.content.Context
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.wtmcodex.kotlinsample.R
import com.wtmcodex.kotlinsample.adapters.GithubUserAdapter
import com.wtmcodex.kotlinsample.base.RootFragment
import com.wtmcodex.kotlinsample.data.entities.GithubUserModel
import com.wtmcodex.kotlinsample.data.network.ErrorMessage
import com.wtmcodex.kotlinsample.data.network.Message
import com.wtmcodex.kotlinsample.di.ActivityScoped
import kotlinx.android.synthetic.main.frag_home.*
import java.util.*
import javax.inject.Inject


@ActivityScoped
class HomeFragment @Inject constructor() : RootFragment<HomeContract.Presenter>(), HomeContract.View {

    // fields
    var mGithubUserAdapter: GithubUserAdapter? = null
    private var mGithubUsersList: MutableList<GithubUserModel>? = null

    @Inject
    lateinit var mPresenter: HomeContract.Presenter

    override fun getFragmentResourceId(): Int {
        return R.layout.frag_home
    }

    override fun onFragmentInitializationComplete(view: View) {

        // initialize recycler view
        initializeRv()

        // add initial layout as we are not making any initial request
        showErrors(Arrays.asList(Message(ErrorMessage.TYPE_FOR_SEARCH)));

    }

    fun initializeRv(){
        users_rv.setHasFixedSize(true);
        var mLayoutManager = LinearLayoutManager(activity);
        users_rv.setLayoutManager(mLayoutManager)
        users_rv.addItemDecoration(ItemOffsetDecoration(activity!!, R.dimen.margin_20))
    }

    // populate github user with data
    override fun populateGithubUsers(list: List<GithubUserModel>, clearPrevious: Boolean) {
        if (mGithubUserAdapter == null || clearPrevious) {
            hideErrorIfDisplayed();
            loadDataInList(list);
        } else {
            updateDataInList(list);
        }
    }

    // update recycler view
    fun updateDataInList(list: List<GithubUserModel>) {
        mGithubUsersList!!.removeAt(mGithubUsersList!!.size - 1);
        if (list.size > 0)
            mGithubUsersList!!.addAll(list);
        else {
            mGithubUserAdapter!!.setMoreDataAvailable(false);
        }
        mGithubUserAdapter!!.notifyDataChanged()

    }

    // This method would only invoke for the first time the list is populated.
    fun loadDataInList(list: List<GithubUserModel>) {

        mGithubUsersList = list.toMutableList()
        mGithubUserAdapter = GithubUserAdapter(mGithubUsersList, mPresenter);
        mGithubUserAdapter!!.setLoadMoreListener(object : GithubUserAdapter.OnLoadMoreListener {
            override fun onLoadMore() {
                users_rv.post(Runnable {
                    val index = list.size - 1
                    loadMore()
                })
            }
        })
        users_rv.setAdapter(mGithubUserAdapter);
    }

    // this methos will load more data when recylerview reaches at the end
    private fun loadMore() {
        //add loading progress view
        val dummyEst = GithubUserModel(-1)
        dummyEst.isLoadRow = true
        mGithubUsersList!!.add(dummyEst)
        mGithubUserAdapter!!.notifyItemInserted(mGithubUsersList!!.size - 1)

        //request more data
        mPresenter.loadGithubUsers()

    }

    override fun updateUI() {
    }

    override fun attachListeners() {
    }

    override fun setViewToPresenter() {
        mPresenter.takeView(this)
    }

    override fun onDestroy() {
        mPresenter.dropView()  //prevent leaking activity in
        super.onDestroy()
    }

    // this is used as a decorator for recycler item
    inner class ItemOffsetDecoration(private val mItemOffset: Int) : RecyclerView.ItemDecoration() {

        constructor(context: Context, @DimenRes itemOffsetId: Int) : this(context.resources.getDimensionPixelSize(itemOffsetId)) {}

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                    state: RecyclerView.State?) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.set(0, 0, 0, mItemOffset)
        }
    }

}