package com.progressterra.ipbandroidview.ui.bonuses

import androidx.compose.runtime.Composable

@Composable
fun BonusesScreen(
//    state: BonusesState,
//    interactor: BonusesInteractor
) {
//    ThemedLayout(
//        topBar = {
//            ThemedTopAppBar(
//                title = stringResource(R.string.bonuses_title),
//                onBack = { interactor.onBack() })
//        }
//    ) { _, _ ->
//        StateBox(
//            state = state.screenState,
//            refresh = { interactor.refresh() }
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(AppTheme.dimensions.small),
//                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
//            ) {
//                BonusesWidget(
//                    bonuses = state.bonusesInfo.bonuses
//                )
//                BonusesClarification(
//                    burningDate = state.bonusesInfo.burningDate,
//                    burningQuantity = state.bonusesInfo.burningQuantity,
//                    onClick = { interactor.onClarification() }
//                )
//                LazyColumn(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
//                    items(state.transactions) {
//                        BonusesTransaction(
//                            state = it
//                        )
//                    }
//                }
//            }
//        }
//    }
}