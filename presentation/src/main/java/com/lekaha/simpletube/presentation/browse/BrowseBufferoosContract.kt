package com.lekaha.simpletube.presentation.browse

import com.lekaha.simpletube.presentation.BasePresenter
import com.lekaha.simpletube.presentation.BaseView
import com.lekaha.simpletube.presentation.model.BufferooView

/**
 * Defines a contract of operations between the Browse Presenter and Browse View
 */
interface BrowseBufferoosContract {

    interface View : BaseView<Presenter, List<BufferooView>>

    interface Presenter : BasePresenter {
        fun setView(view: View)
        fun retrieveBufferoos()
    }

}