package com.progressterra.ipbandroidview.shared.ui.statebox

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id
import kotlinx.parcelize.Parcelize

//TODO rename to StateColumnState
@Immutable
@Parcelize
data class StateBoxState(
    override val id: String = "",
    val state: ScreenState = ScreenState.LOADING
) : Id, Parcelable
