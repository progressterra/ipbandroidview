package com.progressterra.ipbandroidview.ui.main

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.composable.component.NotificationsState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.BonusesInfo
import com.progressterra.ipbandroidview.model.Notification
import com.progressterra.ipbandroidview.model.StoreGoods
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MainState(
    val items: Flow<PagingData<StoreGoods>> = emptyFlow(),
    val screenState: ScreenState = ScreenState.LOADING,
    val bonuses: BonusesInfo = BonusesInfo(),
    val userExist: Boolean = false,
    override val notifications: List<Notification> = emptyList()
) : NotificationsState