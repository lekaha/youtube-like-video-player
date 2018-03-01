package com.lekaha.simpletube.ui.browse

import android.net.Uri
import android.os.Build
import android.os.Build.VERSION_CODES.KITKAT
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.View
import com.lekaha.simpletube.ui.BaseInjectingActivity
import com.lekaha.simpletube.ui.Navigator
import com.lekaha.simpletube.ui.PlayerVideoHandler
import com.lekaha.simpletube.ui.R
import kotlinx.android.synthetic.main.activity_fullscreen.player_view
import kotlinx.android.synthetic.main.exo_playback_control_view.exo_fullscreen_button
import kotlinx.android.synthetic.main.exo_playback_control_view.exo_fullscreen_icon
import javax.inject.Inject


class VideoFullscreenActivity : BaseInjectingActivity<Any>() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var player: PlayerVideoHandler

    override fun createComponent(): Any? {
        return null
    }

    @LayoutRes
    override fun getLayoutId() = R.layout.activity_fullscreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        player_view.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LOW_PROFILE or View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        if (Build.VERSION.SDK_INT >= KITKAT) {
            player_view.systemUiVisibility = player_view.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }

        player_view.player = player.getPlayer()
        exo_fullscreen_icon.setImageResource(R.drawable.ic_fullscreen_skrink)
        exo_fullscreen_button.setOnClickListener {
            onBackPressed()
        }


    }

    override fun onResume() {
        super.onResume()
        player.goToForeground()

        val uri: Uri? = intent.getParcelableExtra<Uri>(EXTRA_URI)
        uri?.takeIf { uri != null }?.run {
            player.prepareExoPlayerForUri(uri, player_view.videoSurfaceView)
        }
    }

    override fun onPause() {
        super.onPause()
        player.goToBackground()
    }

    companion object {
        const val EXTRA_URI = "EXTRA_URI"
    }
}