package com.progressterra.ipbandroidview.shared.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

fun Color.toBrush() = SolidColor(this)

fun String.toColor() = Color(android.graphics.Color.parseColor(this))
