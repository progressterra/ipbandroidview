package com.progressterra.ipbandroidview.ui.cart

import androidx.compose.runtime.Composable

@Composable
fun CartScreen(
    state: CartState,
    interactor: CartInteractor
) {
//    ThemedLayout(topBar = {
//        ThemedTopAppBar(title = stringResource(id = R.string.cart))
//    }, bottomBar = {
//        if (state.cart.listGoods.isNotEmpty())
//            CartBottomComponent(
//                onNext = { interactor.onNext() },
//                onAuth = { interactor.onAuth() },
//                userExist = state.userExist,
//                totalPrice = state.cart.totalPrice
//            )
//    }) { _, _ ->
//        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
//            LazyColumn(
//                modifier = Modifier.fillMaxSize(),
//                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small),
//                contentPadding = PaddingValues(AppTheme.dimensions.small)
//            ) {
//                items(state.cart.listGoods) { cartGoods ->
//                    CartCard(
//                        state = cartGoods,
//                        interactor = interactor
//                    )
//                }
//            }
//        }
//    }
}