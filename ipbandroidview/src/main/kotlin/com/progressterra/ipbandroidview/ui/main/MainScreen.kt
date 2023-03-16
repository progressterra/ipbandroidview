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
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.features.ProshkaBonuses
import com.progressterra.ipbandroidview.features.ProshkaBonusesState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun MainScreen(
    state: MainState, interactor: MainInteractor
) {
    ThemedLayout(topBar = {
        Column(
            modifier = Modifier.background(IpbTheme.colors.surfaces),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (state.userExist) ThemedTopAppBar(title = stringResource(R.string.main))
        }
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(
                        rememberScrollState()
                    ), verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ProshkaBonuses(
                    id = "main",
                    state = state.extendedBonusesState,
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
    IpbTheme {
        MainScreen(
            state = MainState(
                screenState = ScreenState.SUCCESS,
                userExist = true,
                extendedBonusesState = ProshkaBonusesState(
                    bonuses = "4142", burningDate = "01.01.2021", burningQuantity = "100"
                ),
                qr = null,
                recommended = emptyList()
            ), interactor = MainInteractor.Empty()
        )
    }
}