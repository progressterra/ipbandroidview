package com.progressterra.ipbandroidview.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.GoodsGallery
import com.progressterra.ipbandroidview.composable.Qr
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.component.Bonuses
import com.progressterra.ipbandroidview.composable.component.BonusesState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun MainScreen(
    state: MainState, interactor: MainInteractor
) {
    ThemedLayout(topBar = {
        Column(
            modifier = Modifier.background(AppTheme.colors.surfaces),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
        ) {
            if (state.userExist) ThemedTopAppBar(title = stringResource(R.string.main))
        }
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(AppTheme.dimensions.small)
                    .verticalScroll(
                        rememberScrollState()
                    ), verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                Bonuses(
                    id = "main",
                    state = state.bonusesState,
                    useComponent = interactor
                )
                state.qr?.let { Qr(qr = it) }
                state.recommended.forEach { pair ->
                    GoodsGallery(modifier = Modifier,
                        goods = pair.second,
                        category = pair.first,
                        storeCardInteractor = interactor,
                        onFullCategory = { interactor.onCategory(it) })
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            state = MainState(
                screenState = ScreenState.SUCCESS,
                userExist = true,
                bonusesState = BonusesState(
                    bonuses = "4142", burningDate = "01.01.2021", burningQuantity = "100"
                ),
                qr = null,
                recommended = emptyList()
            ), interactor = MainInteractor.Empty()
        )
    }
}