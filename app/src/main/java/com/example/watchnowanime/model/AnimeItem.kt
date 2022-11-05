package com.example.watchnowanime.model

import android.os.Parcel
import android.os.Parcelable


data class AnimeItem(
    val animeId: String?,
    val animeImg: String?,
    val animeTitle: String?,
    val animeUrl: String?,
    val releasedDate: String?
):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(animeId)
        parcel.writeString(animeImg)
        parcel.writeString(animeTitle)
        parcel.writeString(animeUrl)
        parcel.writeString(releasedDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnimeItem> {
        override fun createFromParcel(parcel: Parcel): AnimeItem {
            return AnimeItem(parcel)
        }

        override fun newArray(size: Int): Array<AnimeItem?> {
            return arrayOfNulls(size)
        }
    }
}