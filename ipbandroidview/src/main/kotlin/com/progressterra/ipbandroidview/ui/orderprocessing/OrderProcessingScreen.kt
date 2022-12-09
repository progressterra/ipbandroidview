package com.progressterra.ipbandroidview.ui.orderprocessing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.OrderProcessingWidget
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrderProcessingScreen(
    state: () -> OrderProcessingState, onNext: () -> Unit, onBack: () -> Unit
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(R.string.order), onBack = onBack)
    }, bottomBar = {
        BottomHolder {
            if (state().orderResult.success) ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onNext,
                text = stringResource(R.string.on_main)
            )
            else ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onBack,
                text = stringResource(R.string.repeat_payment)
            )
        }
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.small)
        ) {
            OrderProcessingWidget(
                modifier = Modifier.fillMaxWidth(), state = state()::orderResult
            )
        }
    }
}