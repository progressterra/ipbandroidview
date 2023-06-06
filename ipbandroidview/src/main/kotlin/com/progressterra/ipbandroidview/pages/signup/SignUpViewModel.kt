package com.progressterra.ipbandroidview.pages.signup

import android.Manifest
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.citizenshipsuggestions.CitizenshipSuggestionsEvent
import com.progressterra.ipbandroidview.features.makephoto.MakePhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
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
class SignUpViewModel(
    private val editUserValidUseCase: EditUserValidUseCase,
    private val saveDataUseCase: SaveDataUseCase,
    private val makePhotoUseCase: MakePhotoUseCase,
    private val fetchUserUseCase: FetchUserUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val fetchAdaptiveEntriesUseCase: FetchAdaptiveEntriesUseCase,
    private val citizenshipSuggestionsUseCase: CitizenshipSuggestionsUseCase,
    private val askPermissionUseCase: AskPermissionUseCase
) : ViewModel(), ContainerHost<SignUpState, SignUpEvent>, UseSignUp {

    override val container = container<SignUpState, SignUpEvent>(SignUpState())

    fun refresh() {
        intent {
            reduce { state.uScreenState(ScreenState.LOADING) }
            fetchUserUseCase().onSuccess {
                reduce {
                    state.uEditUser(it).uPhoneEnabled(false).uScreenState(ScreenState.SUCCESS)
                }
            }.onFailure {
                reduce { state.uScreenState(ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            when (event) {
                is TopBarEvent.Back -> postSideEffect(SignUpEvent.OnBack)
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event) {
                is ButtonEvent.Click -> when {
                    event.id == "auth" -> saveDataUseCase(state.editUser).onSuccess {
                        postSideEffect(SignUpEvent.OnNext)
                    }

                    event.id == "skip" -> postSideEffect(SignUpEvent.OnSkip)

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

    override fun handle(event: MakePhotoEvent) {
        intent {
            when (event) {
                is MakePhotoEvent.Remove -> reduce {
                    state.updateById(event.photo) {
                        it.copy(makePhoto = it.makePhoto?.remove(event.photo))
                    }
                }

                is MakePhotoEvent.Select -> postSideEffect(SignUpEvent.OpenPhoto(event.photo.url))
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
            citizenshipSuggestionsUseCase(state.editUser.citizenship.text).onSuccess {
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
            reduce { state.uAuthEnabled(valid) }
        }
    }
}