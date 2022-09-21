package com.progressterra.ipbandroidview

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class AppTypography(
    val headLine: TextStyle = TextStyle(fontSize = 33.sp, fontWeight = FontWeight.SemiBold, lineHeight = 39.6.sp),
    val title: TextStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium, lineHeight = 24.sp),
    val text: TextStyle = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Normal, lineHeight = 20.4.sp),
    val secondaryText: TextStyle = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal, lineHeight = 18.sp),
    val tertiaryText: TextStyle = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Normal, lineHeight = 15.6.sp),
    val button: TextStyle = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.SemiBold, lineHeight = 20.4.sp),
    val actionBarLabels: TextStyle = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Medium, lineHeight = 12.sp),
)