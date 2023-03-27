package com.progressterra.ipbandroidview.ui.cart

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.processes.usecase.store.CartUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.UserExistsUseCase
import com.progressterra.ipbandroidview.ext.removeItem
import com.progressterra.ipbandroidview.ext.replaceById
import com.progressterra.ipbandroidview.composable.component.CartCardState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CartViewModel(
    private val cartUseCase: CartUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val fastRemoveFromCartUseCase: FastRemoveFromCartUseCase,
    private val userExistsUseCase: UserExistsUseCase
) : ViewModel(), ContainerHost<CartState, CartEffect>, CartInteractor {

    override val container: Container<CartState, CartEffect> = container(CartState())

    override fun favorite(cartCard: CartCardState) = intent {
        modifyFavoriteUseCase(cartCard.id, cartCard.favorite).onSuccess {
            val newList = state.cart.listGoods.replaceById(cartCard.reverseFavorite())
            val newCart = state.cart.copy(listGoods = newList)
            reduce { state.copy(cart = newCart) }
        }
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        cartUseCase().onSuccess {
            val userExists = userExistsUseCase()
            reduce { state.copy(cart = it, userExist = userExists, screenState = ScreenState.SUCCESS) }
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }

    override fun onDetails(cartCard: CartCardState) =
        intent { postSideEffect(CartEffect.GoodsDetails(cartCard.id)) }

    override fun onNext() = intent {
        postSideEffect(CartEffect.Next(state.cart.listGoods.map { it.toOrderGoods() }))
    }

    override fun delete(cartCard: CartCardState) = intent {
        fastRemoveFromCartUseCase(cartCard.id, cartCard.inCartCounter).onSuccess {
            val newListGoods = state.cart.listGoods.removeItem(cartCard)
            val newCart = state.cart.copy(listGoods = newListGoods)
            reduce {
                state.copy(cart = newCart)
            }
        }
    }

    override fun onAuth() = intent {
        postSideEffect(CartEffect.Auth)
    }
}