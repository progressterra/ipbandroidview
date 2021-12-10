package com.progressterra.ipbandroidview.utils.ui.adapters.recycler

import android.os.Bundle
import android.os.Parcelable
import androidx.recyclerview.widget.RecyclerView

class ScrollStateHolder(savedInstanceState: Bundle? = null) {

    companion object {
        private const val STATE_BUNDLE = "_scroll_state_holder_bundle_key_"
    }

    /**
     * Provides a key that uniquely identifies a RecyclerView
     */
    interface ScrollStateKeyProvider {
        fun getScrollStateKey(): String?
    }


    /**
     * Persists the [RecyclerView.LayoutManager] states
     */
    private val scrollStates = hashMapOf<String, Parcelable>()

    /**
     * Keeps track of the keys that point to RecyclerViews
     * that have new scroll states that should be saved
     */
    private val scrolledKeys = mutableSetOf<String>()

    init {
        savedInstanceState?.getBundle(STATE_BUNDLE)?.let { bundle ->
            bundle.keySet().forEach { key ->
                bundle.getParcelable<Parcelable>(key)?.let {
                    scrollStates[key] = it
                }
            }
        }
    }

    fun setupRecyclerView(recyclerView: RecyclerView, scrollKeyProvider: ScrollStateKeyProvider) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    saveScrollState(recyclerView, scrollKeyProvider)
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val key = scrollKeyProvider.getScrollStateKey()
                if (key != null && dx != 0) {
                    scrolledKeys.add(key)
                }
            }
        })
    }

    fun onSaveInstanceState(outState: Bundle) {
        val stateBundle = Bundle()
        scrollStates.entries.forEach {
            stateBundle.putParcelable(it.key, it.value)
        }
        outState.putBundle(STATE_BUNDLE, stateBundle)
    }

    fun clearScrollState() {
        scrollStates.clear()
        scrolledKeys.clear()
    }

    /**
     * Saves this RecyclerView layout state for a given key
     */
    fun saveScrollState(
        recyclerView: RecyclerView,
        scrollKeyProvider: ScrollStateKeyProvider
    ) {
        val key = scrollKeyProvider.getScrollStateKey() ?: return
        // Check if we scrolled the RecyclerView for this key
        if (scrolledKeys.contains(key)) {
            val layoutManager = recyclerView.layoutManager ?: return
            layoutManager.onSaveInstanceState()?.let { scrollStates[key] = it }
            scrolledKeys.remove(key)
        }
    }

    /**
     * Restores this RecyclerView layout state for a given key
     */
    fun restoreScrollState(
        recyclerView: RecyclerView,
        scrollKeyProvider: ScrollStateKeyProvider
    ) {
        val key = scrollKeyProvider.getScrollStateKey() ?: return
        val layoutManager = recyclerView.layoutManager ?: return
        val savedState = scrollStates[key]
        if (savedState != null) {
            layoutManager.onRestoreInstanceState(savedState)
        } else {
            // If we don't have any state for this RecyclerView,
            // make sure we reset the scroll position
            layoutManager.scrollToPosition(0)
        }
        // Mark this key as not scrolled since we just restored the state
        scrolledKeys.remove(key)
    }

}