package com.lekaha.simpletube.ui.browse

import android.os.Bundle
import com.lekaha.simpletube.ui.BaseInjectingActivity
import com.lekaha.simpletube.ui.Navigator
import com.lekaha.simpletube.ui.R
import javax.inject.Inject

class BrowseActivity :
    BaseInjectingActivity<Any>() {

    @Inject
    lateinit var navigator: Navigator

    override fun createComponent(): Any? {
        return null
    }

    override fun getLayoutId(): Int = R.layout.activity_browse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        savedInstanceState?.let {

        } ?: run {
            navigator.navigateTo {
                replace(R.id.list, BrowseFragment.newInstance())
                add(R.id.detail, DetailFragment.newInstance())
            }
        }
    }

    override fun onBackPressed() {
        navigator.pressedBackKey<DetailFragment>(R.id.detail) {
            it.isShowUp().apply { it.showMini() }
        }.takeIf { !it }?.run { super.onBackPressed() }
    }
}