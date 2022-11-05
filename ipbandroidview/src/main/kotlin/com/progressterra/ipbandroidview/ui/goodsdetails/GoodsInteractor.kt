package com.progressterra.ipbandroidview.ui.goodsdetails

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.dto.GoodsColor
import com.progressterra.ipbandroidview.dto.size.GoodsSize

interface GoodsInteractor : Back {

    fun add()

    fun remove()

    fun favorite()

    fun color(color: GoodsColor)

    fun size(size: GoodsSize)

    fun sizeTable()

    class Empty : GoodsInteractor {

        override fun add() = Unit

        override fun remove() = Unit

        override fun favorite() = Unit

        override fun sizeTable() = Unit

        override fun size(size: GoodsSize) = Unit

        override fun back() = Unit

        override fun color(color: GoodsColor) = Unit
    }
}