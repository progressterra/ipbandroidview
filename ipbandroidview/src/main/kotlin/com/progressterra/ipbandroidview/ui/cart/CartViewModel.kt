package com.progressterra.ipbandroidview.ui.cart

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class CartViewModel : ViewModel(), ContainerHost<CartState, CartEffect>, CartInteractor {

    override val container: Container<CartState, CartEffect> = container(CartState())

    override fun favorite(goodsId: String, favorite: Boolean) {
        TODO("Not yet implemented")
    }

    override fun refresh() {
        TODO("Not yet implemented")
    }
}