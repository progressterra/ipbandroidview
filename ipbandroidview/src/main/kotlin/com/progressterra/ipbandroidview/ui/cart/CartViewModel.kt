package com.progressterra.ipbandroidview.ui.cart

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.CartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UserExistsUseCase
import com.progressterra.ipbandroidview.ext.removeItem
import com.progressterra.ipbandroidview.ext.replaceById
import com.progressterra.ipbandroidview.model.CartGoods
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

    override fun favoriteSpecific(goods: CartGoods) = intent {
        modifyFavoriteUseCase(goods.id, goods.favorite).onSuccess {
            val newList = state.cart.listGoods.replaceById(goods.reverseFavorite())
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

    override fun openDetails(goods: CartGoods) =
        intent { postSideEffect(CartEffect.GoodsDetails(goods.id)) }

    override fun onNext() = intent {
        postSideEffect(CartEffect.Next(state.cart.listGoods.map { it.toOrderGoods() }))
    }

    override fun removeSpecific(goods: CartGoods) = intent {
        fastRemoveFromCartUseCase(goods.id, goods.inCartCounter).onSuccess {
            val newListGoods = state.cart.listGoods.removeItem(goods)
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