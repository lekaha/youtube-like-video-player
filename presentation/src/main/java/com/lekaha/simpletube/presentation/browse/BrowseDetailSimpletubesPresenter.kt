package com.lekaha.simpletube.presentation.browse

import com.lekaha.simpletube.domain.interactor.SingleUseCase
import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.domain.model.SimpletubeSections
import com.lekaha.simpletube.presentation.ViewResponse
import com.lekaha.simpletube.presentation.mapper.SimpletubeSectionsMapper
import io.reactivex.observers.DisposableSingleObserver

class BrowseDetailSimpletubesPresenter constructor(
    val getSimpletubesUseCase: SingleUseCase<SimpletubeSections, Simpletube>,
    val getSimpletubeUseCase: SingleUseCase<Simpletube, String>,
    val simpletubeMapper: SimpletubeSectionsMapper
) :
    BrowseDetailSimpletubesContract.Presenter {

    var browseView: BrowseDetailSimpletubesContract.View? = null

    override fun setView(view: BrowseDetailSimpletubesContract.View) {
        browseView = view
    }

    override fun start() {
        browseView?.apply {
            retrieveSimpletubes(getSimpletubeName())
            onResponse(ViewResponse.loading())
        }
    }

    override fun stop() {
        getSimpletubesUseCase.dispose()
        getSimpletubeUseCase.dispose()
    }

    override fun retrieveSimpletubes(title: String) {
        getSimpletubesUseCase.execute(
            SimpletubeSubscriber(),
            getSimpletubeUseCase.emit(title).flatMap {
            getSimpletubesUseCase.emit(it)
        })
    }

    internal fun handleGetSimpletubesSuccess(simpletubes: SimpletubeSections) {
        browseView?.onResponse(ViewResponse.success(simpletubeMapper.mapToView(simpletubes)))
    }

    inner class SimpletubeSubscriber : DisposableSingleObserver<SimpletubeSections>() {

        override fun onSuccess(t: SimpletubeSections) {
            handleGetSimpletubesSuccess(t)
        }

        override fun onError(exception: Throwable) {
            browseView?.onResponse(ViewResponse.error(exception))
        }

    }

}