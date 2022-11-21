package com.progressterra.ipbandroidview.ui.profile

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.user.UserData
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileViewModel : ViewModel(), ContainerHost<ProfileState, ProfileEffect> {

    override val container: Container<ProfileState, ProfileEffect> = container(ProfileState("", ""))

    init {
        refresh()
    }

    fun refresh() = intent {
        reduce {
            state.copy(
                phone = UserData.phone,
                name = "${UserData.userName.name} ${UserData.userName.surname}"
            )
        }
    }

    fun openDetails() = intent {
        postSideEffect(ProfileEffect.OpenDetails)
    }
}