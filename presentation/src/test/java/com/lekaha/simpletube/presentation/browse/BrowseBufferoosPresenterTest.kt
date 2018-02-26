package com.lekaha.simpletube.presentation.browse

import com.lekaha.simpletube.domain.interactor.browse.GetBufferoos
import com.lekaha.simpletube.domain.model.Bufferoo
import com.lekaha.simpletube.presentation.ViewResponse
import com.lekaha.simpletube.presentation.mapper.BufferooMapper
import com.lekaha.simpletube.presentation.model.BufferooView
import com.lekaha.simpletube.presentation.test.factory.BufferooFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BrowseBufferoosPresenterTest {

    private lateinit var mockBrowseBufferoosView: BrowseBufferoosContract.View
    private lateinit var mockBufferooMapper: BufferooMapper
    private lateinit var mockGetBufferoos: GetBufferoos

    private lateinit var browseBufferoosPresenter: BrowseBufferoosPresenter
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<Bufferoo>>>

    @Before
    fun setup() {
        captor = argumentCaptor<DisposableSingleObserver<List<Bufferoo>>>()
        mockBrowseBufferoosView = mock()
        mockBufferooMapper = mock()
        mockGetBufferoos = mock()
        browseBufferoosPresenter = BrowseBufferoosPresenter(
                mockGetBufferoos, mockBufferooMapper)
        browseBufferoosPresenter.setView(mockBrowseBufferoosView)
    }

    @Test
    fun retrieveBufferoosShowsBufferoos() {
        val bufferoos = BufferooFactory.makeBufferooList(2)
        browseBufferoosPresenter.retrieveBufferoos()

        verify(mockGetBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onSuccess(bufferoos)
        verify(mockBufferooMapper, times(2)).mapToView(anyVararg<Bufferoo>())
        verify(mockBrowseBufferoosView).onResponse(anyVararg<ViewResponse<List<BufferooView>>>())
    }

    @Test
    fun retrieveBufferoosHidesEmptyStateWhenErrorThrown() {
        browseBufferoosPresenter.retrieveBufferoos()

        verify(mockGetBufferoos).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        verify(mockBufferooMapper, never()).mapToView(anyVararg<Bufferoo>())
        verify(mockBrowseBufferoosView).onResponse(anyVararg<ViewResponse<List<BufferooView>>>())
    }
    //</editor-fold>

}