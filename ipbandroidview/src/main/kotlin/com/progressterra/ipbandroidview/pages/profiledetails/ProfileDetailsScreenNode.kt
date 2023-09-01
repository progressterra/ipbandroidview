package com.progressterra.ipbandroidview.pages.profiledetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class ProfileDetailsScreenNode(
    buildContext: BuildContext,
    navigation: ProfileDetailsScreenNavigation
) : AbstractNonInputNode<ProfileDetailsScreenNavigation, ProfileDetailsState, ProfileDetailsScreenEffect, ProfileDetailsScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: ProfileDetailsScreenEffect) {
        when (effect) {
            is ProfileDetailsScreenEffect.Back -> navigation.onBack()
            is ProfileDetailsScreenEffect.OpenPhoto -> navigation.openPhoto(effect.data)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<ProfileDetailsScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: ProfileDetailsState) {
        ProfileDetailsScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}