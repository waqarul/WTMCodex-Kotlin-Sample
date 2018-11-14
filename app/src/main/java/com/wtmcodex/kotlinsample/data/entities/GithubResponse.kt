package com.wtmcodex.kotlinsample.entities.baseClass;

import com.google.gson.annotations.SerializedName


open class GithubResponse<T>{

    @SerializedName("total_count")
    var totalCount: Int = 0


    @SerializedName("items")
    var items: List<T>? = null


}