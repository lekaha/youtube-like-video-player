package com.lekaha.simpletube.ui.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Representation for a [SimpletubeViewModel] fetched from an external layer data source
 */
data class SimpletubeViewModel(val name: String, val title: String, val avatar: String) :
    Parcelable {
    var onClickListener: ((SimpletubeViewModel) -> Unit)? = null

    fun click() {
        onClickListener?.apply {
            this(this@SimpletubeViewModel)
        }
    }

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(title)
        writeString(avatar)
    }

    companion object {
        const val DISPLAY_TYPE_BROWSE: Int = 3

        @JvmField
        val CREATOR: Parcelable.Creator<SimpletubeViewModel> =
            object : Parcelable.Creator<SimpletubeViewModel> {
                override fun createFromParcel(source: Parcel): SimpletubeViewModel =
                    SimpletubeViewModel(source)

                override fun newArray(size: Int): Array<SimpletubeViewModel?> = arrayOfNulls(size)
            }
    }
}