package com.progressterra.ipbandroidview.utils.ui.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.utils.extensions.dpToPx


/**
 *  @param verticalSpacing in Dp
 *  @param horizontalSpacing in Dp
 */
class SimpleSpacesGridItemDecoration(
    private val spanCount: Int = 2,
    private val verticalSpacing: Int = 24,
    private val horizontalSpacing: Int = 24,
    private val includeEdge: Boolean = false
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position

        val column: Int = position % spanCount // item column

        if (includeEdge) {
            outRect.left =
                horizontalSpacing.dpToPx - column * horizontalSpacing.dpToPx / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
                (column + 1) * horizontalSpacing.dpToPx / spanCount // (column + 1) * ((1f / spanCount) * spacing)
            if (position < spanCount) { // top edge
                outRect.top = verticalSpacing.dpToPx
            }
            outRect.bottom = verticalSpacing.dpToPx // item bottom
        } else {
            outRect.left =
                column * horizontalSpacing.dpToPx / spanCount // column * ((1f / spanCount) * spacing)
            outRect.right =
                horizontalSpacing.dpToPx - (column + 1) * horizontalSpacing.dpToPx / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= spanCount) {
                outRect.top = verticalSpacing.dpToPx // item top
            }
        }
    }
}