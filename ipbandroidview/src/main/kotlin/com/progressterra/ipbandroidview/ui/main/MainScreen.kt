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
import com.progressterra.ipbandroidview.composable.Notifications
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun MainScreen(
    state: MainState,
    interactor: MainInteractor
) {
    ThemedLayout(topBar = {
        Column(
            modifier = Modifier.background(AppTheme.colors.surfaces),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.tiny)
        ) {
            if (state.userExist)
                ThemedTopAppBar(title = stringResource(R.string.main))
        }
    }) { _, _ ->
        StateBox(
            state = state.screenState, refresh = { interactor.refresh() }
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = AppTheme.dimensions.small)
                    .verticalScroll(
                        rememberScrollState()
                    ),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                Notifications(state = state)
                state.recommended.forEach { pair ->
                    GoodsGallery(
                        modifier = Modifier,
                        goods = pair.second,
                        category = pair.first,
                        storeCardInteractor = interactor,
                        onFullCategory = { interactor.onCategory(it) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    AppTheme {}
}