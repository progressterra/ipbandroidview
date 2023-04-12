package com.progressterra.ipbandroidview.pages.catalog

import androidx.lifecycle.ViewModel
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

    fun refresh() = intent {
        reduce { state.updateStateBox(ScreenState.LOADING) }
        catalogUseCase().onSuccess {
            reduce { state.updateStateBox(ScreenState.SUCCESS) }
            reduce { state.updateItems(it) }
        }.onFailure {
            reduce { state.updateStateBox(ScreenState.ERROR) }
        }
    }

    override fun handle(event: CatalogCardEvent) = intent {
        when (event) {
            is CatalogCardEvent.Open -> {
                if (!state.current.hasNext) {
                    goodsUseCase(event.category.id).onSuccess {
                        reduce { state.updateGoods(it) }
                    }
                }
                reduce { state.addTrace(event.category).updateItems(event.category) }
            }
        }
    }

    override fun handle(event: TraceEvent) = intent {
        when (event) {
            is TraceEvent.Back -> {
                reduce { state.removeTrace().updateItems(state.trace.trace.last()) }
            }
        }
    }

    override fun handle(event: StoreCardEvent) = intent {
        when (event) {
            is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                refresh()
            }
            is StoreCardEvent.Open -> postSideEffect(CatalogEvent.OnItem(event.id))
        }
    }

    override fun handle(event: CounterEvent) = intent {
        when (event) {
            is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                refresh()
            }
            is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                refresh()
            }
        }
    }

    override fun handle(event: SearchEvent) = intent {
        when (event) {
            is SearchEvent.OnTextChanged -> {
                //TODO
            }
        }
    }

    override fun handle(event: StateBoxEvent) = intent {
        when (event) {
            is StateBoxEvent.Refresh -> refresh()
        }
    }
}