package com.progressterra.ipbandroidview.ui.signin

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class SignInNode(
    buildContext: BuildContext,
    private val onNext: (phoneNumber: String) -> Unit,
    private val onSkip: (() -> Unit)? = null,
    private val onAgreement: () -> Unit,
    private val canBeSkipped: Boolean,
    private val offerUrl: String,
    private val policyUrl: String
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SignInViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is SignInEffect.Next -> onNext(it.phoneNumber)
                is SignInEffect.Skip -> onSkip?.invoke()
                is SignInEffect.Agreement -> onAgreement()
                is SignInEffect.Toast -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        val state = viewModel.collectAsState()
        SignInScreen(
            state = state.value,
            interactor = viewModel,
            canBeSkipped = canBeSkipped,
            offerUrl = offerUrl,
            policyUrl = policyUrl
        )
    }
}