package com.progressterra.ipbandroidview.ui.goods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.composable.component.GoodsBarComponentEvent
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.shared.ui.TextFieldEvent
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.processes.usecase.store.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.GoodsUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.ModifyFavoriteUseCase
import kotlinx.coroutines.flow.emptyFlow
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class GoodsViewModel(
    private val goodsUseCase: GoodsUseCase,
    private val filteredGoodsUseCase: FilteredGoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<GoodsState, GoodsEffect>, GoodsInteractor {

    override val container: Container<GoodsState, GoodsEffect> = container(GoodsState())

    fun setup(categoryId: String, keyword: String) = intent {
        reduce {
            val newKeywordState = state.goodsBarComponentState.keyword.copy(text = keyword)
            val newGoodsBarState = state.goodsBarComponentState.copy(keyword = newKeywordState)
            state.copy(goodsBarComponentState = newGoodsBarState, currentCategory = categoryId)
        }
        refresh()
    }

    private fun onBack() = intent {
        postSideEffect(GoodsEffect.Back)
    }

    private fun filters() = intent {
        postSideEffect(GoodsEffect.Filters)
    }

    private fun search() = intent {
        if (state.goodsBarComponentState.keyword.text.isNotBlank())
            refreshSearch()
    }

    private fun clear() = intent {
        reduce {
            val newKeywordState = state.goodsBarComponentState.keyword.copy(text = "")
            val newGoodsBarState = state.goodsBarComponentState.copy(keyword = newKeywordState)
            state.copy(goodsBarComponentState = newGoodsBarState)
        }
    }

    override fun onClick(storeCard: StoreCardComponentState) = intent {
        postSideEffect(GoodsEffect.GoodsDetails(storeCard.id))
    }

    override fun favorite(storeCard: StoreCardComponentState) = intent {
        modifyFavoriteUseCase(storeCard.id, storeCard.favorite).onSuccess { refresh() }
    }

    override fun refresh() = intent {
        if (state.goodsBarComponentState.keyword.text.isNotBlank())
            refreshSearch()
        else
            refreshCategory()
    }

    private fun refreshSearch() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        filteredGoodsUseCase(
            state.currentCategory,
            state.goodsBarComponentState.keyword.text,
            state.filters
        ).onSuccess {
            reduce {
                state.copy(
                    items = it,
                    itemsFlow = emptyFlow(),
                    screenState = ScreenState.SUCCESS
                )
            }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    private fun refreshCategory() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        goodsUseCase(state.currentCategory).onSuccess {
            reduce {
                state.copy(
                    itemsFlow = it.flow.cachedIn(viewModelScope),
                    items = emptyList(),
                    screenState = ScreenState.SUCCESS
                )
            }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun handle(id: String, event: GoodsBarComponentEvent) {
        when (id) {
            "main" -> {
                when (event) {
                    is GoodsBarComponentEvent.OnBack -> onBack()
                    is GoodsBarComponentEvent.OnClear -> clear()
                    is GoodsBarComponentEvent.OnSearch -> search()
                }
            }
        }
    }

    override fun handle(id: String, event: TextFieldEvent) = blockingIntent {
        when (id) {
            "keyword" -> {
                when (event) {
                    is TextFieldEvent.TextChanged -> reduce {
                        val newKeywordState =
                            state.goodsBarComponentState.keyword.copy(text = event.text)
                        val newGoodsBarState =
                            state.goodsBarComponentState.copy(keyword = newKeywordState)
                        state.copy(goodsBarComponentState = newGoodsBarState)
                    }
                    is TextFieldEvent.Action -> search()
                }
            }
        }
    }
}