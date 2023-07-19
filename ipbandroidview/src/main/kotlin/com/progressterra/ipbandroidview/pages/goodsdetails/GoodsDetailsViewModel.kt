package com.progressterra.ipbandroidview.pages.goodsdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonEvent
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
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class GoodsDetailsViewModel(
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val goodsDetailsUseCase: GoodsDetailsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val addToCartInstallmentUseCase: AddToCartInstallmentUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : ViewModel(), ContainerHost<GoodsDetailsState, GoodsDetailsEvent>,
    UseGoodsDetails {

    override val container = container<GoodsDetailsState, GoodsDetailsEvent>(GoodsDetailsState())

    fun setupId(newId: String) {
        blockingIntent {
            reduce { state.uId(newId) }
        }
    }

    fun refresh() {
        intent {
            reduce { state.uScreenState(ScreenState.LOADING) }
            goodsDetailsUseCase(state.id)
                .onSuccess { reduce { it.uId(state.id).uScreenState(ScreenState.SUCCESS) } }
                .onFailure { reduce { state.uScreenState(ScreenState.ERROR) } }
        }
    }

    override fun handle(event: ItemGalleryEvent) {
        intent {
            postSideEffect(GoodsDetailsEvent.OpenImage(event.image))
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(GoodsDetailsEvent.Back)
        }
    }

    override fun handle(event: FavoriteButtonEvent) {
        intent {
            modifyFavoriteUseCase(
                event.id,
                !state.description.favoriteButton.favorite
            ).onSuccess {
                postSideEffect(GoodsDetailsEvent.Toast(R.string.success))
                reduce { state.uDescriptionFavoriteButtonState(!state.description.favoriteButton.favorite) }
            }.onFailure {
                postSideEffect(GoodsDetailsEvent.Toast(R.string.failure))
            }
        }
    }

    override fun handle(event: StoreCardEvent) {
        intent {
            when (event) {
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                    postSideEffect(GoodsDetailsEvent.Toast(R.string.added_to_cart))
                }

                is StoreCardEvent.Open -> postSideEffect(GoodsDetailsEvent.GoodsDetails(event.id))
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "buy" -> addToCartUseCase(state.id).onSuccess {
                    postSideEffect(GoodsDetailsEvent.Toast(R.string.added_to_cart))
                }

                "buyInstallment" -> addToCartInstallmentUseCase(
                    state.id,
                    state.buyGoods.installment
                ).onSuccess {
                    postSideEffect(GoodsDetailsEvent.Toast(R.string.added_to_cart))
                }
            }
        }
    }

    override fun handle(event: CounterEvent) {
        intent {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                    postSideEffect(GoodsDetailsEvent.Toast(R.string.added_to_cart))
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                    refresh()
                    postSideEffect(GoodsDetailsEvent.Toast(R.string.removed_from_cart))

                }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }
}