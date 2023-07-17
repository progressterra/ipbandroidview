package com.progressterra.ipbandroidview.shared.theme

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.reflection.Copyable
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.full.memberFunctions

@Immutable
data class IpbColors(
    // Main
    val primary: ColorUnit = ColorUnit(listOf("#35C290", "#2E9399")),
    val secondary: ColorUnit = ColorUnit(listOf("#3E4555")),
    val secondary2: ColorUnit = ColorUnit(listOf("#CDCDD6")),
    val tertiary: ColorUnit = ColorUnit(listOf("#B5B5BC")),
    val background: ColorUnit = ColorUnit(listOf("#F2F5FF")),
    val onBackground: ColorUnit = ColorUnit(listOf("#2E8E6C")),
    val surface: ColorUnit = ColorUnit(listOf("#FFFFFF")),
    val surface2: ColorUnit = ColorUnit(listOf("#111111")),
    val onSurface: ColorUnit = ColorUnit(listOf("#FFFFFF")),
    val onSurface2: ColorUnit = ColorUnit(listOf("#101010")),
    val primaryPressed: ColorUnit = ColorUnit(listOf("#3D3D3D")),
    val primaryDisabled: ColorUnit = ColorUnit(listOf("#E4E4F0")),
    val secondaryPressed: ColorUnit = ColorUnit(listOf("#232427")),
    // Status
    val error: ColorUnit = ColorUnit(listOf("#DF3636")),
    val success: ColorUnit = ColorUnit(listOf("#7ADB6B")),
    val info: ColorUnit = ColorUnit(listOf("#6980CF")),
    val warning: ColorUnit = ColorUnit(listOf("#DB742A")),
    // Text
    val textPrimary: ColorUnit = ColorUnit(listOf("#111111")),
    val textPrimary2: ColorUnit = ColorUnit(listOf("#E82741")),
    val textSecondary: ColorUnit = ColorUnit(listOf("#6E7289")),
    val textTertiary: ColorUnit = ColorUnit(listOf("#9191A1")),
    val textTertiary2: ColorUnit = ColorUnit(listOf("#453896")),
    val textTertiary3: ColorUnit = ColorUnit(listOf("#28AB13")),
    val textTertiary4: ColorUnit = ColorUnit(listOf("#CA451C")),
    val textButton: ColorUnit = ColorUnit(listOf("#FFFFFF")),
    val textDisabled: ColorUnit = ColorUnit(listOf("#B5B5B5")),
    val textPressed: ColorUnit = ColorUnit(listOf("#24282E")),
    // Icons
    val iconPrimary: ColorUnit = ColorUnit(listOf("#111111")),
    val iconPrimary2: ColorUnit = ColorUnit(listOf("#E82741")),
    val iconSecondary: ColorUnit = ColorUnit(listOf("#FFFFFF")),
    val iconTertiary: ColorUnit = ColorUnit(listOf("#B5B5BC")),
    val iconTertiary2: ColorUnit = ColorUnit(listOf("#4578DC", "#453896")),
    val iconTertiary3: ColorUnit = ColorUnit(listOf("#B2FF75", "#28AB13")),
    val iconTertiary4: ColorUnit = ColorUnit(listOf("#F6E651", "#B80707")),
    val iconPressed: ColorUnit = ColorUnit(listOf("#0F1215")),
    val iconDisabled: ColorUnit = ColorUnit(listOf("#B5B5B5"))
) : Copyable<IpbColors> {

    override fun copy(key: String, values: List<String>): IpbColors {
        val copy = this::class.memberFunctions.first { it.name == "copy" }
        val instanceParameter = copy.instanceParameter!!
        val parameterToBeUpdated = copy.parameters.first { it.name == key }
        val valueToUpdate = ColorUnit(values)
        return copy.callBy(
            mapOf(
                instanceParameter to this,
                parameterToBeUpdated to valueToUpdate
            )
        ) as IpbColors
    }
}

val defaultIpbLightColors = IpbColors()

val defaultIpbDarkColors = IpbColors()