package com.progressterra.ipbandroidview.pages.goodsdetails

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class GoodsDetailsScreenNode(
    buildContext: BuildContext,
    private val navigation: GoodsDetailsScreenNavigation,
    private val input: String
) : Node(
    buildContext
) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<GoodsDetailsScreenViewModel>()
        val context = LocalContext.current
        viewModel.collectEffects { effect ->
            when (effect) {
                is GoodsDetailsScreenEffect.Back -> navigation.onBack()
                is GoodsDetailsScreenEffect.OpenImage -> navigation.onPhoto(effect.data)
                is GoodsDetailsScreenEffect.GoodsDetails -> navigation.onGoodsDetails(effect.data)
                is GoodsDetailsScreenEffect.Toast -> Toast.makeText(
                    context,
                    effect.data,
                    Toast.LENGTH_SHORT
                ).show()
                is GoodsDetailsScreenEffect.OnAuth -> navigation.onAuth()

                is GoodsDetailsScreenEffect.Delivery -> navigation.onDelivery()
            }
        }
        val state = viewModel.state.collectAsState().value
        LaunchedEffect(input) {
            viewModel.setup(input)
        }
        GoodsDetailsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}