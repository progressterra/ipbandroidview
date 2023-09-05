package com.progressterra.ipbandroidview.pages.documents

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class DocumentsScreenNode(
    buildContext: BuildContext,
    navigation: DocumentsScreenNavigation
) : AbstractNonInputNode<DocumentsScreenNavigation, DocumentsScreenState, DocumentsScreenEffect, DocumentsScreenViewModel>(
    buildContext,
    navigation
) {

    override fun mapEffect(effect: DocumentsScreenEffect) {
        when (effect) {
            is DocumentsScreenEffect.Back -> navigation.onBack()
            is DocumentsScreenEffect.OpenDocument -> navigation.onDocument(effect.data)
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<DocumentsScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: DocumentsScreenState) {
        DocumentsScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}

