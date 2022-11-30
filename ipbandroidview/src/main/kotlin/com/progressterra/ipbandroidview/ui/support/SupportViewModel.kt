package com.progressterra.ipbandroidview.ui.support

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class SupportViewModel : ViewModel(), ContainerHost<SupportState, SupportEffect> {

    override val container: Container<SupportState, SupportEffect> = container(SupportState())
}