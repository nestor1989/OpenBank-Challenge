package com.idea3d.open_bank_challengue.model

import android.net.Uri
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroDetails(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name:String,
    @SerializedName("thumbnail")
    val image: PhotoDetails?,
    @SerializedName("description")
    val description:String,
    @SerializedName("comics")
    val comics: Comics,
    @SerializedName("series")
    val series: Series,
    @SerializedName("stories")
    val stories: Stories
): Parcelable

@Parcelize
data class Results(
    @SerializedName("results")
    val heroDetails: List<HeroDetails>
):Parcelable


@Parcelize
data class DataDetails(
    @SerializedName("data")
    val results: Results
): Parcelable

@Parcelize
data class PhotoDetails(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
): Parcelable

@Parcelize
data class Comics(
    @SerializedName("available")
    val available: Int
): Parcelable

@Parcelize
data class Series(
    @SerializedName("available")
    val available: Int
): Parcelable

@Parcelize
data class Stories(
    @SerializedName("available")
    val available: Int
): Parcelable

@Parcelize
data class ItemsComic(
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("name")
    val name: String
): Parcelable

