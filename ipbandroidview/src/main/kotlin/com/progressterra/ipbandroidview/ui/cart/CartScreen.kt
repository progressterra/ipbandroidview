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
import com.progressterra.ipbandroidview.components.bottombar.CartBottomBar
import com.progressterra.ipbandroidview.components.topbar.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CartScreen(state: CartState, interactor: CartInteractor) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.cart))
    }, bottomBar = {
        CartBottomBar(state = state, onNext = { interactor.next() }, onAuth = { interactor.auth() })
    }) { _, _ ->
        StateBox(state = state, onRefresh = { interactor.refresh() }) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium),
                contentPadding = PaddingValues(AppTheme.dimensions.medium)
            ) {
                items(state.cart.listGoods) {
                    CartCard(
                        state = it,
                        onFavorite = { interactor.favoriteSpecific(it) },
                        onDelete = { interactor.removeSpecific(it) }) {

                    }
                }
            }
        }
    }
}