package com.progressterra.ipbandroidview.pages.catalog

import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardEvent
import com.progressterra.ipbandroidview.features.search.SearchEvent
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.trace.TraceEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import kotlinx.coroutines.flow.emptyFlow

class CatalogScreenViewModel(
    private val catalogUseCase: CatalogUseCase,
    private val goodsUseCase: GoodsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
) : UseCatalog, AbstractNonInputViewModel<CatalogScreenState, CatalogScreenEffect>() {

    override fun createInitialState() = CatalogScreenState()

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            catalogUseCase().onSuccess { catalog ->
                emitState {
                    it.copy(
                        screen = it.screen.copy(state = ScreenState.SUCCESS),
                        current = catalog,
                        trace = it.trace.copy(current = catalog)
                    )
                }
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: CatalogCardEvent) {
        emitState {
            it.copy(
                current = event.category,
                trace = it.trace.copy(trace = it.trace.trace + it.current, current = event.category)
            )
        }
        uCategory()
    }

    override fun handle(event: TraceEvent) {
        emitState {
            val last = it.trace.trace.last()
            it.copy(
                current = last,
                trace = it.trace.copy(current = last)
            )
        }
        emitState { it.copy(trace = it.trace.copy(trace = it.trace.trace.dropLast(1))) }
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

                is StoreCardEvent.Open -> postEffect(CatalogScreenEffect.OnItem(event.id))
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
            goodsUseCase(filter).onSuccess { nonCached ->
                emitState { it.copy(goods = it.goods.copy(items = cachePaging(nonCached))) }
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}
