package com.progressterra.ipbandroidview.pages.proshkamain

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesEvent
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesState
import com.progressterra.ipbandroidview.features.proshkaoffer.ProshkaOfferEvent
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardEvent
import com.progressterra.ipbandroidview.shared.ui.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.StateBoxState
import com.progressterra.ipbandroidview.widgets.proshkagalleries.ProshkaGalleriesState
import com.progressterra.ipbandroidview.widgets.proshkaoffers.ProshkaOffersState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class ProshkaMainViewModel(
    private val manageResources: ManageResources
) : UseProshkaMain, ContainerHost<ProshkaMainState, ProshkaMainEvent>, ViewModel() {

    override val container: Container<ProshkaMainState, ProshkaMainEvent> = container(
        ProshkaMainState(
            bonuses = ProshkaBonusesState(),
            offers = ProshkaOffersState(items = listOf()),
            stateBoxState = StateBoxState(),
            hits = ProshkaGalleriesState(
                title = "",
                items = listOf()
            ),
            new = ProshkaGalleriesState(
                title = "",
                items = listOf()
            )
        )
    )

    override fun handle(id: String, event: ProshkaBonusesEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(id: String, event: ProshkaOfferEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(id: String, event: ProshkaStoreCardEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(id: String, event: CounterEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(id: String, event: StateBoxEvent) {
        TODO("Not yet implemented")
    }
}