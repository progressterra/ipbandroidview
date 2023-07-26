package com.progressterra.ipbandroidview.pages.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardEvent
import com.progressterra.ipbandroidview.features.search.SearchEvent
import com.progressterra.ipbandroidview.features.search.text
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.trace.TraceEvent
import com.progressterra.ipbandroidview.features.trace.trace
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.widgets.storeitems.items
import kotlinx.coroutines.flow.emptyFlow
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class CatalogViewModel(
    private val catalogUseCase: CatalogUseCase,
    private val goodsUseCase: GoodsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
) : UseCatalog, ViewModel(), ContainerHost<CatalogState, CatalogEvent> {

    override val container = container<CatalogState, CatalogEvent>(CatalogState())

    fun refresh() {
        intent {
            reduce { CatalogState() }
            catalogUseCase().onSuccess { catalog ->
                reduce { CatalogState.stateBox.set(state, ScreenState.SUCCESS) }
                reduce { CatalogState.current.set(state, catalog) }
                reduce { CatalogState.trace.trace.modify(state) { it + catalog } }
            }.onFailure {
                reduce { CatalogState.stateBox.set(state, ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: CatalogCardEvent) {
        intent {
            reduce { CatalogState.trace.trace.modify(state) { it + event.category } }
            reduce { CatalogState.current.set(state, event.category) }
            uCategory()
        }
    }

    override fun handle(event: TraceEvent) {
        intent {
            reduce { CatalogState.trace.trace.modify(state) { it.dropLast(1) } }
            reduce { CatalogState.current.set(state, state.trace.trace.last()) }
            uCategory()
        }
    }

    private fun uCategory() {
        intent {
            if (state.current.children.isEmpty()) {
                goodsUseCase(GoodsFilter(categoryId = state.current.id)).onSuccess {
                    val cached = it.cachedIn(viewModelScope)
                    reduce { CatalogState.goods.items.set(state, cached) }
                }
            } else {
                reduce { CatalogState.goods.items.set(state, emptyFlow()) }
            }
        }
    }

    override fun handle(event: StoreCardEvent) {
        intent {
            when (event) {
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is StoreCardEvent.Open -> postSideEffect(CatalogEvent.OnItem(event.id))
            }
        }
    }

    override fun handle(event: CounterEvent) {
        intent {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                    refresh()
                }
            }
        }
    }

    override fun handle(event: SearchEvent) {
        blockingIntent {
            reduce { CatalogState.search.text.set(state, event.text) }
        }
        intent {
            var filter = GoodsFilter(search = state.search.text)
            if (state.current.id.isNotEmpty()) {
                filter = filter.copy(categoryId = state.current.id)
            }
            if (state.search.text.isNotEmpty()) {
                goodsUseCase(filter).onSuccess {
                    val cached = it.cachedIn(viewModelScope)
                    reduce { CatalogState.goods.items.set(state, cached) }
                }
            } else {
                reduce { CatalogState.goods.items.set(state, emptyFlow()) }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent { refresh() }
    }
}
