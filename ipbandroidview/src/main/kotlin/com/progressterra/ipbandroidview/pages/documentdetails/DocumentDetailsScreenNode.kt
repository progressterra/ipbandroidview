package com.progressterra.ipbandroidview.pages.documentdetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class DocumentDetailsScreenNode(
    buildContext: BuildContext,
    navigation: DocumentDetailsScreenNavigation,
    input: Document
) : AbstractInputNode<Document, DocumentDetailsScreenNavigation, DocumentDetailsScreenState, DocumentDetailsScreenEffect, DocumentDetailsViewModel>(
    buildContext,
    navigation,
    input
) {

    override fun mapEffect(effect: DocumentDetailsScreenEffect) {
        when (effect) {
            is DocumentDetailsScreenEffect.Back -> navigation.onBack()
            is DocumentDetailsScreenEffect.OpenPhoto -> navigation.openPhoto(effect.data)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<DocumentDetailsViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: DocumentDetailsScreenState) {
        DocumentDetailsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}

