package com.progressterra.ipbandroidview.ui.profiledetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.domain.UpdatePersonalInfoUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileDetailsViewModel(private val updatePersonalInfoUseCase: UpdatePersonalInfoUseCase) :
    ViewModel(),
    ContainerHost<ProfileDetailsState, ProfileDetailsEffect>,
    ProfileDetailsInteractor {

    override val container: Container<ProfileDetailsState, ProfileDetailsEffect> =
        container(ProfileDetailsState("", "", ""))

    init {
        refresh()
    }

    fun refresh() = intent {
        reduce {
            state.copy(
                phone = UserData.phone,
                name = "${UserData.clientInfo.soname} ${UserData.clientInfo.name}",
                email = UserData.clientAdditionalInfo.eMailGeneral
            )
        }
    }


    override fun onEmail(email: String) = intent {
        reduce { state.copy(email = email) }
    }

    override fun onName(name: String) = intent {
        reduce { state.copy(name = name) }
    }

    override fun logout() = intent {
        UserData.clearUser()
        postSideEffect(ProfileDetailsEffect.Logout)
    }

    override fun back() = intent {
        updatePersonalInfoUseCase.update(state.name, state.email).onSuccess {
            UserData.clientInfo = it.client
            UserData.clientAdditionalInfo = it.clientAdditional
            postSideEffect(ProfileDetailsEffect.Toast(R.string.success_changed_personal))
            postSideEffect(ProfileDetailsEffect.UpdateUserInfo)
        }.onFailure {
            postSideEffect(ProfileDetailsEffect.Toast(R.string.failed_changed_personal))
        }
        postSideEffect(ProfileDetailsEffect.GoBack)
    }
}