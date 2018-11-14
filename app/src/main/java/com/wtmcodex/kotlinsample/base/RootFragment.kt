package com.wtmcodex.kotlinsample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.wtmcodex.kotlinsample.R
import com.wtmcodex.kotlinsample.data.network.ErrorMessage
import com.wtmcodex.kotlinsample.data.network.Message
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.frag_home.*


abstract class RootFragment<T> : DaggerFragment(), BaseView<T> {

    // Fields

    val TAG: String = RootFragment::class.java.simpleName

    // will hold error
    var mErrorLayout: LinearLayout? = null

    // hold the refernece of current view
    lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getFragmentResourceId(), container, false)

        mView = view

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewToPresenter()
        onFragmentInitializationComplete(mView)
        updateUI()
        attachListeners()
    }

    // show progress bar
    override fun showProgress(show: Boolean, lockScreen: Boolean) {
        val progressBar = activity!!.findViewById(R.id.progress_bar) as ProgressBar

        if (progressBar != null) {
            if (show) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    // show error
    override fun showErrors(messages: List<Message>) {
        //first check if the list is or not
        if (messages != null && messages.size > 0) {
            for (message in messages) {

                if (message.code != null) {
                    handleErrorMessage(message)
                }

            }
        }
    }

    // this method will handle error message
    /**
     * @param message contain error messafe type
     */
    fun handleErrorMessage(message: Message) {

        var errorMsg = getString(R.string.error_internal_server)
        if (message.code!!.equals(ErrorMessage.TYPE_FOR_SEARCH)) {
            errorMsg = getString(R.string.type_for_search)
        } else if (message.code!!.equals(ErrorMessage.NO_GITHUB_USER_FOUND)) {
            errorMsg = getString(R.string.no_results_for_search)
        } else if (message.code!!.equals(ErrorMessage.TYPE_FORBIDDEN)) {
            errorMsg = getString(R.string.type_forbidden)
        } else if (message.code!!.equals(ErrorMessage.NO_CONNECTION_ERROR)) {
            errorMsg = getString(R.string.no_internet_connection_found)
        } else if (message.code!!.equals(ErrorMessage.CANNOT_REACH_SERVER)) {
            errorMsg = getString(R.string.error_internal_server)
        }

        if (mErrorLayout != null) {
            showErrorLayout(errorMsg)
        } else {
            addErrorLayout()
            showErrorLayout(errorMsg)
        }
    }

    // this method will hide error layout
    protected fun hideErrorIfDisplayed() {
        if (mErrorLayout != null && mErrorLayout!!.getVisibility() === View.VISIBLE) {
            mErrorLayout!!.setVisibility(View.GONE)
            users_rv.setVisibility(View.VISIBLE)
            users_rv.animate().alpha(1f).setInterpolator(AccelerateInterpolator(2f))
        }
    }

    // this method will add error layout
    private fun addErrorLayout() {
        mErrorLayout = LayoutInflater.from(context).inflate(R.layout.view_error_layout, null, false) as LinearLayout
        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        params.addRule(RelativeLayout.CENTER_IN_PARENT)
        mErrorLayout!!.setLayoutParams(params)
        root_layout.addView(mErrorLayout)
    }

    // this method will show error layout
    private fun showErrorLayout(errorMessage: String) {
        users_rv.animate().alpha(0f).setInterpolator(AccelerateInterpolator(2f)).withEndAction(Runnable { users_rv.setVisibility(View.GONE) })
        val errorTextView = mErrorLayout!!.findViewById(R.id.error_desc) as TextView
        errorTextView.setText(errorMessage)
        mErrorLayout!!.setVisibility(View.VISIBLE)
        mErrorLayout!!.setAlpha(0f)
        mErrorLayout!!.animate().alpha(1f).interpolator = DecelerateInterpolator(2f)
    }


    //region Abstract Methods
    // get the fragment resource id
    abstract fun getFragmentResourceId(): Int

    // this method will be invoked from @onCreateView
    abstract fun onFragmentInitializationComplete(view: View)

    // this method is used to update ui
    abstract fun updateUI()

    // this method is used to attach listeners
    abstract fun attachListeners()

    //endregion
}

