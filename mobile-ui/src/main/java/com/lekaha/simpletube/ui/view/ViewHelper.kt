package com.lekaha.simpletube.ui.view

import android.view.View

class ViewHelper {

    companion object {

        fun getAlpha(view: View) =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).alpha
            else
                Honeycomb.getAlpha(view)

        fun setAlpha(view: View, alpha: Float) =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).alpha = alpha
            else
                Honeycomb.setAlpha(view, alpha)

        fun getPivotX(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).pivotX
            else
                Honeycomb.getPivotX(view)

        fun setPivotX(view: View, pivotX: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).pivotX = pivotX
            } else {
                Honeycomb.setPivotX(view, pivotX)
            }
        }

        fun getPivotY(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).pivotY
            else
                Honeycomb.getPivotY(view)

        fun setPivotY(view: View, pivotY: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).pivotY = pivotY
            } else {
                Honeycomb.setPivotY(view, pivotY)
            }
        }

        fun getRotation(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).rotation
            else
                Honeycomb.getRotation(view)

        fun setRotation(view: View, rotation: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).rotation = rotation
            } else {
                Honeycomb.setRotation(view, rotation)
            }
        }

        fun getRotationX(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).rotationX
            else
                Honeycomb.getRotationX(view)

        fun setRotationX(view: View, rotationX: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).rotationX = rotationX
            } else {
                Honeycomb.setRotationX(view, rotationX)
            }
        }

        fun getRotationY(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).rotationY
            else
                Honeycomb.getRotationY(view)

        fun setRotationY(view: View, rotationY: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).rotationY = rotationY
            } else {
                Honeycomb.setRotationY(view, rotationY)
            }
        }


        fun getScaleX(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).scaleX
            else
                Honeycomb.getScaleX(view)

        fun setScaleX(view: View, scaleX: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).scaleX = scaleX
            } else {
                Honeycomb.setScaleX(view, scaleX)
            }
        }

        fun getScaleY(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).scaleY
            else
                Honeycomb.getScaleY(view)

        fun setScaleY(view: View, scaleY: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).scaleY = scaleY
            } else {
                Honeycomb.setScaleY(view, scaleY)
            }
        }

        fun getScrollX(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).scrollX.toFloat()
            else
                Honeycomb.getScrollX(view)

        fun setScrollX(view: View, scrollX: Int) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).scrollX = scrollX
            } else {
                Honeycomb.setScrollX(view, scrollX)
            }
        }

        fun getScrollY(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).scrollY.toFloat()
            else
                Honeycomb.getScrollY(view)

        fun setScrollY(view: View, scrollY: Int) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).scrollY = scrollY
            } else {
                Honeycomb.setScrollY(view, scrollY)
            }
        }

        fun getTranslationX(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).translationX
            else
                Honeycomb.getTranslationX(view)

        fun setTranslationX(view: View, translationX: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).translationX = translationX
            } else {
                Honeycomb.setTranslationX(view, translationX)
            }
        }

        fun getTranslationY(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).translationY
            else
                Honeycomb.getTranslationY(view)

        fun setTranslationY(view: View, translationY: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).translationY = translationY
            } else {
                Honeycomb.setTranslationY(view, translationY)
            }
        }

        fun getX(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).x
            else
                Honeycomb.getX(view)

        fun setX(view: View, x: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).x = x
            } else {
                Honeycomb.setX(view, x)
            }
        }

        fun getY(view: View): Float =
            if (AnimatorProxy.NEEDS_PROXY)
                AnimatorProxy.wrap(view).y
            else
                Honeycomb.getY(view)

        fun setY(view: View, y: Float) {
            if (AnimatorProxy.NEEDS_PROXY) {
                AnimatorProxy.wrap(view).y = y
            } else {
                Honeycomb.setY(view, y)
            }
        }
    }

    class Honeycomb {
        companion object {
            internal fun getAlpha(view: View): Float {
                return view.alpha
            }

            internal fun setAlpha(view: View, alpha: Float) {
                view.alpha = alpha
            }

            internal fun getPivotX(view: View): Float {
                return view.pivotX
            }

            internal fun setPivotX(view: View, pivotX: Float) {
                view.pivotX = pivotX
            }

            internal fun getPivotY(view: View): Float {
                return view.pivotY
            }

            internal fun setPivotY(view: View, pivotY: Float) {
                view.pivotY = pivotY
            }

            internal fun getRotation(view: View): Float {
                return view.rotation
            }

            internal fun setRotation(view: View, rotation: Float) {
                view.rotation = rotation
            }

            internal fun getRotationX(view: View): Float {
                return view.rotationX
            }

            internal fun setRotationX(view: View, rotationX: Float) {
                view.rotationX = rotationX
            }

            internal fun getRotationY(view: View): Float {
                return view.rotationY
            }

            internal fun setRotationY(view: View, rotationY: Float) {
                view.rotationY = rotationY
            }

            internal fun getScaleX(view: View): Float {
                return view.scaleX
            }

            internal fun setScaleX(view: View, scaleX: Float) {
                view.scaleX = scaleX
            }

            internal fun getScaleY(view: View): Float {
                return view.scaleY
            }

            internal fun setScaleY(view: View, scaleY: Float) {
                view.scaleY = scaleY
            }

            internal fun getScrollX(view: View): Float {
                return view.scrollX.toFloat()
            }

            internal fun setScrollX(view: View, scrollX: Int) {
                view.scrollX = scrollX
            }

            internal fun getScrollY(view: View): Float {
                return view.scrollY.toFloat()
            }

            internal fun setScrollY(view: View, scrollY: Int) {
                view.scrollY = scrollY
            }

            internal fun getTranslationX(view: View): Float {
                return view.translationX
            }

            internal fun setTranslationX(view: View, translationX: Float) {
                view.translationX = translationX
            }

            internal fun getTranslationY(view: View): Float {
                return view.translationY
            }

            internal fun setTranslationY(view: View, translationY: Float) {
                view.translationY = translationY
            }

            internal fun getX(view: View): Float {
                return view.x
            }

            internal fun setX(view: View, x: Float) {
                view.x = x
            }

            internal fun getY(view: View): Float {
                return view.y
            }

            internal fun setY(view: View, y: Float) {
                view.y = y
            }
        }

    }
}