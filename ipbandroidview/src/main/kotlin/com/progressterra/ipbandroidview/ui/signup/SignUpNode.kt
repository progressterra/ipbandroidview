package com.progressterra.ipbandroidview.ui.signup

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
class SignUpNode(
    buildContext: BuildContext,
    private val onAddress: () -> Unit,
    private val onSkip: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: SignUpViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is SignUpEffect.NeedAddress -> onAddress()
                is SignUpEffect.Skip -> onSkip()
                is SignUpEffect.Toast -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        val state = viewModel.collectAsState()
        SignUpScreen(
            state = state.value,
            skip = viewModel::skip,
            next = viewModel::next,
            editBirthday = viewModel::editBirthday,
            editEmail = viewModel::editEmail,
            editName = viewModel::editName,
            openCalendar = viewModel::openCalendar,
            closeCalendar = viewModel::closeCalendar,
            refresh = viewModel::refresh
        )
    }
}