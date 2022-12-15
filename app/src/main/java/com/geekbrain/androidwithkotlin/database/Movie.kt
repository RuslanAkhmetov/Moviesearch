package com.geekbrain.androidwithkotlin.database

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val Id: String?,
    val Rank: String?,
    val RankUpDown: String?,
    val Title: String?,
    val FullTitle: String?,
    val Year: String?,
    val Image: String?,
    val Crew: String?,
    val IMDbRating: String?,
    val IMDbRatingCount: String?
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(Id)
        parcel.writeString(Rank)
        parcel.writeString(RankUpDown)
        parcel.writeString(Title)
        parcel.writeString(FullTitle)
        parcel.writeString(Year)
        parcel.writeString(Image)
        parcel.writeString(Crew)
        parcel.writeString(IMDbRating)
        parcel.writeString(IMDbRatingCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}
