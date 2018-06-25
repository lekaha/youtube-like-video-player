/*
 * Copyright (C) 2014 Pedro Vicente Gómez Sánchez.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lekaha.simpletube.ui.view.transformer

import android.view.View
import android.widget.RelativeLayout

/**
 * Transformer extension created to resize the view instead of scale it as the other
 * implementation does. This implementation is based on change the LayoutParams.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
internal class ResizeTransformer(view: View, parent: View) : Transformer(view, parent) {

    private val layoutParams: RelativeLayout.LayoutParams =
        view.layoutParams as RelativeLayout.LayoutParams

    /**
     * Changes view scale using view's LayoutParam.
     *
     * @param horizontalDragOffset used to calculate the new size.
     */
    override fun updateScaleX(horizontalDragOffset: Float) {
        layoutParams.width = (originalWidth * (1 - horizontalDragOffset / xScaleFactor)).toInt()
        view.layoutParams = layoutParams
    }

    /**
     * Changes view scale using view's LayoutParam.
     *
     * @param verticalDragOffset used to calculate the new size.
     */
    override fun updateScaleY(verticalDragOffset: Float) {
        layoutParams.height = (originalHeight * (1 - verticalDragOffset / yScaleFactor)).toInt()
        view.layoutParams = layoutParams
    }


    /**
     * Changes X view position using layout() method.
     *
     * @param verticalDragOffset used to calculate the new X position.
     */
    override fun updatePosition(verticalDragOffset: Float) {
        val right = getViewRightPosition(verticalDragOffset)
        val left = right - layoutParams.width
        val top = view.top
        val bottom = top + layoutParams.height

        view.layout(left, top, right, bottom)
    }


    /**
     * @return true if the right position of the view plus the right margin is equals to the parent
     * width.
     */
    override fun isViewAtRight(): Boolean {
        return view.right + marginRight == parentView.width
    }

    /**
     * @return true if the bottom position of the view plus the margin right is equals to
     * the parent view height.
     */
    override fun isViewAtBottom(): Boolean {
        return view.bottom + marginBottom == parentView.height
    }

    /**
     * @return true if the left position of the view is to the right of the seventy five percent of
     * the parent view width.
     */
    override fun isNextToRightBound(): Boolean {
        return view.left - marginRight > parentView.width * 0.75
    }

    /**
     * @return true if the left position of the view is to the left of the twenty five percent of
     * the parent width.
     */
    override fun isNextToLeftBound(): Boolean {
        return view.left - marginRight < parentView.width * 0.05
    }

    /**
     * Uses the Y scale factor to calculate the min possible height.
     */
    override fun getMinHeightPlusMargin(): Int {
        return (originalHeight * (1 - 1 / yScaleFactor) + marginBottom).toInt()
    }

    /**
     * Uses the X scale factor to calculate the min possible width.
     */
    override fun getMinWidthPlusMarginRight(): Int {
        return (originalWidth * (1 - 1 / xScaleFactor) + marginRight).toInt()
    }

    /**
     * Calculate the current view right position for a given verticalDragOffset.
     *
     * @param verticalDragOffset used to calculate the new right position.
     */
    private fun getViewRightPosition(verticalDragOffset: Float): Int {
        return (originalWidth - marginRight * verticalDragOffset).toInt()
    }

}
