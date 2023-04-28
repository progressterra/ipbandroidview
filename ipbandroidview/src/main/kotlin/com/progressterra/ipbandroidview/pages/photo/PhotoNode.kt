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
import com.progressterra.ipbandroidview.shared.nav.Back
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

interface PhotoNodeContract : Back {

    fun enabled(): Boolean

    fun picture(): MultisizedImage

    data class Base(
        private val enabled: Boolean,
        private val picture: MultisizedImage,
        private val onBack: () -> Unit,
        private val onRemove: () -> Unit
    ) : PhotoNodeContract {

        override fun enabled(): Boolean = enabled

        override fun picture(): MultisizedImage = picture

        override fun back() = onBack()
    }
}

@Suppress("unused")
class PhotoNode(
    buildContext: BuildContext,
    private val contract: PhotoNodeContract
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: PhotoViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is PhotoEvent.Back -> contract.back()
            }
        }
        var alreadyLaunched by rememberSaveable(contract) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setPhoto(contract.picture())
        }
        val state = viewModel.collectAsState()
        PhotoScreen(
            state = state.value,
            useComponent = viewModel
        )
    }
}