package com.progressterra.ipbandroidview.ui.cart

import com.progressterra.ipbandroidview.composable.component.CartCardInteractor
import com.progressterra.ipbandroidview.composable.component.CartCardState

interface CartInteractor : CartCardInteractor {

    fun onNext()

    fun refresh()

    fun onAuth()

    class Empty : CartInteractor {

        override fun favorite(cartCard: CartCardState) = Unit

        override fun delete(cartCard: CartCardState) = Unit

        override fun onDetails(cartCard: CartCardState) = Unit

        override fun onNext() = Unit

        override fun refresh() = Unit

        override fun onAuth() = Unit
    }
}