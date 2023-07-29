package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardEvent
import com.progressterra.ipbandroidview.features.search.SearchEvent
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.trace.TraceEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import kotlinx.coroutines.flow.emptyFlow

class CatalogViewModel(
    private val catalogUseCase: CatalogUseCase,
    private val goodsUseCase: GoodsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
) : UseCatalog, BaseViewModel<CatalogState, CatalogEvent>() {

    override fun createInitialState() = CatalogState()

    fun refresh() {
        onBackground {
            emitState { it.copy(stateBox = ScreenState.LOADING) }
            catalogUseCase().onSuccess { catalog ->
                emitState {
                    it.copy(
                        stateBox = ScreenState.SUCCESS,
                        items = cachePaging(catalog)
                    )
                }
            }.onFailure {
                emitState { it.copy(stateBox = ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: CatalogCardEvent) {
        emitState {
            it.copy(
                current = event.category,
                trace = it.trace.copy(trace = it.trace.trace + event.category)
            )
        }
        uCategory()
    }

    override fun handle(event: TraceEvent) {
        emitState {
            it.copy(trace = it.trace.copy(trace = it.trace.trace.dropLast(1)))
        }
        emitState { it.copy(current = it.trace.trace.last()) }
        uCategory()
    }

    private fun uCategory() {
        onBackground {
            if (currentState.current.children.isEmpty()) {
                goodsUseCase(GoodsFilter(categoryId = currentState.current.id)).onSuccess { nonCached ->
                    emitState {
                        it.copy(goods = it.goods.copy(items = cachePaging(nonCached)))
                    }
                }
            } else {
                emitState { it.copy(goods = it.goods.copy(items = emptyFlow())) }
            }
        }
    }

    override fun handle(event: StoreCardEvent) {
        onBackground {
            when (event) {
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is StoreCardEvent.Open -> postEffect(CatalogEvent.OnItem(event.id))
            }
        }
    }

    override fun handle(event: CounterEvent) {
        onBackground {
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
        emitState {
            it.copy(search = it.search.copy(text = event.text))
        }
        onBackground {
            var filter = GoodsFilter(search = currentState.search.text)
            if (currentState.current.id.isNotEmpty()) {
                filter = filter.copy(categoryId = currentState.current.id)
            }
            if (currentState.search.text.isNotEmpty()) {
                goodsUseCase(filter).onSuccess { nonCached ->
                    emitState { it.copy(goods = it.goods.copy(items = cachePaging(nonCached))) }
                }
            } else {
                emitState { it.copy(goods = it.goods.copy(items = emptyFlow())) }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }
}
