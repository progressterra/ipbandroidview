package com.progressterra.ipbandroidview.ui.cart

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.CartUseCase
import com.progressterra.ipbandroidview.domain.usecase.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.ext.removeItem
import com.progressterra.ipbandroidview.ext.replaceById
import com.progressterra.ipbandroidview.model.Goods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CartViewModel(
    private val cartUseCase: CartUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val fastRemoveFromCartUseCase: FastRemoveFromCartUseCase
) : ViewModel(), ContainerHost<CartState, CartEffect>, CartInteractor {

    override val container: Container<CartState, CartEffect> = container(CartState())

    override fun favoriteSpecific(item: Goods) = intent {
        modifyFavoriteUseCase.modifyFavorite(item.id, item.favorite).onSuccess {
            reduce {
                state.copy(
                    cart = state.cart.copy(
                        listGoods = state.cart.listGoods.replaceById(
                            item.copy(
                                favorite = !item.favorite
                            )
                        )
                    )
                )
            }
        }
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        cartUseCase.cart().onSuccess {
            reduce { state.copy(cart = it, screenState = ScreenState.SUCCESS) }

        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun openDetails(item: Goods) = intent { postSideEffect(CartEffect.GoodsDetails(item)) }

    override fun next() = intent {
        postSideEffect(CartEffect.Next)
    }

    override fun removeSpecific(item: Goods) = intent {
        fastRemoveFromCartUseCase.remove(item.id, item.inCartCounter).onSuccess {
            val newListGoods = state.cart.listGoods.removeItem(item)
            val newCart = state.cart.copy(listGoods = newListGoods)
            reduce {
                state.copy(cart = newCart)
            }
        }
    }

    override fun auth() = intent {
        postSideEffect(CartEffect.Auth)
    }
}