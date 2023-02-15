package com.progressterra.ipbandroidview.ui.partner

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.domain.usecase.OpenPhoneUseCase
import com.progressterra.ipbandroidview.domain.usecase.OpenUrlUseCase
import com.progressterra.ipbandroidview.model.Partner
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class PartnerViewModel(
    private val openUrlUseCase: OpenUrlUseCase,
    private val openPhoneUseCase: OpenPhoneUseCase
) : ViewModel(), ContainerHost<PartnerState, PartnerEffect>,
    PartnerInteractor {

    override val container: Container<PartnerState, PartnerEffect> = container(PartnerState())

    fun setPartner(partner: Partner) = intent {
        reduce { state.copy(partner = partner) }
    }

    override fun onBack() = intent { postSideEffect(PartnerEffect.Back) }

    override fun openWebsite(url: String) = intent { openUrlUseCase(url) }

    override fun openPhone(phone: String) = intent { openPhoneUseCase(phone) }
}