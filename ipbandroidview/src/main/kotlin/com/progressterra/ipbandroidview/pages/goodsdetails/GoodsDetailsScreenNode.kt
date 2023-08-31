package com.progressterra.ipbandroidview.pages.goodsdetails

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.progressterra.ipbandroidview.shared.mvi.AbstractInputNode
import org.koin.androidx.compose.getViewModel

@Suppress("unused")
class GoodsDetailsScreenNode(
    buildContext: BuildContext,
    navigation: GoodsDetailsScreenNavigation,
    input: String
) : AbstractInputNode<String, GoodsDetailsScreenNavigation, GoodsDetailsScreenState, GoodsDetailsScreenEffect, GoodsDetailsScreenViewModel>(
    buildContext,
    navigation,
    input
) {

    override fun mapEffect(effect: GoodsDetailsScreenEffect) {
        when (effect) {
            is GoodsDetailsScreenEffect.Back -> navigation.onBack()
            is GoodsDetailsScreenEffect.OpenImage -> navigation.openPhoto(effect.data)
            is GoodsDetailsScreenEffect.GoodsDetails -> navigation.openGoodsDetails(effect.data)
            is GoodsDetailsScreenEffect.Toast -> Toast.makeText(
                context,
                effect.data,
                Toast.LENGTH_SHORT
            ).show()

            is GoodsDetailsScreenEffect.Delivery -> navigation.onDelivery()
        }
    }

    @Composable
    override fun obtainViewModel() = getViewModel<GoodsDetailsScreenViewModel>()

    @Composable
    override fun Screen(modifier: Modifier, state: GoodsDetailsScreenState) {
        GoodsDetailsScreen(modifier = modifier, state = state, useComponent = viewModel)
    }
}