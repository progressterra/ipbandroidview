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
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.BonusesClarification
import com.progressterra.ipbandroidview.composable.BonusesTransaction
import com.progressterra.ipbandroidview.composable.BonusesWidget
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.component.BonusesComponentState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.Transaction
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun BonusesScreen(
    state: BonusesState,
    interactor: BonusesInteractor
) {
    ThemedLayout(
        topBar = {
            ThemedTopAppBar(
                title = stringResource(R.string.bonuses_title),
                onBack = { interactor.onBack() })
        }
    ) { _, _ ->
        StateBox(
            state = state.screenState,
            refresh = { interactor.refresh() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AppTheme.dimensions.small),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                BonusesWidget(
                    bonuses = state.bonusesInfo.bonuses
                )
                BonusesClarification(
                    burningDate = state.bonusesInfo.burningDate,
                    burningQuantity = state.bonusesInfo.burningQuantity,
                    onClick = { interactor.onClarification() }
                )
                LazyColumn(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
                    items(state.transactions) {
                        BonusesTransaction(
                            state = it
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun BonusesScreenPreview() {
    AppTheme {
        BonusesScreen(
            state = BonusesState(
                bonusesInfo = BonusesComponentState("100"),
                transactions = listOf(
                    Transaction("01.01", "Зачисление", 300),
                    Transaction("03.01", "Зачисление", 300),
                    Transaction("06.01", "Снятие", -600)
                ),
                screenState = ScreenState.SUCCESS
            ),
            interactor = BonusesInteractor.Empty()
        )
    }
}