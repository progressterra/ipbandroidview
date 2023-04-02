package com.progressterra.ipbandroidview.pages.bonuses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonuses
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.widgets.bonusestransactions.BonusesTransactions

@Composable
fun BonusesScreen(
    state: BonusesState,
    useComponent: UseBonuses
) {
    ThemedLayout(
        topBar = {
            ProshkaTopBar(
                title = stringResource(R.string.bonuses_title),
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.stateBoxState, useComponent = useComponent
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                ProshkaBonuses(
                    state = state.bonusesInfo,
                    useComponent = useComponent
                )
                BonusesTransactions(state = state.transactions)
            }
        }
    }
}