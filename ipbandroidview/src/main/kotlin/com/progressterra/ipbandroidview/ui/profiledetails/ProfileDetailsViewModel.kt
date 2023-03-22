package com.progressterra.ipbandroidview.ui.profiledetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.ButtonState
import com.progressterra.ipbandroidview.shared.ui.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.TextFieldState
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.domain.usecase.FetchVersionUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserNameUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserPhoneUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.LogoutUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.ext.isEmail
import com.progressterra.ipbandroidview.ext.isNameAndSurname
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
    private val fetchUserEmailUseCase: FetchUserEmailUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val fetchVersionUseCase: FetchVersionUseCase,
    manageResources: ManageResources
) : ViewModel(), ContainerHost<ProfileDetailsState, ProfileDetailsEffect>,
    ProfileDetailsInteractor {

    override val container: Container<ProfileDetailsState, ProfileDetailsEffect> =
        container(
            ProfileDetailsState(
                name = TextFieldState(
                    hint = manageResources.string(R.string.name_surname)
                ),
                email = TextFieldState(
                    hint = manageResources.string(R.string.email)
                ),
                phone = TextFieldState(
                    hint = manageResources.string(R.string.phone_number),
                    enabled = false
                ),
                confirm = ButtonState(
                    text = manageResources.string(R.string.confirm_change),
                    enabled = false
                ),
                logout = ButtonState(
                    text = manageResources.string(R.string.logout)
                )
            )
        )

    init {
        refresh()
    }

    fun refresh() = intent {
        val phone = fetchUserPhoneUseCase()
        val name = fetchUserNameUseCase()
        val email = fetchUserEmailUseCase()
        val version = fetchVersionUseCase()
        reduce {
            state.updateEmail(email).updateName(name).updatePhone(phone).updateVersion(version)
        }
    }

    private fun confirmChange() = intent {
        reduce { state.updateTextFieldsEnabled(false) }
        updatePersonalInfoUseCase(state.name.text, state.email.text).onSuccess {
            postSideEffect(ProfileDetailsEffect.Toast(R.string.success_changed_personal))
            postSideEffect(ProfileDetailsEffect.UpdateUserInfo)
        }.onFailure {
            postSideEffect(ProfileDetailsEffect.Toast(R.string.failed_changed_personal))
        }
        reduce { state.updateTextFieldsEnabled(true) }
    }

    override fun handleEvent(id: String, event: ButtonEvent) = intent {
        when (id) {
            "confirm" -> when (event) {
                is ButtonEvent.Click -> confirmChange()
            }
            "logout" -> when (event) {
                is ButtonEvent.Click -> logout()
            }
        }
    }

    override fun handleEvent(id: String, event: TextFieldEvent) = blockingIntent {
        when (id) {
            "name" -> when (event) {
                is TextFieldEvent.TextChanged -> {
                    reduce { state.updateName(event.text) }
                    validateInputs()
                }
                is TextFieldEvent.Action -> Unit
            }
            "email" -> when (event) {
                is TextFieldEvent.TextChanged -> {
                    reduce { state.updateEmail(event.text) }
                    validateInputs()
                }
                is TextFieldEvent.Action -> Unit
            }
            "phone" -> when (event) {
                is TextFieldEvent.TextChanged -> Unit
                is TextFieldEvent.Action -> Unit
            }
        }
    }

    private fun validateInputs() = intent {
        val valid = (state.email.text.trim()
            .isEmail() || state.email.text.isEmpty()) && state.name.text.trim().isNameAndSurname()
        reduce { state.updateConfirmEnabled(valid) }
    }

    private fun logout() = intent {
        logoutUseCase().onSuccess {
            postSideEffect(ProfileDetailsEffect.Logout)
        }
    }

    override fun onBack() = intent { postSideEffect(ProfileDetailsEffect.Back) }
}