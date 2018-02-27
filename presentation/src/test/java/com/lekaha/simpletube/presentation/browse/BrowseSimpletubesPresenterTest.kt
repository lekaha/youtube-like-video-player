package com.lekaha.simpletube.presentation.browse

import com.lekaha.simpletube.domain.interactor.browse.GetSimpletubes
import com.lekaha.simpletube.domain.model.Simpletube
import com.lekaha.simpletube.presentation.ViewResponse
import com.lekaha.simpletube.presentation.mapper.SimpletubeMapper
import com.lekaha.simpletube.presentation.model.SimpletubeView
import com.lekaha.simpletube.presentation.test.factory.SimpletubeFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BrowseSimpletubesPresenterTest {

    private lateinit var mockBrowseSimpletubesView: BrowseSimpletubesContract.View
    private lateinit var mockSimpletubeMapper: SimpletubeMapper
    private lateinit var mockGetSimpletubes: GetSimpletubes

    private lateinit var browseSimpletubesPresenter: BrowseSimpletubesPresenter
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<Simpletube>>>

    @Before
    fun setup() {
        captor = argumentCaptor<DisposableSingleObserver<List<Simpletube>>>()
        mockBrowseSimpletubesView = mock()
        mockSimpletubeMapper = mock()
        mockGetSimpletubes = mock()
        browseSimpletubesPresenter = BrowseSimpletubesPresenter(
            mockGetSimpletubes, mockSimpletubeMapper
        )
        browseSimpletubesPresenter.setView(mockBrowseSimpletubesView)
    }

    @Test
    fun retrieveSimpletubesShowsSimpletubes() {
        val simpletubes = SimpletubeFactory.makeSimpletubeList(2)
        browseSimpletubesPresenter.retrieveSimpletubes()

        verify(mockGetSimpletubes).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(simpletubes)
        verify(mockSimpletubeMapper, times(2)).mapToView(anyVararg<Simpletube>())
        verify(mockBrowseSimpletubesView).onResponse(anyVararg<ViewResponse<List<SimpletubeView>>>())
    }

    @Test
    fun retrieveSimpletubesHidesEmptyStateWhenErrorThrown() {
        browseSimpletubesPresenter.retrieveSimpletubes()

        verify(mockGetSimpletubes).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockSimpletubeMapper, never()).mapToView(anyVararg<Simpletube>())
        verify(mockBrowseSimpletubesView).onResponse(anyVararg<ViewResponse<List<SimpletubeView>>>())
    }

}