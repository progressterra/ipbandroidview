package com.progressterra.ipbandroidview.composable

import android.graphics.Rect
import android.view.View

fun View.isKeyboardOpen(): Boolean {
    val rect = Rect()
    getWindowVisibleDisplayFrame(rect);
    val screenHeight = rootView.height
    val keypadHeight = screenHeight - rect.bottom;
    return keypadHeight > screenHeight * 0.15
}