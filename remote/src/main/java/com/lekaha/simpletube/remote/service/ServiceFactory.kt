package com.lekaha.simpletube.remote.service

/**
 * ServiceFactory
 */
interface ServiceFactory<out S> {
    fun makeService(isDebug: Boolean): S
}