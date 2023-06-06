package com.progressterra.ipbandroidview.pages.profiledetails

import android.Manifest
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.authprofile.AuthProfileEvent
import com.progressterra.ipbandroidview.features.citizenshipsuggestions.CitizenshipSuggestionsEvent
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
import com.progressterra.ipbandroidview.features.makephoto.uMakePhotoEnabled
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.uEnabled
import com.progressterra.ipbandroidview.shared.ui.textfield.uText
import com.progressterra.ipbandroidview.widgets.edituser.CitizenshipSuggestionsUseCase
import com.progressterra.ipbandroidview.widgets.edituser.EditUserValidUseCase
import com.progressterra.ipbandroidview.widgets.edituser.FetchAdaptiveEntriesUseCase
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
    private val suggestionsUseCase: CitizenshipSuggestionsUseCase,
    private val makePhotoUseCase: MakePhotoUseCase,
    private val fetchAdaptiveEntriesUseCase: FetchAdaptiveEntriesUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase
) : ViewModel(),
    ContainerHost<ProfileDetailsState, ProfileDetailsEvent>, UseProfileDetails {

    override val container =
        container<ProfileDetailsState, ProfileDetailsEvent>(ProfileDetailsState())

    fun refresh() {
        intent {
            reduce { ProfileDetailsState() }
            fetchUserUseCase().onSuccess {
                reduce { state.uEditUser(it).uScreenState(ScreenState.SUCCESS) }
            }.onFailure { reduce { state.uScreenState(ScreenState.ERROR) } }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            when (event) {
                is StateBoxEvent.Refresh -> refresh()
            }
        }
    }

    override fun handle(event: AuthProfileEvent) {
        intent {
            when (event) {
                is AuthProfileEvent.Click -> Unit
            }
        }
    }


    override fun handle(event: ButtonEvent) {
        intent {
            when (event) {
                is ButtonEvent.Click -> when {
                    event.id == "save" -> saveUseCase(state.editUser).onSuccess {
                        reduce {
                            state.startCancelEdit()
                                .uCitizenshipEnabled(false)
                                .uEmailEnabled(false)
                                .uNameEnabled(false)
                                .uBirthdayEnabled(false)
                        }
                        state.editUser.adaptiveDocuments.forEach {
                            reduce {
                                state.updateById(it) { edit ->
                                    edit.copy(
                                        text = edit.text.uEnabled(false),
                                        makePhoto = edit.makePhoto?.uMakePhotoEnabled(false)
                                    )
                                }
                            }
                        }
                    }

                    event.id == "edit" -> {
                        reduce {
                            state.startCancelEdit()
                                .uCitizenshipEnabled(true)
                                .uEmailEnabled(true)
                                .uNameEnabled(true)
                                .uBirthdayEnabled(true)
                        }
                        state.editUser.adaptiveDocuments.forEach {
                            reduce {
                                state.updateById(it) { edit ->
                                    edit.copy(
                                        text = edit.text.uEnabled(true),
                                        makePhoto = edit.makePhoto?.uMakePhotoEnabled(true)
                                    )
                                }
                            }
                        }
                    }

                    event.id == "cancel" -> refresh()

                    event.id.startsWith("makePhoto") -> {
                        val trueId = event.id.substring(9)
                        checkPermissionUseCase(Manifest.permission.CAMERA).onSuccess {
                            makePhotoUseCase(trueId).onSuccess {
                                reduce {
                                    state.updateById(it) { entry ->
                                        entry.copy(makePhoto = entry.makePhoto?.add(it))
                                    }
                                }
                            }
                        }.onFailure {
                            askPermissionUseCase(Manifest.permission.CAMERA)
                        }
                    }
                }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            when (event) {
                is TopBarEvent.Back -> postSideEffect(ProfileDetailsEvent.Back)
            }
        }
    }

    override fun handle(event: MakePhotoEvent) {
        intent {
            when (event) {
                is MakePhotoEvent.Remove -> reduce {
                    state.updateById(event.photo) {
                        it.copy(makePhoto = it.makePhoto?.remove(event.photo))
                    }
                }

                is MakePhotoEvent.Select -> postSideEffect(ProfileDetailsEvent.OpenPhoto(event.photo.fullSize))
            }
        }
    }


    override fun handle(event: TextFieldEvent) {
        blockingIntent {
            when (event) {
                is TextFieldEvent.TextChanged -> {
                    when (event.id) {
                        "name" -> reduce { state.uName(event.text) }
                        "email" -> reduce { state.uEmail(event.text) }
                        "birthday" -> reduce { state.uBirthday(event.text) }
                        "citizenship" -> {
                            reduce { state.uCitizenship(event.text) }
                            updateSuggestions()
                        }

                        else -> reduce {
                            state.updateById(event) {
                                it.copy(text = it.text.uText(event.text))
                            }
                        }
                    }
                }

                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> when (event.id) {
                    "name" -> reduce { state.uName("") }
                    "email" -> reduce { state.uEmail("") }
                    "birthday" -> Unit
                    "citizenship" -> reduce { state.uCitizenship("") }
                }
            }
            valid()
        }
    }

    override fun handle(event: CitizenshipSuggestionsEvent) {
        intent {
            when (event) {
                is CitizenshipSuggestionsEvent.Click -> {
                    reduce { state.uCitizenship(citizenship = state.editUser.suggestions.suggestion) }
                    updateEntries()
                }
            }
        }
    }

    private fun updateEntries() {
        intent {
            fetchAdaptiveEntriesUseCase(state.editUser.suggestions.id).onSuccess {
                reduce { state.uDocuments(it) }
            }
        }
    }

    private fun updateSuggestions() {
        intent {
            suggestionsUseCase(state.editUser.citizenship.text).onSuccess {
                reduce { state.uSuggestions(it) }
                if (it.exact) {
                    updateEntries()
                }
            }
        }
    }

    private fun valid() {
        intent {
            val valid = editUserValidUseCase(state.editUser).isSuccess
            reduce { state.uSaveEnabled(valid) }
        }
    }
}