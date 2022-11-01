package com.progressterra.ipbandroidview.ui.main

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.recommendedgoods.RecommendedGoodsUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val recommendedGoodsUseCase: RecommendedGoodsUseCase
) : ViewModel(), ContainerHost<MainState, MainEffect>, MainInteractor {

    override val container: Container<MainState, MainEffect> = container(MainState())

    init {
        refresh()
    }

    override fun back() = intent {

    }

    override fun favorite(id: String) = intent {

    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        recommendedGoodsUseCase.recommendedGoods().onSuccess { flow ->
            reduce { state.copy(items = flow, screenState = ScreenState.SUCCESS) }
        }
    }

    override fun keyword(keyword: String) = intent {
        reduce { state.copy(searchBarState = state.searchBarState.copy(keyword = keyword)) }
    }

    override fun card(id: String) = intent {
        postSideEffect(MainEffect.OpenDetails(id))
    }

    override fun search() = intent {

    }
}