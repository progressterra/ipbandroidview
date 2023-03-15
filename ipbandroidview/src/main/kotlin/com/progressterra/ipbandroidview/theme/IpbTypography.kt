package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class IpbTypography(
    val headLine: TextStyle = TextStyle(
        fontSize = 33.sp, fontWeight = FontWeight.Bold, lineHeight = 39.6.sp
    ),
    val title: TextStyle = TextStyle(
        fontSize = 20.sp, fontWeight = FontWeight.Bold, lineHeight = 24.sp
    ),
    val text: TextStyle = TextStyle(
        fontSize = 17.sp, fontWeight = FontWeight.Normal, lineHeight = 20.4.sp
    ),
    val button: TextStyle = TextStyle(
        fontSize = 17.sp, fontWeight = FontWeight.Bold, lineHeight = 20.4.sp
    ),
    val label: TextStyle = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 12.sp,
        letterSpacing = (-0.2).sp
    ),
    val secondaryText: TextStyle = TextStyle(
        fontSize = 15.sp, fontWeight = FontWeight.Normal, lineHeight = 18.sp
    ),
    val secondaryItalic: TextStyle = secondaryText.copy(
        fontStyle = FontStyle.Italic, fontWeight = FontWeight.Bold
    ),
    val secondaryBold: TextStyle = secondaryText.copy(
        fontWeight = FontWeight.Bold
    ),
    val tertiaryText: TextStyle = TextStyle(
        fontSize = 13.sp, fontWeight = FontWeight.Normal, lineHeight = 15.6.sp
    ),
    val tertiaryBold: TextStyle = tertiaryText.copy(
        fontWeight = FontWeight.Bold
    ),
)