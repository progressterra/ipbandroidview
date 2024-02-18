package com.progressterra.ipbandroidview.pages.fer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.pages.bankcards.BankCardsScreen
import com.progressterra.ipbandroidview.pages.bankcards.BankCardsScreenEffect
import com.progressterra.ipbandroidview.pages.bankcards.BankCardsScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Suppress("unused")
class FERNode(buildContext: BuildContext) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = koinViewModel<FERViewModel>()
        FERScreen(modifier = modifier, viewModel = viewModel)
    }
}