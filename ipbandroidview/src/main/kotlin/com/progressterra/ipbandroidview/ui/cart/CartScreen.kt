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
import com.progressterra.ipbandroidview.composable.CartBottomBar
import com.progressterra.ipbandroidview.composable.component.CartCardComponent
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CartScreen(
    state: CartState,
    interactor: CartInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.cart))
    }, bottomBar = {
        if (state.cart.listGoods.isNotEmpty())
            CartBottomBar(
                onNext = { interactor.onNext() },
                onAuth = { interactor.onAuth() },
                userExist = state.userExist,
                totalPrice = state.cart.totalPrice
            )
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
                contentPadding = PaddingValues(AppTheme.dimensions.small)
            ) {
                items(state.cart.listGoods) { cartGoods ->
                    CartCardComponent(
                        state = cartGoods,
                        interactor = interactor
                    )
                }
            }
        }
    }
}