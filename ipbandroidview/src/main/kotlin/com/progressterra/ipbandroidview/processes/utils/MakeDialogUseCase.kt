package com.progressterra.ipbandroidview.processes.utils

import com.progressterra.ipbandroidview.R

interface MakeDialogUseCase {

    operator fun invoke(text: String, action: String, onAction: () -> Unit)

    fun auth(onAuth: () -> Unit)

    class Base(
        private val makeDialog: MakeDialogContract.Client,
        private val manageResources: ManageResources
    ) : MakeDialogUseCase {

        override fun invoke(text: String, action: String, onAction: () -> Unit) {
            makeDialog.start(
                text = text,
                action = action,
                onAction = onAction
            )
        }

        override fun auth(onAuth: () -> Unit) {
            makeDialog.start(
                text = manageResources.string(R.string.auth_dialog_text),
                action = manageResources.string(R.string.auth_button),
                onAction = onAuth
            )
        }
    }
}

