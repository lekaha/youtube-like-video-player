package com.lekaha.simpletube.domain.usecase.simpletube

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import com.lekaha.simpletube.domain.executor.PostExecutionThread
import com.lekaha.simpletube.domain.executor.ThreadExecutor
import com.lekaha.simpletube.domain.interactor.browse.GetSimpletubes
import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.domain.repository.SimpletubeRepository
import com.lekaha.simpletube.domain.test.factory.SimpletubeFactory
import org.junit.Before
import org.junit.Test

class GetSimpletubesTest {

    private lateinit var getSimpletubes: GetSimpletubes

    private lateinit var mockThreadExecutor: ThreadExecutor
    private lateinit var mockPostExecutionThread: PostExecutionThread
    private lateinit var mockSimpletubeRepository: SimpletubeRepository

    @Before
    fun setUp() {
        mockThreadExecutor = mock()
        mockPostExecutionThread = mock()
        mockSimpletubeRepository = mock()
        getSimpletubes = GetSimpletubes(
            mockSimpletubeRepository, mockThreadExecutor,
                mockPostExecutionThread)
    }

    @Test
    fun buildUseCaseObservableCallsRepository() {
        getSimpletubes.buildUseCaseObservable(null)
        verify(mockSimpletubeRepository).getSimpletubes()
    }

    @Test
    fun buildUseCaseObservableCompletes() {
        stubSimpletubeRepositoryGetSimpletubes(Single.just(SimpletubeFactory.makeSimpletubeList(2)))
        val testObserver = getSimpletubes.buildUseCaseObservable(null).test()
        testObserver.assertComplete()
    }

    @Test
    fun buildUseCaseObservableReturnsData() {
        val simpletubes = SimpletubeFactory.makeSimpletubeList(2)
        stubSimpletubeRepositoryGetSimpletubes(Single.just(simpletubes))
        val testObserver = getSimpletubes.buildUseCaseObservable(null).test()
        testObserver.assertValue(simpletubes)
    }

    private fun stubSimpletubeRepositoryGetSimpletubes(single: Single<List<Simpletube>>) {
        whenever(mockSimpletubeRepository.getSimpletubes())
                .thenReturn(single)
    }

}