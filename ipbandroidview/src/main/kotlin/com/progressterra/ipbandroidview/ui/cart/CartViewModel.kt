package com.progressterra.ipbandroidview.ui.cart

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.CartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.ext.removeItem
import com.progressterra.ipbandroidview.ext.replaceById
import com.progressterra.ipbandroidview.model.CartGoods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused", "MemberVisibilityCanBePrivate")
class CartViewModel(
    private val cartUseCase: CartUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val fastRemoveFromCartUseCase: FastRemoveFromCartUseCase
) : ViewModel(), ContainerHost<CartState, CartEffect> {

    override val container: Container<CartState, CartEffect> = container(CartState())

    fun favoriteSpecific(item: CartGoods) = intent {
        modifyFavoriteUseCase.modifyFavorite(item.id, item.favorite).onSuccess {
            val newList = state.cart.listGoods.replaceById(item.reverseFavorite())
            val newCart = state.cart.copy(listGoods = newList)
            reduce {
                state.copy(
                    cart = newCart
                )
            }
        }
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        cartUseCase.cart().onSuccess {
            reduce { state.copy(cart = it, screenState = ScreenState.SUCCESS) }

        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    fun openDetails(item: CartGoods) =
        intent { postSideEffect(CartEffect.GoodsDetails(item.id)) }

    fun next() = intent {
        postSideEffect(CartEffect.Next)
    }

    fun removeSpecific(item: CartGoods) = intent {
        fastRemoveFromCartUseCase.remove(item.id, item.inCartCounter).onSuccess {
            val newListGoods = state.cart.listGoods.removeItem(item)
            val newCart = state.cart.copy(listGoods = newListGoods)
            reduce {
                state.copy(cart = newCart)
            }
        }
    }

    fun auth() = intent {
        postSideEffect(CartEffect.Auth)
    }
}