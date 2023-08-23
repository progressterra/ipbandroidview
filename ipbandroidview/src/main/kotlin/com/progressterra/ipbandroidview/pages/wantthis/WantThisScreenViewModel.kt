package com.progressterra.ipbandroidview.pages.wantthis

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButtonEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.docs.CreateAndSaveDocUseCase
import com.progressterra.ipbandroidview.processes.docs.DocsModule
import com.progressterra.ipbandroidview.processes.docs.DocsModuleUser
import com.progressterra.ipbandroidview.processes.docs.DocumentValidationUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.mvi.BaseViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class WantThisScreenViewModel(
    private val fetchWantThisUseCase: FetchWantThisUseCase,
    private val createAndSaveDocUseCase: CreateAndSaveDocUseCase,
    docsValidationUseCase: DocumentValidationUseCase,
    checkPermissionUseCase: CheckPermissionUseCase,
    askPermissionUseCase: AskPermissionUseCase,
    makePhotoUseCase: MakePhotoUseCase,
) : BaseViewModel<WantThisScreenState, WantThisScreenEvent>(), UseWantThisScreen {

    override fun createInitialState() = WantThisScreenState()

    private val docsModule = DocsModule(
        docsValidationUseCase,
        checkPermissionUseCase,
        askPermissionUseCase,
        makePhotoUseCase,
        this,
        object : DocsModuleUser {
            override fun isValid(isValid: Boolean) {
                emitState { it.copy(send = it.send.copy(enabled = isValid)) }
            }

            override fun openPhoto(url: String) {
                postEffect(WantThisScreenEvent.OpenPhoto(url))
            }

            override fun emitModuleState(reducer: (Document) -> Document) {
                emitState { it.copy(document = reducer(currentState.document)) }
            }

            override val moduleState: Document
                get() = currentState.document
        }
    )

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchWantThisUseCase().onSuccess { newDocument ->
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.SUCCESS)) }
                docsModule.setup(newDocument)
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: ProfileButtonEvent) {
        when (event.id) {
            "requests" -> postEffect(WantThisScreenEvent.Requests)
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(WantThisScreenEvent.Back)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "send" -> createAndSaveDocUseCase(
                    currentState.document,
                    IpbAndroidViewSettings.WANT_THIS_DOC_TYPE_ID
                ).onSuccess {
                    postEffect(WantThisScreenEvent.Toast(R.string.success))
                    postEffect(WantThisScreenEvent.Back)
                }.onFailure {
                    postEffect(WantThisScreenEvent.Toast(R.string.failure))
                }
            }
        }
    }

    override fun handle(event: DocumentPhotoEvent) {
        docsModule.handle(event)
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: TextFieldEvent) {
        docsModule.handle(event)
    }
}