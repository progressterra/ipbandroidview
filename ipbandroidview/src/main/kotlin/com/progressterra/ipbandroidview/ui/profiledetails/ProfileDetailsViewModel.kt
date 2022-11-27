package com.progressterra.ipbandroidview.ui.profiledetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.data.UserData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.domain.usecase.user.UpdatePersonalInfoUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProfileDetailsViewModel(private val updatePersonalInfoUseCase: UpdatePersonalInfoUseCase) :
    ViewModel(), ContainerHost<ProfileDetailsState, ProfileDetailsEffect> {

    override val container: Container<ProfileDetailsState, ProfileDetailsEffect> =
        container(ProfileDetailsState("", "", ""))

    init {
        refresh()
    }

    fun refresh() = intent {
        reduce {
            state.copy(
                phone = UserData.phone,
                name = if (UserData.userName.name.isNotBlank() && UserData.userName.surname.isNotBlank()) "${UserData.userName.name} ${UserData.userName.surname}" else "",
                email = UserData.email
            )
        }
    }

    fun confirmChange() = intent {
        updatePersonalInfoUseCase.update(state.name, state.email).onSuccess {
            postSideEffect(ProfileDetailsEffect.Toast(R.string.success_changed_personal))
            postSideEffect(ProfileDetailsEffect.UpdateUserInfo)
        }.onFailure {
            postSideEffect(ProfileDetailsEffect.Toast(R.string.failed_changed_personal))
        }
    }

    fun editEmail(email: String) = intent {
        reduce { state.copy(email = email) }
    }

    fun editName(name: String) = intent {
        reduce { state.copy(name = name) }
    }

    fun logout() = intent {
        UserData.clearUser()
        postSideEffect(ProfileDetailsEffect.Logout)
    }

    fun back() = intent {
        postSideEffect(ProfileDetailsEffect.Back)
    }
}