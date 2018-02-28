package com.lekaha.simpletube.ui.model

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lekaha.simpletube.presentation.ViewResponse
import com.lekaha.simpletube.presentation.browse.BrowseDetailSimpletubesContract
import com.lekaha.simpletube.presentation.model.SimpletubeSectionsView

class BrowseDetailViewModel(var detailPresenter: BrowseDetailSimpletubesContract.Presenter)
    : ViewModel(), LifecycleObserver, BrowseDetailSimpletubesContract.View {

    private var isProgressing: MutableLiveData<Boolean> = MutableLiveData()
    private var occurredError: MutableLiveData<Throwable> = MutableLiveData()
    private var simpletubeSections: MutableLiveData<SimpletubeSectionsView> = MutableLiveData()
    private lateinit var browseDetailTitle: String

    init {
        detailPresenter.setView(this)
    }

    override fun getSimpletubeName(): String = browseDetailTitle

    override fun setPresenter(presenter: BrowseDetailSimpletubesContract.Presenter) {
        this.detailPresenter = presenter
        this.detailPresenter.setView(this)
    }

    override fun onResponse(response: ViewResponse<SimpletubeSectionsView>) {
        when(response.status) {
            ViewResponse.Status.LOADING -> { isProgressing.value = true }
            ViewResponse.Status.ERROR -> {
                isProgressing.value = false
                occurredError.value = response.error
            }
            ViewResponse.Status.SUCCESS -> {
                isProgressing.value = false
                simpletubeSections.value = response.data
            }
        }
    }

    fun isProgressing(): LiveData<Boolean> = isProgressing

    fun occurredError(): LiveData<Throwable> = occurredError

    fun fetchedData(): LiveData<SimpletubeSectionsView> = simpletubeSections

    fun load(title: String) {
        browseDetailTitle = title
        detailPresenter.start()
    }

    override fun onCleared() {
        detailPresenter.stop()
    }


}