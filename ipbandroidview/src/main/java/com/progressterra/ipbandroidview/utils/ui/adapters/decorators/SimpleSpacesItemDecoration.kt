package com.progressterra.ipbandroidview.utils.ui.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.utils.extensions.dpToPx

/**
 * @param margin in dp
 * @param orientation of recycler
 * @see RecyclerOrientation
 */
class SimpleSpacesItemDecoration(
    private val margin: Int,
    private val orientation: RecyclerOrientation
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val pos = parent.getChildLayoutPosition(view)
//        pos != parent.childCount &&
        if (pos > 0) {
            when (orientation) {
                RecyclerOrientation.VERTICAL -> outRect.top = margin.dpToPx
                RecyclerOrientation.HORIZONTAL -> outRect.left = margin.dpToPx
            }
        }
    }
}

enum class RecyclerOrientation {
    HORIZONTAL, VERTICAL
}