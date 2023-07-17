package com.progressterra.ipbandroidview.pages.goodsdetails

import android.widget.Toast
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class GoodsDetailsNode(
    buildContext: BuildContext,
    private val goodsId: String,
    private val onBack: () -> Unit,
    private val openPhoto: (String) -> Unit,
    private val onGoodsDetails: (String) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<GoodsDetailsViewModel>()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is GoodsDetailsEvent.Back -> onBack()
                is GoodsDetailsEvent.OpenImage -> openPhoto(it.image)
                is GoodsDetailsEvent.Refresh -> viewModel.refresh(goodsId)
                is GoodsDetailsEvent.GoodsDetails -> onGoodsDetails(it.id)
                is GoodsDetailsEvent.Toast -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        var alreadyLaunched by rememberSaveable {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.refresh(goodsId)
        }
        val state = viewModel.collectAsState().value
        GoodsDetailsScreen(state = state, useComponent = viewModel)
    }
}