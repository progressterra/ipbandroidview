package com.progressterra.ipbandroidview.ui.goodsdetails

import com.progressterra.ipbandroidview.model.store.GoodsColor
import com.progressterra.ipbandroidview.model.store.GoodsSize

interface GoodsDetailsInteractor {

    fun refresh()

    fun add()

    fun remove()

    fun favorite()

    fun chooseSize(size: GoodsSize)

    fun onBack()

    fun chooseColor(color: GoodsColor)

    class Empty : GoodsDetailsInteractor {

        override fun refresh() = Unit

        override fun add() = Unit

        override fun remove() = Unit

        override fun favorite() = Unit

        override fun chooseSize(size: GoodsSize) = Unit

        override fun onBack() = Unit

        override fun chooseColor(color: GoodsColor) = Unit
    }
}