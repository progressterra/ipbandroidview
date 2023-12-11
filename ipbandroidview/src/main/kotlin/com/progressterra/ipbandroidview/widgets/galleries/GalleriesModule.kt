package com.progressterra.ipbandroidview.widgets.galleries

import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.goods.FetchGalleriesUseCase
import com.progressterra.ipbandroidview.shared.mvi.Operations
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import kotlinx.coroutines.flow.collectLatest

class GalleriesModule(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val fetchGalleriesUseCase: FetchGalleriesUseCase,
    operations: Operations,
    user: GalleriesModuleUser
) : GalleriesModuleUser by user, Operations by operations, UseGalleries {

    init {
        onBackground {
            fetchGalleriesUseCase.resultFlow.collectLatest { result ->
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
                is StoreCardEvent.AddToCart -> addToCartUseCase(
                    goodsId = event.id,
                    onAuth = { onAuth() })

                is StoreCardEvent.Open -> onGoods(event.id)
            }
        }
    }

    override fun handle(event: CounterEvent) {
        onBackground {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(goodsId = event.id, onAuth = { onAuth() })
                is CounterEvent.Remove -> removeFromCartUseCase(event.id)
            }
        }
    }
}
