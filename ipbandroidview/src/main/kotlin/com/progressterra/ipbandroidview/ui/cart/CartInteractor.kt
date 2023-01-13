package com.progressterra.ipbandroidview.ui.cart

import com.progressterra.ipbandroidview.model.store.CartGoods

interface CartInteractor {

    fun openDetails(goods: CartGoods)

    fun favoriteSpecific(goods: CartGoods)

    fun removeSpecific(goods: CartGoods)

    fun onNext()

    fun refresh()

    fun onAuth()

    class Empty: CartInteractor {

        override fun openDetails(goods: CartGoods) = Unit

        override fun favoriteSpecific(goods: CartGoods) = Unit

        override fun removeSpecific(goods: CartGoods) = Unit

        override fun onNext() = Unit

        override fun refresh() = Unit

        override fun onAuth() = Unit
    }
}