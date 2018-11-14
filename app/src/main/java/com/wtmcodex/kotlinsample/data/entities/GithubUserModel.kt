package com.wtmcodex.kotlinsample.data.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName
import com.wtmcodex.kotlinsample.data.source.database.GithubContract

@Entity(tableName = GithubContract.TABLE_GITHUB_USERS)
data class GithubUserModel(@PrimaryKey
                           @NonNull
                           @ColumnInfo(name = "id")
                           @SerializedName("id")
                           var userId: Int) : Parcelable {


    @ColumnInfo(name = "login")
    @SerializedName("login")
    var login: String? = null

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    var avatarUrl: String? = null

    @ColumnInfo(name = "html_url")
    @SerializedName("html_url")
    var htmlUrl: String? = null

    @ColumnInfo(name = "score")
    @SerializedName("score")
    var score: Double = 0.0

    @Ignore
    var isLoadRow: Boolean = false

    constructor(parcel: Parcel) : this(parcel.readInt()) {
        login = parcel.readString()
        avatarUrl = parcel.readString()
        htmlUrl = parcel.readString()
        score = parcel.readDouble()
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeString(login)
        parcel.writeString(avatarUrl)
        parcel.writeString(htmlUrl)
        parcel.writeDouble(score)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GithubUserModel> {
        override fun createFromParcel(parcel: Parcel): GithubUserModel {
            return GithubUserModel(parcel)
        }

        override fun newArray(size: Int): Array<GithubUserModel?> {
            return arrayOfNulls(size)
        }
    }
}