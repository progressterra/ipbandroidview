package com.progressterra.ipbandroidview.ui.main

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.composable.NotificationsState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.bonuses.BonusesInfo
import com.progressterra.ipbandroidview.model.store.StoreNotification
import com.progressterra.ipbandroidview.model.store.StoreGoods
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MainState(
    val items: Flow<PagingData<StoreGoods>> = emptyFlow(),
    val screenState: ScreenState = ScreenState.LOADING,
    val bonuses: BonusesInfo = BonusesInfo(),
    val userExist: Boolean = false,
    override val notifications: List<StoreNotification> = emptyList()
) : NotificationsState