package com.progressterra.ipbandroidview.pages.bankcarddetails

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class BankCardDetailsScreenState(
    val apply: ButtonState = ButtonState(id = "apply"),
    val document: Document = Document(),
    val screen: StateBoxState = StateBoxState()
) : Parcelable