package com.progressterra.ipbandroidview.ui.orderprocessing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.component.OrderProcessingWidget
import com.progressterra.ipbandroidview.composable.component.ThemedLayout
import com.progressterra.ipbandroidview.composable.component.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.element.BottomHolder
import com.progressterra.ipbandroidview.composable.element.ThemedButton
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun OrderProcessingScreen(
    state: () -> OrderProcessingState,
    onNext: () -> Unit,
    onBack: () -> Unit
) {
    ThemedLayout(
        topBar = {
            ThemedTopAppBar(title = stringResource(R.string.order), onBack = onBack)
        },
        bottomBar = {
            BottomHolder {
                if (state().orderResult.success)
                    ThemedButton(onClick = onNext, text = stringResource(R.string.on_main))
                else
                    ThemedButton(onClick = onBack, text = stringResource(R.string.repeat_payment))
            }
        }
    ) { _, _ ->
        Column(modifier = Modifier.padding(AppTheme.dimensions.small)) {
            OrderProcessingWidget(
                modifier = Modifier.fillMaxWidth(),
                state = state()::orderResult
            )
        }
    }
}