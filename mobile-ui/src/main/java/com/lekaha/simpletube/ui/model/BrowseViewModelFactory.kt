package com.lekaha.simpletube.ui.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.lekaha.simpletube.presentation.browse.BrowseSimpletubesContract

class BrowseViewModelFactory(val presenter: BrowseSimpletubesContract.Presenter)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BrowseViewModel::class.java)) {
            return BrowseViewModel(presenter) as T
        }

        throw IllegalArgumentException("Illegal ViewModel class")
    }
}