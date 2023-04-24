package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.widgets.edituser.EditUserValidUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class ProfileDetailsViewModel(
    private val saveUseCase: SaveDataUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val editUserValidUseCase: EditUserValidUseCase
) : ViewModel(),
    ContainerHost<ProfileDetailsState, ProfileDetailsEvent>, UseProfileDetails {

    override val container =
        container<ProfileDetailsState, ProfileDetailsEvent>(ProfileDetailsState())

    fun refresh() = intent {
        fetchUserUseCase().onSuccess {
            reduce { state.updateEditUser(it) }
        }
    }

    override fun handle(event: AuthProfileEvent) = intent {
        when (event) {
            //TODO
            is AuthProfileEvent.Click -> Unit
        }
    }

    override fun handle(event: TopBarEvent) = intent {
        when (event) {
            is TopBarEvent.Back -> postSideEffect(ProfileDetailsEvent.Back)
        }
    }

    override fun handle(event: ButtonEvent) = intent {
        when (event) {
            is ButtonEvent.Click -> when (event.id) {
                "edit" -> reduce { state.startCancelEdit() }
                "save" -> saveUseCase(state.editUser)
                "cancel" -> refresh()
            }
        }
    }

    override fun handle(event: StateBoxEvent) = intent {
        when (event) {
            is StateBoxEvent.Refresh -> refresh()
        }
    }


    override fun handle(event: TextFieldEvent) = blockingIntent {
        when (event.id) {
            "name" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateName(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "email" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateEmail(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "phone" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updatePhone(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "birthday" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateBirthday(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "citizenship" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateCitizenship(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "address" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updateAddress(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "passport" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updatePassport(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "passportProvider" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updatePassportProvider(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "passportProviderCode" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updatePassportProviderCode(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }

            "patent" -> when (event) {
                is TextFieldEvent.TextChanged -> reduce { state.updatePatent(event.text) }
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
            }
        }
        valid()
    }

    private fun valid() = intent {
        val valid = editUserValidUseCase(state.editUser).isSuccess
        reduce { state.updateSaveEnabled(valid) }
    }
}