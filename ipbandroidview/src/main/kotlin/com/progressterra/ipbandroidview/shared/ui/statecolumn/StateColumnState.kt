package com.progressterra.ipbandroidview.shared.ui.statecolumn

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class StateColumnState(
    override val id: String = "",
    val state: ScreenState = ScreenState.LOADING
) : Id, Parcelable
