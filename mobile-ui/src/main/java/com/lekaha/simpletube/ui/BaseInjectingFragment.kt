package com.lekaha.simpletube.ui

import android.content.Context
import android.support.annotation.CallSuper

abstract class BaseInjectingFragment: BaseFragment() {

    @CallSuper
    open protected fun onInject() {}

    @CallSuper
    override fun onAttach(context: Context?) {
        onInject()
        super.onAttach(context)
    }
}