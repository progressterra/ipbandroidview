package com.progressterra.ipbandroidview.shared.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp


data class IpbTypography(
    val largeTitle: TextStyle = TextStyle(
        fontSize = 33.sp, fontWeight = FontWeight.Bold, lineHeight = 39.6.sp
    ),
    val title: TextStyle = TextStyle(
        fontSize = 20.sp, fontWeight = FontWeight.Bold, lineHeight = 24.sp
    ),
    val title2: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp
    ),
    val headline: TextStyle = TextStyle(
        fontSize = 17.sp, fontWeight = FontWeight.Bold, lineHeight = 20.4.sp
    ),
    val body: TextStyle = TextStyle(
        fontSize = 17.sp, fontWeight = FontWeight.Normal, lineHeight = 20.4.sp
    ),
    val body2: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.4.sp,
        textDecoration = TextDecoration.LineThrough
    ),
    val subHeadlineRegular: TextStyle = TextStyle(
        fontSize = 15.sp, fontWeight = FontWeight.Normal, lineHeight = 18.sp
    ),
    val subHeadlineItalic: TextStyle = subHeadlineRegular.copy(
        fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold
    ),
    val subHeadlineBold: TextStyle = subHeadlineRegular.copy(
        fontWeight = FontWeight.Bold
    ),
    val footnoteRegular: TextStyle = TextStyle(
        fontSize = 13.sp, fontWeight = FontWeight.Normal, lineHeight = 15.6.sp
    ),
    val footnoteBold: TextStyle = footnoteRegular.copy(
        fontWeight = FontWeight.Bold
    ),
    val caption: TextStyle = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 12.sp,
        letterSpacing = (-0.2).sp
    )
)