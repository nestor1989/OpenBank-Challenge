package com.idea3d.open_bank_challengue.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    @SerializedName("name")
    val name:String,
    @SerializedName("resourceURI")
    val image: String,
    @SerializedName("description")
    val description:String
    ): Parcelable

data class HeroList(
    @SerializedName("results")
    val heroList: List<Hero>
)


