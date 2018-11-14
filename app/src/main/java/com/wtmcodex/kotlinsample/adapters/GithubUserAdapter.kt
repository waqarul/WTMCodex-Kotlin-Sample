package com.wtmcodex.kotlinsample.adapters


import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.common.base.Strings
import com.wtmcodex.kotlinsample.R
import com.wtmcodex.kotlinsample.data.entities.GithubUserModel
import com.wtmcodex.kotlinsample.home.HomeContract
import de.hdodenhof.circleimageview.CircleImageView

// Provide a suitable constructor (depends on the kind of dataset)
class GithubUserAdapter
(private val mDataset: List<GithubUserModel>?, internal var presenter: HomeContract.Presenter) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    internal var isLoading = false
    internal var isMoreDataAvailable = true
    internal var loadMoreListener: OnLoadMoreListener? = null

    val TYPE_ITEM = 0
    val TYPE_LOAD = 1

    open interface OnLoadMoreListener {
        fun onLoadMore()
    }

    fun setLoadMoreListener(loadMoreListener: OnLoadMoreListener) {
        this.loadMoreListener = loadMoreListener
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {


        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_ITEM) {
            GithubUserViewHolder(inflater.inflate(R.layout.user_item, parent, false) as CardView)
        } else {
            LoadHolder(inflater.inflate(R.layout.load_item, parent, false))
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position >= itemCount - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {
            isLoading = true
            loadMoreListener!!.onLoadMore()
        }

        if (getItemViewType(position) == TYPE_ITEM) {
            (holder as GithubUserViewHolder).bind(mDataset!![position])
        }
        //No else part needed as load holder doesn't bind any data

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataset?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (!mDataset!![position].isLoadRow) {
            TYPE_ITEM
        } else {
            TYPE_LOAD
        }
    }

    /* notifyDataSetChanged is final method so we can't override it
      call adapter.notifyDataChanged(); after update the list
      */
    fun notifyDataChanged() {
        notifyDataSetChanged()
        isLoading = false
    }

    fun setMoreDataAvailable(moreDataAvailable: Boolean) {
        isMoreDataAvailable = moreDataAvailable
    }


    open class LoadHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    internal inner class GithubUserViewHolder(val rowRootView: CardView) : RecyclerView.ViewHolder(rowRootView) {

        fun bind(user: GithubUserModel) {
            val title: TextView = rowRootView.findViewById(R.id.title)
            title.setText(user.login)

            val profile: TextView = rowRootView.findViewById(R.id.profile)
            profile.setText(user.htmlUrl)

            val score: TextView = rowRootView.findViewById(R.id.score)
            score.setText("" + user.score)

            val avatar: CircleImageView = rowRootView.findViewById(R.id.image)

            // check if avatar is null/empty show placeholder
            if (!Strings.isNullOrEmpty(user.avatarUrl)) {
                Glide.with(avatar.context)
                        .load(user.avatarUrl)
                        .into(avatar)
            } else {
                avatar.setImageResource(R.drawable.img_placeholder)
            }
        }

    }


}
