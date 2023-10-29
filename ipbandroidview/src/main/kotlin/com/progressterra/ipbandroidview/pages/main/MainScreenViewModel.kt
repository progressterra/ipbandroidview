package com.progressterra.ipbandroidview.pages.main

import com.progressterra.ipbandroidapi.api.catalog.CatalogRepository
import com.progressterra.ipbandroidview.IpbAndroidViewSettings.MAIN_SCREEN_CATEGORIES
import com.progressterra.ipbandroidview.features.bonuses.BonusesEvent
import com.progressterra.ipbandroidview.features.bonuses.BonusesModule
import com.progressterra.ipbandroidview.features.bonuses.BonusesModuleUser
import com.progressterra.ipbandroidview.features.bonuses.BonusesState
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.processes.FetchBonusesUseCase
import com.progressterra.ipbandroidview.processes.FetchGalleriesUseCase
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.replaceById
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesModule
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesModuleUser
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState

class MainScreenViewModel(
    fetchBonusesUseCase: FetchBonusesUseCase,
    private val obtainAccessToken: ObtainAccessToken,
    private val goodsUseCase: GoodsUseCase,
    private val catalogRepository: CatalogRepository,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val makeToastUseCase: MakeToastUseCase,
    private val manageResources: ManageResources
) : UseMainScreen, AbstractNonInputViewModel<MainScreenState, MainScreenEffect>() {

    override fun createInitialState() =
        MainScreenState(recommended = MAIN_SCREEN_CATEGORIES.map { GalleriesState(id = it) })

    private val galleriesModules: List<GalleriesModule> = MAIN_SCREEN_CATEGORIES.map { galleryId ->
        GalleriesModule(
            addToCartUseCase = addToCartUseCase,
            removeFromCartUseCase = removeFromCartUseCase,
            fetchGalleriesUseCase = FetchGalleriesUseCase.Base(
                obtainAccessToken = obtainAccessToken,
                goodsUseCase = goodsUseCase,
                productRepository = catalogRepository,
                makeToastUseCase = makeToastUseCase,
                manageResources = manageResources
            ),
            operations = this,
            user = object : GalleriesModuleUser {

                override fun emitModuleState(reducer: (GalleriesState) -> GalleriesState) {
                    emitState {
                        it.copy(recommended = it.recommended.replaceById(reducer(moduleState)))
                    }
                }

                override val moduleState: GalleriesState
                    get() = currentState.recommended.first { it.id == galleryId }


                override fun onGoods(data: String) {
                    postEffect(MainScreenEffect.OnItem(data))
                }
            }
        )
    }

    override fun refresh() {
        onBackground {
            bonusesModule.refresh()
            galleriesModules.forEach { it.refresh() }
        }
    }

    private val bonusesModule = BonusesModule(
        fetchBonusesUseCase = fetchBonusesUseCase,
        operations = this,
        user = object : BonusesModuleUser {

            override fun onBonusesTransactions() {
                postEffect(MainScreenEffect.OnBonuses)
            }

            override fun onWithdrawal() {
                postEffect(MainScreenEffect.OnWithdrawal)
            }

            override fun onAddCard() {
                postEffect(MainScreenEffect.OnAddCard)
            }

            override fun emitModuleState(reducer: (BonusesState) -> BonusesState) {
                emitState { it.copy(bonuses = reducer(currentState.bonuses)) }
            }

            override val moduleState: BonusesState
                get() = currentState.bonuses
        }
    )

    override fun handle(event: BonusesEvent) {
        bonusesModule.handle(event)
    }

    override fun handle(event: StoreCardEvent) {
        galleriesModules.getOrNull(0)?.handle(event)
        if (event is StoreCardEvent.AddToCart) galleriesModules.forEach { it.refresh() }
    }

    override fun handle(event: CounterEvent) {
        galleriesModules.getOrNull(0)?.handle(event)
        galleriesModules.forEach { it.refresh() }
    }

    override fun handle(event: StateColumnEvent) {
        bonusesModule.handle(event)
        galleriesModules.forEach { it.handle(event) }
    }
}