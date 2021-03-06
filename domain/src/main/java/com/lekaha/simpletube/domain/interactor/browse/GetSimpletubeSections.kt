package com.lekaha.simpletube.domain.interactor.browse

import com.lekaha.simpletube.domain.executor.PostExecutionThread
import com.lekaha.simpletube.domain.executor.ThreadExecutor
import com.lekaha.simpletube.domain.interactor.SingleUseCase
import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.domain.model.SimpletubeSection
import com.lekaha.simpletube.domain.model.SimpletubeSections
import com.lekaha.simpletube.domain.repository.SimpletubeRepository
import io.reactivex.Single

/**
 * Use case used for retreiving a [List] of [SimpletubeSection]
 * instances from the [SimpletubeRepository]
 */
open class GetSimpletubeSections constructor(
    val simpletubeRepository: SimpletubeRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) :
    SingleUseCase<SimpletubeSections, Simpletube>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Simpletube?): Single<SimpletubeSections> {
        return simpletubeRepository.getSimpletubeSections(params!!)
    }

}