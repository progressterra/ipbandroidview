package com.progressterra.ipbandroidview.ui.cart

import com.progressterra.ipbandroidview.composable.component.CartCardComponentInteractor
import com.progressterra.ipbandroidview.composable.component.CartCardComponentState

interface CartInteractor : CartCardComponentInteractor {

    fun onNext()

    fun refresh()

    fun onAuth()

    class Empty: CartInteractor {

        override fun favorite(cartCard: CartCardComponentState) = Unit

        override fun delete(cartCard: CartCardComponentState) = Unit

        override fun onDetails(cartCard: CartCardComponentState) = Unit

        override fun onNext() = Unit

        override fun refresh() = Unit

        override fun onAuth() = Unit
    }
}