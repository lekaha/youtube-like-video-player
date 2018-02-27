package com.lekaha.simpletube.presentation.browse

import com.lekaha.simpletube.domain.interactor.SingleUseCase
import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.presentation.ViewResponse
import com.lekaha.simpletube.presentation.mapper.SimpletubeMapper
import io.reactivex.observers.DisposableSingleObserver

class BrowseSimpletubesPresenter constructor(
    val getSimpletubesUseCase: SingleUseCase<List<Simpletube>, Void>,
    val simpletubeMapper: SimpletubeMapper
) :
    BrowseSimpletubesContract.Presenter {

    var browseView: BrowseSimpletubesContract.View? = null

    override fun setView(view: BrowseSimpletubesContract.View) {
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

    internal fun handleGetSimpletubesSuccess(simpletubes: List<Simpletube>) {
        browseView?.onResponse(ViewResponse.success(simpletubes.map {
            simpletubeMapper.mapToView(it)
        }))
    }

    inner class SimpletubeSubscriber : DisposableSingleObserver<List<Simpletube>>() {

        override fun onSuccess(t: List<Simpletube>) {
            handleGetSimpletubesSuccess(t)
        }

        override fun onError(exception: Throwable) {
            browseView?.onResponse(ViewResponse.error(exception))
        }

    }

}