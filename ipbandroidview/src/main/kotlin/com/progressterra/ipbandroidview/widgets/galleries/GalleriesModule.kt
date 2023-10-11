package com.progressterra.ipbandroidview.widgets.galleries

import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.mvi.Operations
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class GalleriesModule(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val fetchGalleriesUseCase: FetchGalleriesUseCase,
    operations: Operations,
    user: GalleriesModuleUser
) : GalleriesModuleUser by user, Operations by operations, UseGalleries {


    init {
        onBackground {
            fetchGalleriesUseCase.resultFlow.collect { result ->
                result.onSuccess { gallery ->
                    val cachedGallery = gallery.copy(items = cachePaging(gallery.items))
                    emitModuleState { cachedGallery }
                }.onFailure {
                    emitModuleState { it.copy(state = it.state.copy(state = ScreenState.ERROR)) }
                }
            }
        }
    }

    fun refresh() {
        onBackground {
            fetchGalleriesUseCase(moduleState.id)
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }

    override fun handle(event: StoreCardEvent) {
        onBackground {
            when (event) {
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id)
                is StoreCardEvent.Open -> onGoods(event.id)
            }
        }
    }

    override fun handle(event: CounterEvent) {
        onBackground {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id)
                is CounterEvent.Remove -> removeFromCartUseCase(event.id)
            }
        }
    }
}

