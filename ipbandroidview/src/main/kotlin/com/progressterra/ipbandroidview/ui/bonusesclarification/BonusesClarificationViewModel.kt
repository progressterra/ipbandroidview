package com.progressterra.ipbandroidview.ui.bonusesclarification

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class BonusesClarificationViewModel : ViewModel(),
    ContainerHost<BonusesClarificationState, BonusesClarificationEffect>, BonusesClarificationInteractor {

    override val container: Container<BonusesClarificationState, BonusesClarificationEffect> =
        container(BonusesClarificationState())

    override fun onBack() = intent {
        postSideEffect(BonusesClarificationEffect.Back)
    }

    override fun expandHowToSpend() = intent {
        reduce { state.copy(howToSpendExpand = !state.howToSpendExpand) }
    }

    override fun expandRatio() = intent {
        reduce { state.copy(ratioExpand = !state.ratioExpand) }
    }

    override fun expandHowToObtain() = intent {
        reduce { state.copy(howToObtainExpand = !state.howToObtainExpand) }
    }
}
