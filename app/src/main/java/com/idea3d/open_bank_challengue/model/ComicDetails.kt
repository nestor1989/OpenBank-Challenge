package com.idea3d.open_bank_challengue.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicDetails(
    @SerializedName("title")
    val title:String,
    @SerializedName("thumbnail")
    val image: Photo
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

