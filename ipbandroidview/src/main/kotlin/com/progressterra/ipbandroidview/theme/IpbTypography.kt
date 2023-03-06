package com.progressterra.ipbandroidview.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class IpbTypography(
    val largeTitle: TextStyle = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 41.sp,
        letterSpacing = 0.37.sp
    ),
    val title1: TextStyle = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 34.sp,
        letterSpacing = 0.36.sp
    ),
    val title2: TextStyle = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 28.sp,
        letterSpacing = 0.35.sp
    ),
    val title3: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.38.sp
    ),
    val headline: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.sp,
        letterSpacing = (-0.41).sp
    ),
    val body: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp,
        letterSpacing = (-0.41).sp
    ),
    val callout: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
        letterSpacing = (-0.32).sp
    ),
    val subheadline: TextStyle = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        letterSpacing = (-0.24).sp
    ),
    val footnote: TextStyle = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 18.sp,
        letterSpacing = (-0.08).sp
    ),
    val caption1: TextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 16.sp
    ),
    val caption2: TextStyle = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 13.sp,
        letterSpacing = 0.07.sp
    )
)