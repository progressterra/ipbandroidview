package com.progressterra.ipbandroidview.pages.photo

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
class PhotoNode(
    buildContext: BuildContext, private val image: String, private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: PhotoViewModel = getViewModel()
        viewModel.collectEffects {
            when (it) {
                is PhotoEvent.Back -> onBack()
            }
        }
        var alreadyLaunched by rememberSaveable(image) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setup(image)
        }
        val state = viewModel.state.value
        PhotoScreen(
            state = state, useComponent = viewModel
        )
    }
}