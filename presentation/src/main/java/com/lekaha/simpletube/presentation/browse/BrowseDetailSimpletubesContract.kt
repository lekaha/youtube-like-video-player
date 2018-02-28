package com.lekaha.simpletube.presentation.browse

import com.lekaha.simpletube.presentation.BasePresenter
import com.lekaha.simpletube.presentation.BaseView
import com.lekaha.simpletube.presentation.model.SimpletubeSectionView

/**
 * Defines a contract of operations between the Browse Presenter and Browse View
 */
interface BrowseDetailSimpletubesContract {

    interface View : BaseView<Presenter, List<SimpletubeSectionView>>

    interface Presenter : BasePresenter {
        fun setView(view: View)
        fun retrieveSimpletubes()
    }

}