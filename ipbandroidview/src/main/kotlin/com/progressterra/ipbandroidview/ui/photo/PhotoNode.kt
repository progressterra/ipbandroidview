package com.progressterra.ipbandroidview.ui.photo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.media.MultisizedImage
import com.progressterra.ipbandroidview.ui.checklist.ChecklistViewModel
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class PhotoNode(
    buildContext: BuildContext,
    private val picture: MultisizedImage,
    private val enabled: Boolean,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val checklistViewModel: ChecklistViewModel = getViewModel()
        val viewModel: PhotoViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is PhotoEffect.Back -> onBack()
                is PhotoEffect.Remove -> checklistViewModel.removePhoto(picture)
            }
        }
        var alreadyLaunched by rememberSaveable(picture, enabled) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setPhoto(picture, enabled)
        }
        val state = viewModel.collectAsState()
        PhotoScreen(
            state = state.value,
            back = viewModel::back,
            remove = viewModel::remove
        )
    }
}