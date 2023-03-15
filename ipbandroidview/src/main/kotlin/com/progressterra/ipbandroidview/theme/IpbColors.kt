package com.progressterra.ipbandroidview.theme

import androidx.compose.runtime.Immutable

@Immutable
data class IpbColors(
    // Main
    val primary: ColorUnit = ColorUnit("#35C290", "#2E9399"),
    val secondary1: ColorUnit = ColorUnit("#3E4555"),
    val secondary2: ColorUnit = ColorUnit("#CDCDD6"),
    val secondary3: ColorUnit = ColorUnit("#DCE8FF", "#FFFFFF"),
    val tertiary: ColorUnit = ColorUnit("#B5B5BC"),
    val background1: ColorUnit = ColorUnit("#F2F5FF"),
    val background2: ColorUnit = ColorUnit("#202128"),
    val onBackground1: ColorUnit = ColorUnit("#2E8E6C"),
    val onBackground2: ColorUnit = ColorUnit("#1A1A20"),
    val surface1: ColorUnit = ColorUnit("#FFFFFF"),
    val surface2: ColorUnit = ColorUnit("#111111"),
    val onSurface1: ColorUnit = ColorUnit("#FFFFFF"),
    val onSurface2: ColorUnit = ColorUnit("#101010"),
    val primaryPressed: ColorUnit = ColorUnit("#3D3D3D"),
    val primaryDisabled: ColorUnit = ColorUnit("#B5B5B5"),
    val secondaryPressed: ColorUnit = ColorUnit("#232427"),
    // Status
    val error: ColorUnit = ColorUnit("#DF3636"),
    val success: ColorUnit = ColorUnit("#7ADB6B"),
    val info: ColorUnit = ColorUnit("#6980CF"),
    val warning: ColorUnit = ColorUnit("#DB742A"),
    // Text
    val textPrimary1: ColorUnit = ColorUnit("#111111"),
    val textPrimary2: ColorUnit = ColorUnit("#E82741"),
    val textPrimary3: ColorUnit = ColorUnit("#35C290", "#2E9399"),
    val textSecondary: ColorUnit = ColorUnit("#6E7289"),
    val textTertiary1: ColorUnit = ColorUnit("#9191A1"),
    val textTertiary2: ColorUnit = ColorUnit("#453896"),
    val textTertiary3: ColorUnit = ColorUnit("#28AB13"),
    val textTertiary4: ColorUnit = ColorUnit("#CA451C"),
    val textButton: ColorUnit = ColorUnit("#FFFFFF"),
    val textDisabled: ColorUnit = ColorUnit("#B5B5B5"),
    val textPressed: ColorUnit = ColorUnit("#24282E"),
    // Icons
    val iconPrimary1: ColorUnit = ColorUnit("#111111"),
    val iconPrimary2: ColorUnit = ColorUnit("#E82741"),
    val iconPrimary3: ColorUnit = ColorUnit("#35C290", "#2E9399"),
    val iconSecondary: ColorUnit = ColorUnit("#FFFFFF"),
    val iconTertiary1: ColorUnit = ColorUnit("#B5B5BC"),
    val iconTertiary2: ColorUnit = ColorUnit("#4578DC", "#453896"),
    val iconTertiary3: ColorUnit = ColorUnit("#B2FF75", "#28AB13"),
    val iconTertiary4: ColorUnit = ColorUnit("#F6E651", "#B80707"),
    val iconTertiary5: ColorUnit = ColorUnit("#1A1A20"),
    val iconPressed: ColorUnit = ColorUnit("#0F1215"),
    val iconDisabled1: ColorUnit = ColorUnit("#B5B5B5"),
    val iconDisabled2: ColorUnit = ColorUnit("#EDF1FF")
)

val defaultIpbLightColors = IpbColors()

val defaultIpbDarkColors = IpbColors()