package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.buygoods.BuyGoodsEvent
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonEvent
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionEvent
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryEvent
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
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
            .onFailure { reduce { state.uScreenState(ScreenState.ERROR) } }
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

    override fun handle(event: FavoriteButtonEvent) = intent {
        when (event) {
            is FavoriteButtonEvent.Click -> modifyFavoriteUseCase(
                event.id,
                !state.description.favoriteButton.favorite
            ).onSuccess {
                reduce { state.uDescriptionFavoriteButtonState(!state.description.favoriteButton.favorite) }
            }
        }
    }

    override fun handle(event: GoodsDescriptionEvent) {
        when (event) {
            is GoodsDescriptionEvent.Share -> TODO()
        }
    }

    override fun handle(event: BuyGoodsEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: StoreCardEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: ButtonEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: CounterEvent) {
        TODO("Not yet implemented")
    }
}