package com.idea3d.open_bank_challengue.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    @SerializedName ("id")
    val id: Long,
    @SerializedName("name")
    val name:String,
    @SerializedName("thumbnail")
    val image: Photo?,
    @SerializedName("description")
    val description:String
    ): Parcelable

@Parcelize
data class HeroList(
    @SerializedName("results")
    val heroList: List<Hero>
):Parcelable

@Parcelize
data class Data(
    @SerializedName("data")
    val results: HeroList?
):Parcelable

@Parcelize
data class Photo(
    @SerializedName ("path")
    val path: String,
    @SerializedName ("extension")
    val extension: String
):Parcelable

