package com.progressterra.ipbandroidview.ui.catalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.composable.component.CatalogBarComponentEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.processes.usecase.store.CatalogUseCase
import com.progressterra.ipbandroidview.entities.CategoryWithSubcategories
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class CatalogViewModel(
    private val catalogUseCase: CatalogUseCase
) : ViewModel(), ContainerHost<CatalogState, CatalogEffect>, CatalogInteractor {

    override val container: Container<CatalogState, CatalogEffect> = container(CatalogState())

    init {
        refresh()
    }

    override fun openCategory(categoryWithSubcategories: CategoryWithSubcategories) = intent {
        if (categoryWithSubcategories.hasNext) postSideEffect(
            CatalogEffect.SubCatalog(categoryWithSubcategories)
        )
        else postSideEffect(CatalogEffect.Goods(categoryWithSubcategories.id))
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        catalogUseCase().onSuccess {
            reduce { state.copy(categories = it, screenState = ScreenState.SUCCESS) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun handle(event: CatalogBarComponentEvent) = intent {
        when (id) {
            "main" -> when (event) {
                is CatalogBarComponentEvent.OnClear -> clear()
                is CatalogBarComponentEvent.OnSearch -> search()
            }
        }
    }

    override fun handle(event: TextFieldEvent) = blockingIntent {
        when (id) {
            "keyword" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce {
                    val newKeywordState = state.catalogBarState.keywordState.copy(text = event.text)
                    val newCatalogBarState =
                        state.catalogBarState.copy(keywordState = newKeywordState)
                    state.copy(catalogBarState = newCatalogBarState)
                }
                is TextFieldEvent.Action -> search()
            }
        }
    }

    private fun search() = intent {
        postSideEffect(CatalogEffect.Search(state.catalogBarState.keywordState.text))
    }

    private fun clear() = intent {
        reduce {
            val newKeywordState = state.catalogBarState.keywordState.copy(text = "")
            val newCatalogBarState = state.catalogBarState.copy(keywordState = newKeywordState)
            state.copy(catalogBarState = newCatalogBarState)
        }
    }
}