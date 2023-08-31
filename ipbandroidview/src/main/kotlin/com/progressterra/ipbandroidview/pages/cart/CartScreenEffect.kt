package com.progressterra.ipbandroidview.pages.cart

sealed class CartScreenEffect {

    class OnItem(val data: String) : CartScreenEffect()

    data object Payment : CartScreenEffect()
}
