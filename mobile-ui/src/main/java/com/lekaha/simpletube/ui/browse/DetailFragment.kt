package com.lekaha.simpletube.ui.browse

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.lekaha.simpletube.presentation.model.SimpletubeSectionView
import com.lekaha.simpletube.ui.BaseInjectingFragment
import com.lekaha.simpletube.ui.Navigator
import com.lekaha.simpletube.ui.R
import com.lekaha.simpletube.ui.mapper.SimpletubeSectionMapper
import com.lekaha.simpletube.ui.model.BrowseDetailViewModel
import com.lekaha.simpletube.ui.model.BrowseDetailViewModelFactory
import com.lekaha.simpletube.ui.model.SimpletubeSectionViewModel
import com.lekaha.simpletube.ui.model.SimpletubeViewModel
import com.lekaha.simpletube.ui.view.draggableView.DraggableListener
import kotlinx.android.synthetic.main.fragment_detail.chapters
import kotlinx.android.synthetic.main.fragment_detail.detail_loading
import kotlinx.android.synthetic.main.fragment_detail.draggable_view
import kotlinx.android.synthetic.main.fragment_detail.player_view
import kotlinx.android.synthetic.main.fragment_detail.sections_title
import javax.inject.Inject
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.TransferListener
import com.google.android.exoplayer2.util.Util


class DetailFragment : BaseInjectingFragment() {

    @Inject
    lateinit var browseAdapter: BrowseAdapter
    @Inject
    lateinit var mapper: SimpletubeSectionMapper
    @Inject
    lateinit var browseDetailViewModelFactory: BrowseDetailViewModelFactory
    @Inject
    lateinit var navigator: Navigator

    private var viewModel: BrowseDetailViewModel? = null

    private lateinit var mediaDataSourceFactory: DefaultDataSourceFactory
    private lateinit var player: SimpleExoPlayer
    private val extractorsFactory = DefaultExtractorsFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeDraggableView()
        setupSectionListView()
        setupPlayer()
    }

    @LayoutRes
    override fun getLayoutId() = R.layout.fragment_detail

    override fun showUp() {
        arguments?.get(ARGS_MODEL)?.takeIf {
            it is SimpletubeViewModel
        }?.let {
                viewModel?.load((it as SimpletubeViewModel).title)
                showPlayerView()
            } ?: run {
            throw IllegalStateException("Missing required view model")
        }
    }

    private fun showSectionsTitle(title: String) {
        sections_title.text = title
        sections_title.visibility = View.VISIBLE
    }

    private fun hideSectionsTitle() {
        sections_title.text = ""
        sections_title.visibility = View.GONE
    }

    private fun showSimpletubeSections(simpletubeSections: List<SimpletubeSectionView>) {
        browseAdapter.update(mapper.mapToViewModels(simpletubeSections) {
            // TODO: When clicked a section
        }.filter {
                it.type() == SimpletubeSectionViewModel.DISPLAY_TYPE_BROWSE_DETAIL
            })

        browseAdapter.notifyDataSetChanged()
        chapters.visibility = View.VISIBLE
    }

    private fun hideSimpletubeSections() {
        chapters.visibility = View.GONE
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

    private fun hidePlayerView() {
        draggable_view.visibility = View.GONE
    }

    private fun showErrorState() {

    }

    private fun hideErrorState() {

    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, browseDetailViewModelFactory)
            .get(BrowseDetailViewModel::class.java)

        viewModel?.isProgressing()!!.observe(this, Observer { progress ->
            if (progress!!) showProgress() else hideProgress()
        })

        viewModel?.occurredError()!!.observe(this, Observer { _ ->
            showErrorState()
        })

        viewModel?.fetchedData()!!.observe(this, Observer { data ->
            hideErrorState()

            data?.takeIf {
                it.sections.isNotEmpty()
            }?.apply {
                    showSectionsTitle(this.title)
                    showSimpletubeSections(this.sections)

                    var source = ExtractorMediaSource.Factory(mediaDataSourceFactory)
                        .createMediaSource(Uri.parse(this.videoUrl))
                    player.prepare(source)

                } ?: run {
                hideSimpletubeSections()
            }
        })

        viewModel?.let { lifecycle.addObserver(it) }
    }

    private fun initializeDraggableView() {
        draggable_view.isClickToMaximizeEnabled = true
        draggable_view.post { draggable_view.closeToRight() }
        draggable_view.setDraggableListener(object : DraggableListener {
            override fun onMaximized() {
                //Empty
                player_view.showController()
            }

            override fun onMinimized() {
                //Empty
                player_view.hideController()
            }

            override fun onClosedToLeft() {
                //Empty
            }

            override fun onClosedToRight() {
                //Empty
            }
        })
    }

    private fun setupSectionListView() {
        chapters.layoutManager = LinearLayoutManager(activity)
        chapters.adapter = browseAdapter
    }

    private fun setupPlayer() {
        val bandWidthMeter = DefaultBandwidthMeter()
        val trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandWidthMeter))
        player = ExoPlayerFactory.newSimpleInstance(activity, trackSelector)
        player_view.player = player
        mediaDataSourceFactory = DefaultDataSourceFactory(
            activity,
            Util.getUserAgent(activity, "Simpletube"),
            bandWidthMeter as TransferListener<in DataSource>
        )
    }

    fun isShowUp(): Boolean {
        return draggable_view.isMaximized
    }

    fun showMini() {
        draggable_view.minimize();
    }

    companion object {
        const val ARGS_MODEL = "ARGS_MODEL"

        fun newInstance() = DetailFragment()
    }
}