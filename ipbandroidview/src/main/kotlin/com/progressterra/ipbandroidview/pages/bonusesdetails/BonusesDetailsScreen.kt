package com.progressterra.ipbandroidview.pages.bonusesdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.bonuses.Bonuses
import com.progressterra.ipbandroidview.features.bonuses.BonusesStyle
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionState
import com.progressterra.ipbandroidview.features.bonustransaction.BonusTransactionType
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
import com.progressterra.ipbandroidview.widgets.bonusestransactions.BonusesTransactions
import com.progressterra.ipbandroidview.widgets.bonusestransactions.BonusesTransactionsState

@Composable
fun BonusesScreen(
    state: BonusesDetailsState,
    useComponent: UseBonusesDetails
) {
    ThemedLayout(
        topBar = {
            TopBar(
                title = stringResource(R.string.bonuses_title),
                useComponent = useComponent,
                showBackButton = true
            )
        }
    ) { _, _ ->
        StateColumn(
            state = state.screenState, useComponent = useComponent,
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Bonuses(
                modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
                state = state.bonusesInfo,
                useComponent = useComponent,
                style = BonusesStyle.TRAN
            )
            BonusesTransactions(state = state.transactions)
        }
    }
}

@Composable
@Preview
private fun BonusesScreenPreview() {
    BonusesScreen(
        state = BonusesDetailsState(
            screenState = ScreenState.SUCCESS,
            transactions = BonusesTransactionsState(
                transactions = listOf(
                    BonusTransactionState(
                        date = "2021-01-01",
                        amount = "100",
                        type = BonusTransactionType.BURNING
                    ),
                    BonusTransactionState(
                        date = "2021-01-02",
                        amount = "200",
                        type = BonusTransactionType.BUYING
                    ),
                    BonusTransactionState(
                        date = "2021-01-03",
                        amount = "300",
                        type = BonusTransactionType.BURNING
                    ),
                    BonusTransactionState(
                        date = "2021-01-04",
                        amount = "400",
                        type = BonusTransactionType.RECEIVING
                    )
                )
            )
        ),
        useComponent = UseBonusesDetails.Empty()
    )
}

@Composable
@Preview
private fun BonusesScreenPreviewEmpty() {
    BonusesScreen(
        state = BonusesDetailsState(
            screenState = ScreenState.SUCCESS,
            transactions = BonusesTransactionsState(
                transactions = emptyList()
            )
        ),
        useComponent = UseBonusesDetails.Empty()
    )
}