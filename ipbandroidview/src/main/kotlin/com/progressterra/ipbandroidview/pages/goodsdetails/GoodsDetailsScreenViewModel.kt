package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonEvent
import com.progressterra.ipbandroidview.features.goodsdescription.GoodsDescriptionEvent
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryEvent
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.goods.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.processes.goods.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartInstallmentUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class GoodsDetailsScreenViewModel(
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val goodsDetailsUseCase: GoodsDetailsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val addToCartInstallmentUseCase: AddToCartInstallmentUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : AbstractInputViewModel<String, GoodsDetailsScreenState, GoodsDetailsScreenEffect>(),
    UseGoodsDetailsScreen {

    override fun createInitialState() = GoodsDetailsScreenState()

    override fun setup(data: String) {
        emitState {
            it.copy(id = data)
        }
        refresh()
    }

    private fun refresh() {
        onBackground {
            emitState {
                it.copy(screen = it.screen.copy(state = ScreenState.LOADING))
            }
            goodsDetailsUseCase(currentState.id)
                .onSuccess { details ->
                    emitState {
                        details.copy(screen = it.screen.copy(state = ScreenState.SUCCESS))
                    }
                }
                .onFailure {
                    emitState {
                        it.copy(screen = it.screen.copy(state = ScreenState.ERROR))
                    }
                }
        }
    }

    override fun handle(event: GoodsDescriptionEvent) {
        postEffect(GoodsDetailsScreenEffect.Delivery)
    }

    override fun handle(event: ItemGalleryEvent) {
        postEffect(GoodsDetailsScreenEffect.OpenImage(event.image))
    }

    override fun handle(event: TopBarEvent) {
        postEffect(GoodsDetailsScreenEffect.Back)
    }

    override fun handle(event: FavoriteButtonEvent) {
        onBackground {
            modifyFavoriteUseCase(
                event.id,
                currentState.description.favoriteButton.favorite
            ).onSuccess {
                postEffect(GoodsDetailsScreenEffect.Toast(R.string.added_to_favorites))
                emitState {
                    it.copy(
                        description = it.description.copy(
                            favoriteButton = it.description.favoriteButton.copy(
                                favorite = !currentState.description.favoriteButton.favorite
                            )
                        )
                    )
                }
            }.onFailure {
                postEffect(GoodsDetailsScreenEffect.Toast(R.string.failure))
            }
        }
    }

    override fun handle(event: StoreCardEvent) {
        onBackground {
            when (event) {
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                    postEffect(GoodsDetailsScreenEffect.Toast(R.string.added_to_cart))
                }

                is StoreCardEvent.Open -> postEffect(GoodsDetailsScreenEffect.GoodsDetails(event.id))
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "buy" -> addToCartUseCase(currentState.id).onSuccess {
                    postEffect(GoodsDetailsScreenEffect.Toast(R.string.added_to_cart))
                }

                "buyInstallment" -> addToCartInstallmentUseCase(
                    currentState.id,
                    currentState.buyGoods.installment
                ).onSuccess {
                    postEffect(GoodsDetailsScreenEffect.Toast(R.string.added_to_cart))
                }
            }
        }
    }

    override fun handle(event: CounterEvent) {
        onBackground {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                    postEffect(GoodsDetailsScreenEffect.Toast(R.string.added_to_cart))
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                    refresh()
                    postEffect(GoodsDetailsScreenEffect.Toast(R.string.removed_from_cart))
                }
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}