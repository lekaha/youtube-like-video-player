package com.lekaha.simpletube.ui.browse

import android.view.View
import com.lekaha.simpletube.ui.view.ViewHelper
import com.lekaha.simpletube.ui.view.transformer.ScaleTransformer

class AsyncScaleXYTransformer(view: View, parent: View): ScaleTransformer(view, parent) {

    companion object {
        const val ASYNC_PARTIAL = .9f
    }

    override fun updateScale(horizontalDragOffset: Float, verticalDragOffset: Float) {
        updateScaleY(verticalDragOffset)
        updateScaleX(0f)
        if (verticalDragOffset > ASYNC_PARTIAL) {
            val offset = (verticalDragOffset - ASYNC_PARTIAL) / (1 - ASYNC_PARTIAL)
            updateScaleX(offset)
        }
    }

    override fun updatePosition(verticalDragOffset: Float) {
        ViewHelper.setPivotX(view, (marginRight).toFloat())
        ViewHelper.setPivotY(view, (view.height - marginBottom).toFloat())
    }
}
