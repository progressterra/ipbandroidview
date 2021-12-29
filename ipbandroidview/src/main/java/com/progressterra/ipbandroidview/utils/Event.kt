package com.progressterra.ipbandroidview.utils

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
@Deprecated(message = "just because", replaceWith = ReplaceWith("SResult"))
class Event<T>(content: T?) {
    private val mContent: T
    private var hasBeenHandled = false


    val contentIfNotHandled: T?
        get() = if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            mContent
        }

    fun hasBeenHandled(): Boolean {
        return hasBeenHandled
    }

    init {
        requireNotNull(content) { "null values in Event are not allowed." }
        mContent = content
    }
}