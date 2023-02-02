package com.progressterra.ipbandroidview.ui.main

import com.progressterra.ipbandroidview.composable.NotificationsState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.bonuses.BonusesInfo
import com.progressterra.ipbandroidview.model.store.Category
import com.progressterra.ipbandroidview.model.store.StoreGoods
import com.progressterra.ipbandroidview.model.store.StoreNotification

data class MainState(
    val recommended: List<Pair<Category, List<StoreGoods>>> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val bonuses: BonusesInfo = BonusesInfo(),
    val userExist: Boolean = false,
    override val notifications: List<StoreNotification> = emptyList()
) : NotificationsState