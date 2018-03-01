package com.lekaha.simpletube.ui

import android.content.Context
import android.net.Uri
import android.view.SurfaceView
import android.view.View
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.TransferListener
import com.google.android.exoplayer2.util.Util


/**
 * This class is based (copied) from this medium post:
 * https://medium.com/tall-programmer/fullscreen-functionality-with-android-exoplayer-5fddad45509f
 */

class PlayerVideoHandler(private var context : Context) {
    private val player: SimpleExoPlayer
    private var playerUri: Uri? = null
    private var isPlayerPlaying: Boolean = false
    private var currentPosition: Long = 0
    private val bandWidthMeter = DefaultBandwidthMeter()

    init {
        val trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandWidthMeter))
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector)
    }

    fun prepareExoPlayerForUri(uri: Uri, surfaceView: View) {
        if (uri != playerUri) {
            currentPosition = 0
        }
        val mediaDataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, "PlayerVideoHandler"),
            bandWidthMeter as TransferListener<in DataSource>
        )

        playerUri = uri
        isPlayerPlaying = true

        val source =
            ExtractorMediaSource.Factory(mediaDataSourceFactory).createMediaSource(playerUri)

        player.clearVideoSurface()
        player.setVideoSurfaceView(surfaceView as SurfaceView?)

        player.prepare(source)
        player.seekTo(currentPosition)

    }

    fun releaseVideoPlayer() {
        currentPosition = player.currentPosition
        player.release()
    }

    fun goToBackground() {
        isPlayerPlaying = player.playWhenReady
        player.playWhenReady = false
        currentPosition = player.currentPosition
    }

    fun goToForeground() {
        player.playWhenReady = isPlayerPlaying
    }

    fun getUri() = playerUri

    fun isPlaying()  = isPlayerPlaying

    fun getPlayer() = player
}