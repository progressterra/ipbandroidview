package com.progressterra.ipbandroidview.ui.partner

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.domain.usecase.StartActivityUseCase
import com.progressterra.ipbandroidview.model.partner.Partner
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container


class PartnerViewModel(
    private val startActivityUseCase: StartActivityUseCase
) : ViewModel(), ContainerHost<PartnerState, PartnerEffect>,
    PartnerInteractor {

    override val container: Container<PartnerState, PartnerEffect> = container(PartnerState())

    fun setPartner(partner: Partner) = intent {
        reduce { state.copy(partner = partner) }
    }

    override fun onBack() = intent { postSideEffect(PartnerEffect.Back) }

    override fun openWebsite(url: String) = intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivityUseCase(intent)
    }

    override fun openPhone(phone: String) = intent {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phone")
        startActivityUseCase(intent)
    }
}