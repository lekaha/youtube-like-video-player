package com.lekaha.simpletube.ui

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import com.lekaha.simpletube.ui.ext.manager
import com.lekaha.simpletube.ui.ext.start
import com.lekaha.simpletube.ui.ext.transact

/**
 * Navigator is responsible for navigating as to a Fragment or a Activity
 * and dealing with transition behaviors e.g., transition animation.
 */
class Navigator constructor(var activity: BaseActivity) {

    /**
     * navigate to a Fragment with [AppCompatActivity] and the transactions
     * in [FragmentTransaction]
     */
    fun navigateTo(cls: Class<*>, block: (Intent.() -> Unit)? = null) {
        activity.start<BaseActivity> {
            setClass(activity, cls)
            block?.run { block() }
        }
    }

    /**
     * navigate to Activity with [AppCompatActivity]
     */
    fun navigateTo(transactions: FragmentTransaction.() -> Unit) {
        activity.transact{
            transactions()
        }
    }

    fun <T: BaseFragment> showUp(fragmentId: Int, bundle: Bundle?) {
        val fragment = findFragmentById(fragmentId) as T
        if (isFragmentAvailable(fragment)) {
            fragment.takeIf { it.arguments == null }
                ?.apply {
                    arguments = Bundle()
                }

            bundle?.let {
                fragment.arguments?.putAll(it)
            }

            fragment.showUp()
        }
    }

    fun <T: BaseFragment> pressedBackKey(fragmentId: Int, block: (T) -> Boolean): Boolean {
        val fragment = findFragmentById(fragmentId) as T
        if (isFragmentAvailable(fragment)) {
            return block(fragment)
        }

        return false
    }

    private fun findFragmentById(fragmentId: Int) =
        activity.manager {
            findFragmentById(fragmentId)
        }

    /**
     * Check if the fragment is ready to be notified of a new TvShow loaded.
     *
     * @return true if the Fragment instance is not null and is attached.
     */
    private fun isFragmentAvailable(fragment: Fragment?): Boolean {
        return fragment != null && fragment.isAdded
    }
}
