package com.progressterra.ipbandroidview.ui.bonuses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.BonusesClarification
import com.progressterra.ipbandroidview.composable.component.BonusesTransaction
import com.progressterra.ipbandroidview.composable.component.BonusesWidget
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
import com.progressterra.ipbandroidview.composable.component.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.element.StateBox
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BonusesScreen(
    state: () -> BonusesState,
    clarification: () -> Unit,
    back: () -> Unit,
    refresh: () -> Unit
) {
    ThemedLayout(
        topBar = { ThemedTopAppBar(title = stringResource(R.string.bonuses_title), onBack = back) }
    ) { _, _ ->
        StateBox(
            state = state()::screenState,
            refresh = refresh
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AppTheme.dimensions.small),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                BonusesWidget(
                    bonuses = state().bonusesInfo::quantity
                )
                BonusesClarification(
                    burningDate = state().bonusesInfo::burningDate,
                    burningQuantity = state().bonusesInfo::forBurningQuantity,
                    onClick = clarification
                )
                LazyColumn(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
                    items(state().transactions) {
                        BonusesTransaction(
                            state = { it }
                        )
                    }
                }
            }
        }
    }
}