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
        reduce { ProfileDetailsState() }
        fetchUserUseCase().onSuccess {
            reduce { state.updateEditUser(it) }
        }
    }

    override fun handle(event: AuthProfileEvent) = intent {
        when (event) {
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
                "edit" -> reduce {
                    state.startCancelEdit()
                        .updatePassportProviderEnabled(true)
                        .updatePassportEnabled(true)
                        .updatePassportProviderCodeEnabled(true)
                        .updateCitizenshipEnabled(true)
                        .updateEmailEnabled(true)
                        .updateNameEnabled(true)
                        .updateBirthdayEnabled(true)
                        .updateAddressEnabled(true)
                        .updatePatentEnabled(true)
                }

                "save" -> saveUseCase(state.editUser).onSuccess {
                    reduce {
                        state.startCancelEdit()
                            .updatePassportProviderEnabled(false)
                            .updatePassportEnabled(false)
                            .updatePassportProviderCodeEnabled(false)
                            .updateCitizenshipEnabled(false)
                            .updateEmailEnabled(false)
                            .updateNameEnabled(false)
                            .updateBirthdayEnabled(false)
                            .updateAddressEnabled(false)
                            .updatePatentEnabled(false)
                    }
                }

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
        when (event) {
            is TextFieldEvent.TextChanged -> {
                when (event.id) {
                    "name" -> reduce { state.updateName(event.text) }
                    "email" -> reduce { state.updateEmail(event.text) }
                    "birthday" -> reduce { state.updateBirthday(event.text) }
                    "citizenship" -> reduce { state.updateCitizenship(event.text) }
                    "address" -> reduce { state.updateAddress(event.text) }
                    "passport" -> reduce { state.updatePassport(event.text) }
                    "passportProvider" -> reduce { state.updatePassportProvider(event.text) }
                    "passportProviderCode" -> reduce { state.updatePassportProviderCode(event.text) }
                    "patent" -> reduce { state.updatePatent(event.text) }
                }
            }

            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "name" -> reduce { state.updateName("") }
                "email" -> reduce { state.updateEmail("") }
                "birthday" -> Unit
                "citizenship" -> reduce { state.updateCitizenship("") }
                "address" -> reduce { state.updateAddress("") }
                "passport" -> reduce { state.updatePassport("") }
                "passportProvider" -> reduce { state.updatePassportProvider("") }
                "passportProviderCode" -> reduce { state.updatePassportProviderCode("") }
                "patent" -> reduce { state.updatePatent("") }
            }
        }
        valid()
    }

    private fun valid() = intent {
        val valid = editUserValidUseCase(state.editUser).isSuccess
        reduce { state.updateSaveEnabled(valid) }
    }
}