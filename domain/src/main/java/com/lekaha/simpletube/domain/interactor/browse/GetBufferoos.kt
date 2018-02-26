package com.lekaha.simpletube.domain.interactor.browse

import com.lekaha.simpletube.domain.executor.PostExecutionThread
import com.lekaha.simpletube.domain.executor.ThreadExecutor
import com.lekaha.simpletube.domain.interactor.SingleUseCase
import com.lekaha.simpletube.domain.model.Bufferoo
import com.lekaha.simpletube.domain.repository.BufferooRepository
import io.reactivex.Single

/**
 * Use case used for retreiving a [List] of [Bufferoo] instances from the [BufferooRepository]
 */
open class GetBufferoos constructor(
    val bufferooRepository: BufferooRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) :
    SingleUseCase<List<Bufferoo>, Void?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: Void?): Single<List<Bufferoo>> {
        return bufferooRepository.getBufferoos()
    }

}