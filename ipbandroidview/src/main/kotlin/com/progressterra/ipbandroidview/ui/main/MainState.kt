package com.progressterra.ipbandroidview.ui.main

import com.progressterra.ipbandroidview.composable.NotificationsState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.BonusesInfo
import com.progressterra.ipbandroidview.model.Category
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.model.StoreNotification

data class MainState(
    val recommended: List<Pair<Category, List<StoreCardComponentState>>> = emptyList(),
    val screenState: ScreenState = ScreenState.LOADING,
    val bonuses: BonusesInfo = BonusesInfo(),
    val userExist: Boolean = false,
    override val notifications: List<StoreNotification> = emptyList()
) : NotificationsState