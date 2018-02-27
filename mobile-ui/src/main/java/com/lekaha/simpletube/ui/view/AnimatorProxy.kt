package com.lekaha.simpletube.ui.view

import android.graphics.Camera
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Build
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation

import java.lang.ref.WeakReference
import java.util.WeakHashMap

/**
 * A proxy class to allow for modifying post-3.0 view properties on all pre-3.0
 * platforms. **DO NOT** wrap your views with this class if you
 * are using `ObjectAnimator` as it will handle that itself.
 */
class AnimatorProxy private constructor(view: View) : Animation() {

    private val mView: WeakReference<View>
    private val mCamera = Camera()
    private var mHasPivot: Boolean = false

    var alpha = 1f
        set(alpha) {
            if (this.alpha != alpha) {
                field = alpha
                val view = mView.get()
                view?.invalidate()
            }
        }
    var pivotX: Float = 0.toFloat()
        set(pivotX) {
            if (!mHasPivot || this.pivotX != pivotX) {
                prepareForUpdate()
                mHasPivot = true
                field = pivotX
                invalidateAfterUpdate()
            }
        }
    var pivotY: Float = 0.toFloat()
        set(pivotY) {
            if (!mHasPivot || this.pivotY != pivotY) {
                prepareForUpdate()
                mHasPivot = true
                field = pivotY
                invalidateAfterUpdate()
            }
        }
    var rotationX: Float = 0.toFloat()
        set(rotationX) {
            if (this.rotationX != rotationX) {
                prepareForUpdate()
                field = rotationX
                invalidateAfterUpdate()
            }
        }
    var rotationY: Float = 0.toFloat()
        set(rotationY) {
            if (this.rotationY != rotationY) {
                prepareForUpdate()
                field = rotationY
                invalidateAfterUpdate()
            }
        }
    var rotation: Float = 0.toFloat()
        set(rotation) {
            if (this.rotation != rotation) {
                prepareForUpdate()
                field = rotation
                invalidateAfterUpdate()
            }
        }
    var scaleX = 1f
        set(scaleX) {
            if (this.scaleX != scaleX) {
                prepareForUpdate()
                field = scaleX
                invalidateAfterUpdate()
            }
        }
    var scaleY = 1f
        set(scaleY) {
            if (this.scaleY != scaleY) {
                prepareForUpdate()
                field = scaleY
                invalidateAfterUpdate()
            }
        }
    var translationX: Float = 0.toFloat()
        set(translationX) {
            if (this.translationX != translationX) {
                prepareForUpdate()
                field = translationX
                invalidateAfterUpdate()
            }
        }
    var translationY: Float = 0.toFloat()
        set(translationY) {
            if (this.translationY != translationY) {
                prepareForUpdate()
                field = translationY
                invalidateAfterUpdate()
            }
        }

    private val mBefore = RectF()
    private val mAfter = RectF()
    private val mTempMatrix = Matrix()
    var scrollX: Int
        get() {
            val view = mView.get() ?: return 0
            return view.scrollX
        }
        set(value) {
            val view = mView.get()
            view?.scrollTo(value, view.scrollY)
        }
    var scrollY: Int
        get() {
            val view = mView.get() ?: return 0
            return view.scrollY
        }
        set(value) {
            val view = mView.get()
            view?.scrollTo(view.scrollX, value)
        }
    var x: Float
        get() {
            val view = mView.get() ?: return 0f
            return view.left + translationX
        }
        set(x) {
            val view = mView.get()
            if (view != null) {
                translationX = x - view.left
            }
        }
    var y: Float
        get() {
            val view = mView.get() ?: return 0f
            return view.top + translationY
        }
        set(y) {
            val view = mView.get()
            if (view != null) {
                translationY = y - view.top
            }
        }

    init {
        duration = 0 //perform transformation immediately
        fillAfter = true //persist transformation beyond duration
        view.animation = this
        mView = WeakReference(view)
    }

    private fun prepareForUpdate() {
        val view = mView.get()
        if (view != null) {
            computeRect(mBefore, view)
        }
    }

    private fun invalidateAfterUpdate() {
        val view = mView.get()
        if (view == null || view.parent == null) {
            return
        }

        val after = mAfter
        computeRect(after, view)
        after.union(mBefore)

        (view.parent as View).invalidate(
            Math.floor(after.left.toDouble()).toInt(),
            Math.floor(after.top.toDouble()).toInt(),
            Math.ceil(after.right.toDouble()).toInt(),
            Math.ceil(after.bottom.toDouble()).toInt()
        )
    }

    private fun computeRect(r: RectF, view: View) {
        // compute current rectangle according to matrix transformation
        val w = view.width.toFloat()
        val h = view.height.toFloat()

        // use a rectangle at 0,0 to make sure we don't run into issues with scaling
        r.set(0f, 0f, w, h)

        val m = mTempMatrix
        m.reset()
        transformMatrix(m, view)
        mTempMatrix.mapRect(r)

        r.offset(view.left.toFloat(), view.top.toFloat())

        // Straighten coords if rotations flipped them
        if (r.right < r.left) {
            val f = r.right
            r.right = r.left
            r.left = f
        }
        if (r.bottom < r.top) {
            val f = r.top
            r.top = r.bottom
            r.bottom = f
        }
    }

    private fun transformMatrix(m: Matrix, view: View) {
        val w = view.width.toFloat()
        val h = view.height.toFloat()
        val hasPivot = mHasPivot
        val pX = if (hasPivot) pivotX else w / 2f
        val pY = if (hasPivot) pivotY else h / 2f

        val rX = rotationX
        val rY = rotationY
        val rZ = rotation
        if (rX != 0f || rY != 0f || rZ != 0f) {
            val camera = mCamera
            camera.save()
            camera.rotateX(rX)
            camera.rotateY(rY)
            camera.rotateZ(-rZ)
            camera.getMatrix(m)
            camera.restore()
            m.preTranslate(-pX, -pY)
            m.postTranslate(pX, pY)
        }

        val sX = scaleX
        val sY = scaleY
        if (sX != 1.0f || sY != 1.0f) {
            m.postScale(sX, sY)
            val sPX = -(pX / w) * (sX * w - w)
            val sPY = -(pY / h) * (sY * h - h)
            m.postTranslate(sPX, sPY)
        }

        m.postTranslate(translationX, translationY)
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val view = mView.get()
        if (view != null) {
            t.alpha = alpha
            transformMatrix(t.matrix, view)
        }
    }

    companion object {
        /** Whether or not the current running platform needs to be proxied.  */
        internal val NEEDS_PROXY =
            Integer.valueOf(Build.VERSION.SDK)!!.toInt() < Build.VERSION_CODES.HONEYCOMB

        private val PROXIES = WeakHashMap<View, AnimatorProxy>()

        /**
         * Create a proxy to allow for modifying post-3.0 view properties on all
         * pre-3.0 platforms. **DO NOT** wrap your views if you are
         * using `ObjectAnimator` as it will handle that itself.
         *
         * @param view View to wrap.
         * @return Proxy to post-3.0 properties.
         */
        internal fun wrap(view: View): AnimatorProxy {
            var proxy: AnimatorProxy? = PROXIES[view]
            // This checks if the proxy already exists and whether it still is the animation of the given view
            if (proxy == null || proxy != view.animation) {
                proxy = AnimatorProxy(view)
                PROXIES[view] = proxy
            }
            return proxy
        }
    }
}
