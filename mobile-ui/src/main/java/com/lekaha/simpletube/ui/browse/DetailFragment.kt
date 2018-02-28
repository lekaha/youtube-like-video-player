package com.lekaha.simpletube.ui.browse

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import com.lekaha.simpletube.ui.BaseInjectingFragment
import com.lekaha.simpletube.ui.Navigator
import com.lekaha.simpletube.ui.R
import com.lekaha.simpletube.ui.model.BrowseDetailViewModel
import com.lekaha.simpletube.ui.model.BrowseViewModelFactory
import com.lekaha.simpletube.ui.model.SimpletubeViewModel
import com.lekaha.simpletube.ui.view.draggableView.DraggableListener
import kotlinx.android.synthetic.main.fragment_detail.detail_loading
import kotlinx.android.synthetic.main.fragment_detail.draggable_view
import javax.inject.Inject

class DetailFragment : BaseInjectingFragment() {

    @Inject
    lateinit var browseViewModelFactory: BrowseViewModelFactory
    @Inject
    lateinit var navigator: Navigator

    private var viewModel: BrowseDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel?.load()
        initializeDraggableView()

    }

    @LayoutRes
    override fun getLayoutId() = R.layout.fragment_detail

    override fun showUp() {
//        viewModel.showUpDetail()
//        showProgress()

        arguments?.get(ARGS_MODEL)?.takeIf {
            it is SimpletubeViewModel
        } ?.let {
            showPlayerView()
        } ?: run {
            throw IllegalStateException("Missing required view model")
        }


    }

    private fun showProgress() {
        detail_loading.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        detail_loading.visibility = View.GONE
    }

    private fun showPlayerView() {
        draggable_view.visibility = View.VISIBLE
        draggable_view.maximize()
    }

    private fun showErrorState() {

    }

    private fun hideErrorState() {

    }


    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, browseViewModelFactory)
            .get(BrowseDetailViewModel::class.java)

        viewModel?.isProgressing()!!.observe(this, Observer { progress ->
            if (progress!!) showProgress() else hideProgress()
        })

        viewModel?.occurredError()!!.observe(this, Observer { _ ->
            showErrorState()
        })

        viewModel?.fetchedData()!!.observe(this, Observer { data ->
            hideErrorState()

//            data?.takeIf {
//                it.isNotEmpty()
//            }?.apply {
//                    hideEmptyState()
//                    showSimpletubes(this)
//                } ?: run {
//                showEmptyState()
//                hideSimpletubes()
//            }
        })

        viewModel?.let { lifecycle.addObserver(it) }
    }

    private fun initializeDraggableView() {
        draggable_view.isClickToMaximizeEnabled = true
        draggable_view.post { draggable_view.closeToRight() }
        draggable_view.setDraggableListener(object : DraggableListener {
            override fun onMaximized() {
                //Empty
            }

            override fun onMinimized() {
                //Empty
            }

            override fun onClosedToLeft() {
//                tvShowPresenter.tvShowClosed()
            }

            override fun onClosedToRight() {
//                tvShowPresenter.tvShowClosed()
            }
        })
    }

    companion object {
        const val ARGS_MODEL = "ARGS_MODEL"

        fun newInstance() = DetailFragment()
    }
}