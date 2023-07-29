package com.progressterra.ipbandroidview.pages.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class MainNode(
    buildContext: BuildContext,
    private val onBonuses: () -> Unit,
    private val onItem: (String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<MainViewModel>()
        viewModel.collectEffects {
            when (it) {
                is MainEvent.OnBonuses -> onBonuses()
                is MainEvent.OnItem -> onItem(it.id)
            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh()
        }
        val state = viewModel.state.value
        MainScreen(
            state = state, useComponent = viewModel
        )
    }
}