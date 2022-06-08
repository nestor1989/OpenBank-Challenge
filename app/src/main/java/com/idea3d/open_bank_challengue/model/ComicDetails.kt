package com.idea3d.open_bank_challengue.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicDetails(
    @SerializedName("id")
    val id:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("thumbnail")
    val image: Photo,
    @SerializedName("description")
    val description: String,
    @SerializedName("urls")
    val urls: List<Urls>
): Parcelable

@Parcelize
data class ComicList(
    @SerializedName("results")
    val comicList: List<ComicDetails>
):Parcelable

@Parcelize
data class DataComic(
    @SerializedName("data")
    val results: ComicList
):Parcelable

@Parcelize
data class Urls(
    @SerializedName("type")
    val type:String,
    @SerializedName("url")
    val url: String
):Parcelable



