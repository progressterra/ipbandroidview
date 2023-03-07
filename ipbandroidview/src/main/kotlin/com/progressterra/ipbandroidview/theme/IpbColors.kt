package com.progressterra.ipbandroidview.theme

import androidx.compose.ui.graphics.Color
import com.progressterra.ipbandroidview.ext.fromHexToColor

data class IpbColors(
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val surface: Color,
    val onSurface: Color,
    val error: Color,
    val success: Color,
    val info: Color,
    val warning: Color,
    val iconPrimary: Color,
    val iconSecondary: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textButtonPrimary: Color,
    val textButtonSecondary: Color,
    val primaryPressed: Color,
    val primaryDisabled: Color,
    val secondaryPressed: Color,
    val secondaryDisabled: Color,
    val iconsPressed: Color,
    val textPressed: Color,
    val textDisabled: Color
) {

    constructor(map: Map<String, String>) : this(
        background = map["background"]!!.fromHexToColor(),
        onBackground = map["onBackground"]!!.fromHexToColor(),
        primary = map["primary"]!!.fromHexToColor(),
        secondary = map["secondary"]!!.fromHexToColor(),
        tertiary = map["tertiary"]!!.fromHexToColor(),
        surface = map["surface"]!!.fromHexToColor(),
        onSurface = map["onSurface"]!!.fromHexToColor(),
        error = map["error"]!!.fromHexToColor(),
        success = map["success"]!!.fromHexToColor(),
        info = map["info"]!!.fromHexToColor(),
        warning = map["warning"]!!.fromHexToColor(),
        iconPrimary = map["iconPrimary"]!!.fromHexToColor(),
        iconSecondary = map["iconSecondary"]!!.fromHexToColor(),
        textPrimary = map["textPrimary"]!!.fromHexToColor(),
        textSecondary = map["textSecondary"]!!.fromHexToColor(),
        textButtonPrimary = map["textButtonPrimary"]!!.fromHexToColor(),
        textButtonSecondary = map["textButtonSecondary"]!!.fromHexToColor(),
        primaryPressed = map["primaryPressed"]!!.fromHexToColor(),
        secondaryPressed = map["secondaryPressed"]!!.fromHexToColor(),
        primaryDisabled = map["primaryDisabled"]!!.fromHexToColor(),
        secondaryDisabled = map["secondaryDisabled"]!!.fromHexToColor(),
        iconsPressed = map["iconsPressed"]!!.fromHexToColor(),
        textPressed = map["textPressed"]!!.fromHexToColor(),
        textDisabled = map["textDisabled"]!!.fromHexToColor()
    )
}
