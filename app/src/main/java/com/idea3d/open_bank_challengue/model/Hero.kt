package com.idea3d.open_bank_challengue.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
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

@Entity
data class HeroEntity(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "name")
    val name:String,
    @ColumnInfo(name ="path")
    val path: String,
    @ColumnInfo(name ="extension")
    val extension: String,
    @ColumnInfo(name ="description")
    val description:String

)

