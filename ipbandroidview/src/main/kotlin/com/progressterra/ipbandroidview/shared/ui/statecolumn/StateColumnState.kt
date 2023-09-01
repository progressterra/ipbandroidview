package com.progressterra.ipbandroidview.shared.ui.statecolumn

import android.os.Parcelable
import com.progressterra.ipbandroidview.entities.Id
import kotlinx.parcelize.Parcelize


@Parcelize
data class StateColumnState(
    override val id: String = "",
    val state: ScreenState = ScreenState.LOADING
) : Id, Parcelable
