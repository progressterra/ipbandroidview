package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.dto.Goods
import com.progressterra.ipbandroidview.dto.GoodsColor
import com.progressterra.ipbandroidview.dto.size.GoodsSize
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class GoodsDetailsViewModel : ViewModel(),
    ContainerHost<GoodsDetailsScreenState, GoodsDetailsEffect>, GoodsDetailsInteractor {

    override val container: Container<GoodsDetailsScreenState, GoodsDetailsEffect> =
        container(GoodsDetailsScreenState())

    @Suppress("unused")
    fun setGoods(goods: Goods) = intent {
        reduce { state.copy(goods = goods) }
    }

    override fun back() {
        TODO("Not yet implemented")
    }

    override fun add() {
        TODO("Not yet implemented")
    }

    override fun remove() {
        TODO("Not yet implemented")
    }

    override fun favorite() {
        TODO("Not yet implemented")
    }

    override fun color(color: GoodsColor) {
        TODO("Not yet implemented")
    }

    override fun size(size: GoodsSize) {
        TODO("Not yet implemented")
    }

    override fun sizeTable() {
        TODO("Not yet implemented")
    }
}