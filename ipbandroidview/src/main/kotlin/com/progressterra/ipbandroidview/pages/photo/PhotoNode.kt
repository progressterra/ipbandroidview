package com.progressterra.ipbandroidview.pages.photo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.MultisizedImage
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class PhotoNode(
    buildContext: BuildContext, private val image: MultisizedImage, private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: PhotoViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is PhotoEvent.Back -> onBack()
            }
        }
        var alreadyLaunched by rememberSaveable(image) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setPhoto(image)
        }
        val state = viewModel.collectAsState()
        PhotoScreen(
            state = state.value, useComponent = viewModel
        )
    }
}