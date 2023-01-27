package com.progressterra.ipbandroidview.ui.profiledetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.data.UserData
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserNameUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserPhoneUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UpdatePersonalInfoUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class ProfileDetailsViewModel(
    private val updatePersonalInfoUseCase: UpdatePersonalInfoUseCase,
    private val fetchUserNameUseCase: FetchUserNameUseCase,
    private val fetchUserPhoneUseCase: FetchUserPhoneUseCase,
    private val fetchUserEmailUseCase: FetchUserEmailUseCase
) : ViewModel(), ContainerHost<ProfileDetailsState, ProfileDetailsEffect>,
    ProfileDetailsInteractor {

    override val container: Container<ProfileDetailsState, ProfileDetailsEffect> =
        container(ProfileDetailsState())

    init {
        refresh()
    }

    fun refresh() = intent {
        val phone = fetchUserPhoneUseCase()
        val name = fetchUserNameUseCase()
        val email = fetchUserEmailUseCase()
        reduce { state.copy(phone = phone, name = name, email = email) }
    }

    override fun confirmChange() = intent {
        updatePersonalInfoUseCase(state.name, state.email).onSuccess {
            postSideEffect(ProfileDetailsEffect.Toast(R.string.success_changed_personal))
            postSideEffect(ProfileDetailsEffect.UpdateUserInfo)
        }.onFailure {
            postSideEffect(ProfileDetailsEffect.Toast(R.string.failed_changed_personal))
        }
    }

    override fun editEmail(email: String) = blockingIntent { reduce { state.copy(email = email) } }

    override fun editName(name: String) = blockingIntent { reduce { state.copy(name = name) } }

    override fun logout() = intent {
        UserData.clearUser()
        postSideEffect(ProfileDetailsEffect.Logout)
    }

    override fun onBack() = intent { postSideEffect(ProfileDetailsEffect.Back) }
}