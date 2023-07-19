package com.progressterra.ipbandroidview.pages.catalog

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardEvent
import com.progressterra.ipbandroidview.features.search.SearchEvent
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.trace.TraceEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import kotlinx.coroutines.flow.emptyFlow
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

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
            catalogUseCase().onSuccess {
                reduce { state.uCategory(it).addTrace(it).uScreenState(ScreenState.SUCCESS) }
            }.onFailure {
                reduce { state.uScreenState(ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: CatalogCardEvent) {
        intent {
            reduce { state.addTrace(event.category).uCategory(event.category) }
            uCategory()
        }
    }

    override fun handle(event: TraceEvent) {
        intent {
            reduce { state.removeTrace() }
            reduce { state.uCategory(state.trace.trace.last()) }
            uCategory()
        }
    }

    private fun uCategory() {
        intent {
            if (state.current.children.isEmpty()) {
                goodsUseCase(GoodsFilter(state.current.id)).onSuccess {
                    reduce { state.uGoods(it) }
                }
            } else reduce { state.uGoods(emptyFlow()) }
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
        intent {
            reduce { state.uSearchText(event.text) }
            if (state.search.text.isNotEmpty()) {
                goodsUseCase(GoodsFilter(search = state.search.text)).onSuccess {
                    reduce { state.uGoods(it) }
                }
            } else {
                reduce { state.uGoods(emptyFlow()) }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent { refresh() }
    }
}
