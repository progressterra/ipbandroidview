package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ScreenState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class GoodsDetailsViewModel(
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val goodsDetailsUseCase: GoodsDetailsUseCase
) : ViewModel(), ContainerHost<GoodsDetailsState, GoodsDetailsEvent>,
    UseGoodsDetails {

    override val container = container<GoodsDetailsState, GoodsDetailsEvent>(GoodsDetailsState())

    fun refresh(goodsId: String) = intent {
        reduce { GoodsDetailsState() }
        goodsDetailsUseCase(goodsId)
            .onSuccess { reduce { it } }
            .onFailure { reduce { state.updateScreenState(ScreenState.ERROR) } }
    }

    override fun handle(event: ItemGalleryEvent) = intent {
        when (event) {
            is ItemGalleryEvent.Open -> postSideEffect(GoodsDetailsEvent.OpenImage(event.image))
        }
    }

    override fun handle(event: TopBarEvent) = intent {
        when (event) {
            is TopBarEvent.Back -> postSideEffect(GoodsDetailsEvent.Back)
        }
    }
}