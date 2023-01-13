package com.progressterra.ipbandroidview.ui.profile

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.data.UserData
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileViewModel : ViewModel(), ContainerHost<ProfileState, ProfileEffect>,
    ProfileInteractor {

    override val container: Container<ProfileState, ProfileEffect> = container(ProfileState("", ""))

    init {
        refresh()
    }

    //TODO replace with usecases

    fun refresh() = intent {
        reduce {
            state.copy(
                phone = UserData.phone,
                name = "${UserData.userName.name} ${UserData.userName.surname}".trim()
            )
        }
    }

    override fun openDetails() = intent {
        postSideEffect(ProfileEffect.OpenDetails)
    }

    override fun onFavorites() = intent {
        postSideEffect(ProfileEffect.Favorites)
    }

    override fun onOrders() = intent {
        postSideEffect(ProfileEffect.Orders)
    }

    override fun onSupport() = intent {
        postSideEffect(ProfileEffect.Support)
    }

    override fun onReferral() = intent {
        postSideEffect(ProfileEffect.Referral)
    }
}