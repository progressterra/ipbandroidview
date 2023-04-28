package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
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
    private val editUserValidUseCase: EditUserValidUseCase,
    private val makePhotoUseCase: MakePhotoUseCase
) : ViewModel(),
    ContainerHost<ProfileDetailsState, ProfileDetailsEvent>, UseProfileDetails {

    override val container =
        container<ProfileDetailsState, ProfileDetailsEvent>(ProfileDetailsState())

    fun refresh() = intent {
        reduce { ProfileDetailsState() }
        fetchUserUseCase().onSuccess {
            reduce { state.uEditUser(it) }
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
                        .uPassportProviderEnabled(true)
                        .uPassportEnabled(true)
                        .uPassportProviderCodeEnabled(true)
                        .uCitizenshipEnabled(true)
                        .uEmailEnabled(true)
                        .uNameEnabled(true)
                        .uBirthdayEnabled(true)
                        .uAddressEnabled(true)
                        .uPatentEnabled(true)
                }

                "save" -> saveUseCase(state.editUser).onSuccess {
                    reduce {
                        state.startCancelEdit()
                            .uPassportProviderEnabled(false)
                            .uPassportEnabled(false)
                            .uPassportProviderCodeEnabled(false)
                            .uCitizenshipEnabled(false)
                            .uEmailEnabled(false)
                            .uNameEnabled(false)
                            .uBirthdayEnabled(false)
                            .uAddressEnabled(false)
                            .uPatentEnabled(false)
                    }
                }

                "cancel" -> refresh()

                "makePhoto" -> makePhotoUseCase().onSuccess {
                    reduce { state.addPhoto(it) }
                }
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
                    "name" -> reduce { state.uName(event.text) }
                    "email" -> reduce { state.uEmail(event.text) }
                    "birthday" -> reduce { state.uBirthday(event.text) }
                    "citizenship" -> reduce { state.uCitizenship(event.text) }
                    "address" -> reduce { state.uAddress(event.text) }
                    "passport" -> if (event.text.length <= 10) reduce { state.uPassport(event.text) }
                    "passportProvider" -> reduce { state.uPassportProvider(event.text) }
                    "passportProviderCode" -> if (event.text.length <= 6) reduce {
                        state.uPassportProviderCode(
                            event.text
                        )
                    }

                    "patent" -> reduce { state.uPatent(event.text) }
                }
            }

            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "name" -> reduce { state.uName("") }
                "email" -> reduce { state.uEmail("") }
                "birthday" -> Unit
                "citizenship" -> reduce { state.uCitizenship("") }
                "address" -> reduce { state.uAddress("") }
                "passport" -> reduce { state.uPassport("") }
                "passportProvider" -> reduce { state.uPassportProvider("") }
                "passportProviderCode" -> reduce { state.uPassportProviderCode("") }
                "patent" -> reduce { state.uPatent("") }
            }
        }
        valid()
    }

    override fun handle(event: MakePhotoEvent) = intent {
        when (event) {
            is MakePhotoEvent.Remove -> reduce { state.removePhoto(event.photo) }
            is MakePhotoEvent.Select -> Unit
        }
    }

    private fun valid() = intent {
        val valid = editUserValidUseCase(state.editUser).isSuccess
        reduce { state.uSaveEnabled(valid) }
    }
}