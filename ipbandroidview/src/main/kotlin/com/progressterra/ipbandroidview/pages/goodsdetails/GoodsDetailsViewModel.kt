package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonEvent
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionEvent
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryEvent
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartInstallmentUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class GoodsDetailsViewModel(
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val goodsDetailsUseCase: GoodsDetailsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val addToCartInstallmentUseCase: AddToCartInstallmentUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : ViewModel(), ContainerHost<GoodsDetailsState, GoodsDetailsEvent>,
    UseGoodsDetails {

    override val container = container<GoodsDetailsState, GoodsDetailsEvent>(GoodsDetailsState())

    fun refresh(goodsId: String) {
        intent {
            reduce { state.uScreenState(ScreenState.LOADING) }
            goodsDetailsUseCase(goodsId)
                .onSuccess { reduce { it.uId(goodsId).uScreenState(ScreenState.SUCCESS) } }
                .onFailure {
                    it.printStackTrace()
                    reduce { state.uScreenState(ScreenState.ERROR) }
                }
        }
    }

    override fun handle(event: ItemGalleryEvent) {
        intent {
            when (event) {
                is ItemGalleryEvent.Open -> postSideEffect(GoodsDetailsEvent.OpenImage(event.image))
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(GoodsDetailsEvent.Back)
        }
    }

    override fun handle(event: FavoriteButtonEvent) {
        intent {
            when (event) {
                is FavoriteButtonEvent.Click -> modifyFavoriteUseCase(
                    event.id,
                    !state.description.favoriteButton.favorite
                ).onSuccess {
                    reduce { state.uDescriptionFavoriteButtonState(!state.description.favoriteButton.favorite) }
                }
            }
        }
    }

    override fun handle(event: GoodsDescriptionEvent) {
        intent {
            when (event) {
                is GoodsDescriptionEvent.Share -> Unit
            }
        }
    }

    override fun handle(event: StoreCardEvent) {
        intent {
            when (event) {
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id)

                is StoreCardEvent.Open -> postSideEffect(GoodsDetailsEvent.GoodsDetails(event.id))
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "buy" -> addToCartUseCase(state.id)
                "buyInstallment" -> addToCartInstallmentUseCase(
                    state.id,
                    state.buyGoods.installment
                )
            }
        }
    }

    override fun handle(event: CounterEvent) {
        intent {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                    refresh(state.id)
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                    refresh(state.id)
                }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            postSideEffect(GoodsDetailsEvent.Refresh)
        }
    }
}