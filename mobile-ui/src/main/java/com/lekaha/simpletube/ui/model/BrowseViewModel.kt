package com.lekaha.simpletube.ui.model

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.lekaha.simpletube.presentation.ViewResponse
import com.lekaha.simpletube.presentation.browse.BrowseSimpletubesContract
import com.lekaha.simpletube.presentation.model.SimpletubeView

class BrowseViewModel(var onboardingPresenter: BrowseSimpletubesContract.Presenter)
    : ViewModel(), LifecycleObserver, BrowseSimpletubesContract.View {

    private var isProgressing: MutableLiveData<Boolean> = MutableLiveData()
    private var occurredError: MutableLiveData<Throwable> = MutableLiveData()
    private var simpletubes: MutableLiveData<List<SimpletubeView>> = MutableLiveData()

    init {
        onboardingPresenter.setView(this)
    }

    override fun setPresenter(presenter: BrowseSimpletubesContract.Presenter) {
        onboardingPresenter = presenter
        onboardingPresenter.setView(this)
    }

    override fun onResponse(response: ViewResponse<List<SimpletubeView>>) {
        when(response.status) {
            ViewResponse.Status.LOADING -> { isProgressing.value = true }
            ViewResponse.Status.ERROR -> {
                isProgressing.value = false
                occurredError.value = response.error
            }
            ViewResponse.Status.SUCCESS -> {
                isProgressing.value = false
                simpletubes.value = response.data
            }
        }
    }

    fun isProgressing(): LiveData<Boolean> = isProgressing

    fun occurredError(): LiveData<Throwable> = occurredError

    fun fetchedData(): LiveData<List<SimpletubeView>> = simpletubes

    fun load() {
        onboardingPresenter.start()
    }

    override fun onCleared() {
        onboardingPresenter.stop()
    }


}