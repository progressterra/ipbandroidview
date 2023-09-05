package com.progressterra.ipbandroidview.pages.wantthis

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class WantThisScreenNode(
    buildContext: BuildContext,
    navigation: WantThisScreenNavigation
) : AbstractNonInputNode<WantThisScreenNavigation, WantThisScreenState, WantThisScreenEffect, WantThisScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: WantThisScreenEffect) {
        when (effect) {
            is WantThisScreenEffect.Back -> navigation.onBack()
            is WantThisScreenEffect.Requests -> navigation.onWantThisRequests()
            is WantThisScreenEffect.OpenPhoto -> navigation.openPhoto(effect.data)
            is WantThisScreenEffect.Toast -> Toast.makeText(
                context,
                effect.data,
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<WantThisScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: WantThisScreenState) {
        WantThisScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }

}