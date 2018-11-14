package com.wtmcodex.kotlinsample.data.network

import com.google.gson.annotations.SerializedName


class EntityError {
    @SerializedName("ErrorMessage")
    var errorMessage: String? = null
}
