package com.lekaha.simpletube.presentation.browse

import com.lekaha.simpletube.presentation.BasePresenter
import com.lekaha.simpletube.presentation.BaseView
import com.lekaha.simpletube.presentation.model.SimpletubeView

/**
 * Defines a contract of operations between the Browse Presenter and Browse View
 */
interface BrowseSimpletubesContract {

    interface View : BaseView<Presenter, List<SimpletubeView>>

    interface Presenter : BasePresenter {
        fun setView(view: View)
        fun retrieveSimpletubes()
    }

}