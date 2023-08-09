package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.favoritebutton.FavoriteButtonEvent
import com.progressterra.ipbandroidview.features.itemgallery.ItemGalleryEvent
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartInstallmentUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent

class GoodsDetailsViewModel(
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val goodsDetailsUseCase: GoodsDetailsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val addToCartInstallmentUseCase: AddToCartInstallmentUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : BaseViewModel<GoodsDetailsState, GoodsDetailsEvent>(),
    UseGoodsDetails {

    override fun createInitialState() = GoodsDetailsState()

    fun setupId(newId: String) {
        emitState {
            it.copy(id = newId)
        }
    }

    fun refresh() {
        onBackground {
            emitState {
                it.copy(screenState = ScreenState.LOADING)
            }
            goodsDetailsUseCase(currentState.id)
                .onSuccess { details ->
                    emitState {
                        details.copy(id = details.id, screenState = ScreenState.SUCCESS)
                    }
                }
                .onFailure {
                    emitState {
                        it.copy(screenState = ScreenState.ERROR)
                    }
                }
        }
    }

    override fun handle(event: ItemGalleryEvent) {
        postEffect(GoodsDetailsEvent.OpenImage(event.image))
    }

    override fun handle(event: TopBarEvent) {
        postEffect(GoodsDetailsEvent.Back)
    }

    override fun handle(event: FavoriteButtonEvent) {
        onBackground {
            modifyFavoriteUseCase(
                event.id,
                currentState.description.favoriteButton.favorite
            ).onSuccess {
                postEffect(GoodsDetailsEvent.Toast(R.string.added_to_favorites))
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
                postEffect(GoodsDetailsEvent.Toast(R.string.failure))
            }
        }
    }

    override fun handle(event: StoreCardEvent) {
        onBackground {
            when (event) {
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                    postEffect(GoodsDetailsEvent.Toast(R.string.added_to_cart))
                }

                is StoreCardEvent.Open -> postEffect(GoodsDetailsEvent.GoodsDetails(event.id))
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "buy" -> addToCartUseCase(currentState.id).onSuccess {
                    postEffect(GoodsDetailsEvent.Toast(R.string.added_to_cart))
                }

                "buyInstallment" -> addToCartInstallmentUseCase(
                    currentState.id,
                    currentState.buyGoods.installment
                ).onSuccess {
                    postEffect(GoodsDetailsEvent.Toast(R.string.added_to_cart))
                }
            }
        }
    }

    override fun handle(event: CounterEvent) {
        onBackground {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                    postEffect(GoodsDetailsEvent.Toast(R.string.added_to_cart))
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                    refresh()
                    postEffect(GoodsDetailsEvent.Toast(R.string.removed_from_cart))
                }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }
}