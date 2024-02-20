package com.progressterra.ipbandroidview.pages.fer

import java.util.Locale

data class Blendshape(
    val number: Int,
    val name: String,
    val value: Float
) {

    override fun toString() = "$number. $name - ${String.format(Locale.ENGLISH, "%.3f", value).toDouble()}"
}
