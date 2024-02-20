package com.progressterra.ipbandroidview.pages.fer

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbshared.Expression
import com.progressterra.ipbshared.FaceLandmarks

@Immutable
data class FERState(
    val blendshapes: List<Blendshape> = emptyList(),
    val faceLandmarks: FaceLandmarks = FaceLandmarks(),
    val datasetDirPath: String = "",
    val tuningExpression: Expression = Expression.NEUTRAL,
    val tuneButton: ButtonState = ButtonState(id = "tune"),
    val tuneJoyButton: ButtonState = ButtonState(id = "tuneJoy"),
    val tuneFearButton: ButtonState = ButtonState(id = "tuneFear"),
    val tuneDisgustButton: ButtonState = ButtonState(id = "tuneDisgust"),
    val tuneAngryButton: ButtonState = ButtonState(id = "tuneAngry"),
    val tuneSurpriseButton: ButtonState = ButtonState(id = "tuneSurprise"),
    val tuneSadnessButton: ButtonState = ButtonState(id = "tuneSadness"),
    val tuningResult: String = "",
    val tuningMultiplier: TextFieldState = TextFieldState(id = "tuningMultiplier")
    )