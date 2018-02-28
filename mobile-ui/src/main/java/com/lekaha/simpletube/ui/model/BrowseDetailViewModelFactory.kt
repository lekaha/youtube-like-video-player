package com.lekaha.simpletube.ui.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.lekaha.simpletube.presentation.browse.BrowseDetailSimpletubesContract

class BrowseDetailViewModelFactory(val presenter: BrowseDetailSimpletubesContract.Presenter)
    : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(BrowseDetailViewModel::class.java)) {
            return BrowseDetailViewModel(presenter) as T
        }

        throw IllegalArgumentException("Illegal ViewModel class")
    }
}