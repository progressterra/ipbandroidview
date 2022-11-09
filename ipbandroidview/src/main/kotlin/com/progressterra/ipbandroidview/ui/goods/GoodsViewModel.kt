package com.progressterra.ipbandroidview.ui.goods

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.GoodsUseCase
import com.progressterra.ipbandroidview.domain.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.dto.Goods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class GoodsViewModel(
    private val goodsUseCase: GoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<GoodsState, GoodsEffect>, GoodsInteractor {

    override val container: Container<GoodsState, GoodsEffect> = container(GoodsState())

    @Suppress("unused")
    fun setCategoryId(categoryId: String) = intent {
        reduce { state.copy(currentCategory = categoryId) }
    }

    override fun back() = intent {
        postSideEffect(GoodsEffect.Back)
    }

    override fun favorite(goodsId: String, favorite: Boolean) = intent {
        modifyFavoriteUseCase.modifyFavorite(goodsId, favorite).onSuccess { refresh() }
    }

    override fun refresh() = intent {
        state.currentCategory?.let {
            reduce { state.copy(screenState = ScreenState.LOADING) }
            goodsUseCase.goods(it).onSuccess {
                reduce { state.copy(items = it, screenState = ScreenState.SUCCESS) }
            }.onFailure {
                reduce { state.copy(screenState = ScreenState.ERROR) }
            }
        }
    }

    override fun goodsDetails(goods: Goods) = intent {
        postSideEffect(GoodsEffect.GoodsDetails(goods))
    }
}