package com.progressterra.ipbandroidview.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.CartCard
import com.progressterra.ipbandroidview.components.StateBox
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.CartBottomBar
import com.progressterra.ipbandroidview.components.bar.ThemedTopAppBar
import com.progressterra.ipbandroidview.model.CartGoods
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CartScreen(
    state: () -> CartState,
    openDetails: (CartGoods) -> Unit,
    favoriteSpecific: (CartGoods) -> Unit,
    removeSpecific: (CartGoods) -> Unit,
    next: () -> Unit,
    refresh: () -> Unit,
    auth: () -> Unit
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.cart))
    }, bottomBar = {
        CartBottomBar(
            state = state,
            onNext = next,
            onAuth = auth,
            screenState = state()::screenState
        )
    }) { _, _ ->
        StateBox(state = state()::screenState, onRefresh = refresh) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                contentPadding = PaddingValues(AppTheme.dimensions.small)
            ) {
                items(state().cart.listGoods) {
                    CartCard(
                        state = { it },
                        onFavorite = { favoriteSpecific(it) },
                        onDelete = { removeSpecific(it) },
                        onDetails = { openDetails(it) }
                    )
                }
            }
        }
    }
}