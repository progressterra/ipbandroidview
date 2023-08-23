package com.progressterra.ipbandroidview.pages.bankcarddetails

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class BankCardDetailsScreenState(
    val apply: ButtonState = ButtonState(id = "apply"),
    val document: Document = Document(),
    val screen: StateColumnState = StateColumnState()
) : Parcelable