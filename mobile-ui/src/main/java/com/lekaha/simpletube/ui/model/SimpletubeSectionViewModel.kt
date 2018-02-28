package com.lekaha.simpletube.ui.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Representation for a [SimpletubeSectionViewModel] fetched from an external layer data source
 */
data class SimpletubeSectionViewModel(val title: String, val description: String) :
    Parcelable {
    var onClickListener: ((SimpletubeSectionViewModel) -> Unit)? = null

    fun click() {
        onClickListener?.apply {
            this(this@SimpletubeSectionViewModel)
        }
    }

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(description)
    }

    companion object {
        const val DISPLAY_TYPE_BROWSE_DETAIL: Int = 5

        @JvmField
        val CREATOR: Parcelable.Creator<SimpletubeSectionViewModel> =
            object : Parcelable.Creator<SimpletubeSectionViewModel> {
                override fun createFromParcel(source: Parcel): SimpletubeSectionViewModel =
                    SimpletubeSectionViewModel(source)

                override fun newArray(size: Int): Array<SimpletubeSectionViewModel?> = arrayOfNulls(size)
            }
    }
}