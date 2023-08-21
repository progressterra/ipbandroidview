package com.progressterra.ipbandroidview.pages.bankcarddetails

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class BankCardDetailsScreenState(
    val apply: ButtonState,
    val document: Document,
    val screen: ScreenState
) : Parcelable