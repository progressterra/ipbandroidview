package com.progressterra.ipbandroidview.ui.goodsdetails

interface GoodsDetailsInteractor {

    fun refresh()

    fun add()

    fun remove()

    fun favorite()

//    fun chooseSize(size: GoodsSize)

    fun onBack()


    class Empty : GoodsDetailsInteractor {

        override fun refresh() = Unit

        override fun add() = Unit

        override fun remove() = Unit

        override fun favorite() = Unit

//        override fun chooseSize(size: GoodsSize) = Unit

        override fun onBack() = Unit
    }
}