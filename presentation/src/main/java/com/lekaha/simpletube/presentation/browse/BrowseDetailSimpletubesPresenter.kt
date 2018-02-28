package com.lekaha.simpletube.presentation.browse

import com.lekaha.simpletube.domain.interactor.SingleUseCase
import com.lekaha.simpletube.domain.model.SimpletubeSection
import com.lekaha.simpletube.presentation.ViewResponse
import com.lekaha.simpletube.presentation.mapper.SimpletubeSectionMapper
import io.reactivex.observers.DisposableSingleObserver

class BrowseDetailSimpletubesPresenter constructor(
    val getSimpletubesUseCase: SingleUseCase<List<SimpletubeSection>, Void>,
    val simpletubeMapper: SimpletubeSectionMapper
) :
    BrowseDetailSimpletubesContract.Presenter {

    var browseView: BrowseDetailSimpletubesContract.View? = null

    override fun setView(view: BrowseDetailSimpletubesContract.View) {
        browseView = view
    }

    override fun start() {
        retrieveSimpletubes()
        browseView?.onResponse(ViewResponse.loading())
    }

    override fun stop() {
        getSimpletubesUseCase.dispose()
    }

    override fun retrieveSimpletubes() {
        getSimpletubesUseCase.execute(SimpletubeSubscriber())
    }

    internal fun handleGetSimpletubesSuccess(simpletubes: List<SimpletubeSection>) {
        browseView?.onResponse(ViewResponse.success(simpletubes.map {
            simpletubeMapper.mapToView(it)
        }))
    }

    inner class SimpletubeSubscriber : DisposableSingleObserver<List<SimpletubeSection>>() {

        override fun onSuccess(t: List<SimpletubeSection>) {
            handleGetSimpletubesSuccess(t)
        }

        override fun onError(exception: Throwable) {
            browseView?.onResponse(ViewResponse.error(exception))
        }

    }

}