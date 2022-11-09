package com.progressterra.ipbandroidview.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.DomainConstants
import com.progressterra.ipbandroidview.domain.GoodsUseCase
import com.progressterra.ipbandroidview.domain.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.dto.Goods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val goodsUseCase: GoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<MainState, MainEffect>, MainInteractor {

    override val container: Container<MainState, MainEffect> = container(MainState())

    init {
        refresh()
    }

    override fun favorite(goodsId: String, favorite: Boolean) = intent {
        modifyFavoriteUseCase.modifyFavorite(goodsId, favorite).onSuccess { refresh() }
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        goodsUseCase.goods(DomainConstants.MAIN_DEFAULT_CATEGORY_ID).onSuccess { flow ->
            reduce {
                state.copy(
                    items = flow.cachedIn(viewModelScope), screenState = ScreenState.SUCCESS
                )
            }
        }
    }

    override fun goodsDetails(goods: Goods) = intent {
        postSideEffect(MainEffect.GoodsDetails(goods))
    }
}