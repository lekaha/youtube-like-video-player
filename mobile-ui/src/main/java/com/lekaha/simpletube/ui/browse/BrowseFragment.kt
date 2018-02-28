package com.lekaha.simpletube.ui.browse

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.lekaha.simpletube.presentation.model.SimpletubeView
import com.lekaha.simpletube.ui.BaseInjectingFragment
import com.lekaha.simpletube.ui.Navigator
import com.lekaha.simpletube.ui.R
import com.lekaha.simpletube.ui.mapper.SimpletubeMapper
import com.lekaha.simpletube.ui.model.BrowseViewModel
import com.lekaha.simpletube.ui.model.BrowseViewModelFactory
import com.lekaha.simpletube.ui.model.SimpletubeViewModel
import com.lekaha.simpletube.ui.model.SimpletubeViewModel.Companion
import kotlinx.android.synthetic.main.fragment_browse.progress
import kotlinx.android.synthetic.main.fragment_browse.recycler_browse
import javax.inject.Inject

class BrowseFragment : BaseInjectingFragment() {

    @Inject
    lateinit var browseAdapter: BrowseAdapter
    @Inject
    lateinit var mapper: SimpletubeMapper
    @Inject
    lateinit var browseViewModelFactory: BrowseViewModelFactory
    @Inject
    lateinit var navigator: Navigator

    private var viewModel: BrowseViewModel? = null

    companion object {
        fun newInstance(): BrowseFragment = BrowseFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBrowseRecycler()

        viewModel?.load()
    }

    @LayoutRes
    override fun getLayoutId(): Int = R.layout.fragment_browse

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }

    private fun showSimpletubes(simpletubes: List<SimpletubeView>) {
        browseAdapter.update(mapper.mapToViewModels(simpletubes) {
            val b = Bundle()
            b.putParcelable(DetailFragment.ARGS_MODEL, it)
            navigator.showUp<DetailFragment>(R.id.detail, b)
        }.filter { it.type() == SimpletubeViewModel.DISPLAY_TYPE_BROWSE })
        browseAdapter.notifyDataSetChanged()
        recycler_browse.visibility = View.VISIBLE
    }

    private fun hideSimpletubes() {
        recycler_browse.visibility = View.GONE
    }

    private fun showErrorState() {

    }

    private fun hideErrorState() {

    }

    private fun showEmptyState() {

    }

    private fun hideEmptyState() {

    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, browseViewModelFactory)
            .get(BrowseViewModel::class.java)

        viewModel?.isProgressing()!!.observe(this, Observer { progress ->
            if (progress!!) showProgress() else hideProgress()
        })

        viewModel?.occurredError()!!.observe(this, Observer { _ ->
            showErrorState()
        })

        viewModel?.fetchedData()!!.observe(this, Observer { data ->
            hideErrorState()

            data?.takeIf {
                it.isNotEmpty()
            }?.apply {
                    hideEmptyState()
                    showSimpletubes(this)
                } ?: run {
                showEmptyState()
                hideSimpletubes()
            }
        })

        viewModel?.let { lifecycle.addObserver(it) }
    }

    private fun setupBrowseRecycler() {
        recycler_browse.layoutManager = LinearLayoutManager(activity)
        recycler_browse.adapter = browseAdapter
    }
}