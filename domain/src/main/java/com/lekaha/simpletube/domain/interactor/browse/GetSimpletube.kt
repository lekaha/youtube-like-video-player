package com.lekaha.simpletube.domain.interactor.browse

import com.lekaha.simpletube.domain.executor.PostExecutionThread
import com.lekaha.simpletube.domain.executor.ThreadExecutor
import com.lekaha.simpletube.domain.interactor.SingleUseCase
import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.domain.repository.SimpletubeRepository
import io.reactivex.Single

/**
 * Use case used for retreiving a [List] of [Simpletube] instances from the [SimpletubeRepository]
 */
open class GetSimpletube constructor(
    val simpletubeRepository: SimpletubeRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) :
    SingleUseCase<Simpletube, String>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: String?): Single<Simpletube> {
        return simpletubeRepository.getSimpletube(params)
    }

}