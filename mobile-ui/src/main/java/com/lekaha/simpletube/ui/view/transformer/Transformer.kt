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
import com.lekaha.simpletube.ui.view.ViewHelper

/**
 * Abstract class created to be implemented by different classes are going to change the size of a
 * view. The most basic one is going to scale the view and the most complex used with VideoView is
 * going to change the size of the view.
 *
 *
 * The view used in this class has to be contained by a RelativeLayout.
 *
 *
 * This class also provide information about the size of the view and the position because
 * different Transformer implementations could change the size of the view but not the position,
 * like ScaleTransformer does.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
abstract class Transformer(protected val view: View, protected val parentView: View) {

    var marginRight: Int = 0
        set(marginRight) {
            field = Math.round(marginRight.toFloat())
        }
    var marginBottom: Int = 0
        set(marginBottom) {
            field = Math.round(marginBottom.toFloat())
        }

    var xScaleFactor: Float = 0.toFloat()
    var yScaleFactor: Float = 0.toFloat()

    var originalHeight: Int = 0
        get() {
            if (field == 0) {
                field = view.measuredHeight
            }
            return field
        }

    var originalWidth: Int = 0
        get() {
            if (field == 0) {
                field = view.measuredWidth
            }
            return field
        }

    val isViewAtTop: Boolean
        get() = view.top == 0

    val isAboveTheMiddle: Boolean
        get() {
            val parentHeight = parentView.height
            val viewYPosition = ViewHelper.getY(view) + view.height * 0.5f
            return viewYPosition < parentHeight * 0.5
        }

    abstract fun isViewAtRight(): Boolean

    abstract fun isViewAtBottom(): Boolean

    abstract fun isNextToRightBound(): Boolean

    abstract fun isNextToLeftBound(): Boolean

    /**
     * @return min possible height, after apply the transformation, plus the margin right.
     */
    abstract fun getMinHeightPlusMargin(): Int

    /**
     * @return min possible width, after apply the transformation.
     */
    abstract fun getMinWidthPlusMarginRight(): Int

    /**
     * Change view height using the LayoutParams of the view.
     *
     * @param newHeight to change..
     */
    fun setViewHeight(newHeight: Int) {
        if (newHeight > 0) {
            originalHeight = newHeight
            val layoutParams = view.layoutParams as RelativeLayout.LayoutParams
            layoutParams.height = newHeight
            view.layoutParams = layoutParams
        }
    }

    abstract fun updatePosition(verticalDragOffset: Float)

    abstract fun updateScale(verticalDragOffset: Float)
}
