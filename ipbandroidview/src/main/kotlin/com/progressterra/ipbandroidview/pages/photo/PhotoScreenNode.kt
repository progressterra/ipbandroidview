package com.progressterra.ipbandroidview.pages.photo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputNode
import com.progressterra.ipbandroidview.shared.mvi.OnBack
import org.koin.androidx.compose.getViewModel

interface PhotoScreenNavigation : OnBack

@Suppress("unused")
class PhotoScreenNode(
    buildContext: BuildContext,
    navigation: PhotoScreenNavigation,
    input: String
) : AbstractInputNode<String, PhotoScreenNavigation, PhotoScreenState, PhotoScreenEffect, PhotoScreenViewModel>(
    buildContext,
    navigation,
    input
) {

    override fun mapEffect(effect: PhotoScreenEffect) {
        when (effect) {
            is PhotoScreenEffect.Back -> navigation.onBack()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<PhotoScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: PhotoScreenState) {
        PhotoScreen(
            modifier = modifier, state = state, useComponent = viewModel
        )
    }
}