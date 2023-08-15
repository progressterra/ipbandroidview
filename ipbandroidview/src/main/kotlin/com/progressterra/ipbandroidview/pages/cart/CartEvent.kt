package com.progressterra.ipbandroidview.pages.cart

sealed class CartEvent {

    class OnItem(val id: String) : CartEvent()

    data object Payment : CartEvent()
}
