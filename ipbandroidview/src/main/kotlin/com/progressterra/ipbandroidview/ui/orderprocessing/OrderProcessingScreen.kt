package com.progressterra.ipbandroidview.ui.orderprocessing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.component.OrderProcessingComponent
import com.progressterra.ipbandroidview.composable.component.OrderProcessingComponentState

@Composable
fun OrderProcessingScreen(
    state: OrderProcessingComponentState,
    interactor: OrderProcessingInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(R.string.order), onBack = { interactor.onBack() })
    }, bottomBar = {
//        BottomHolder {
//            if (state.success) Button(
//                modifier = Modifier.fillMaxWidth(),
//                onClick = { interactor.onNext() },
//                text = stringResource(R.string.on_main)
//            )
//            else Button(
//                modifier = Modifier.fillMaxWidth(),
//                onClick = { interactor.onBack() },
//                text = stringResource(R.string.repeat_payment)
//            )
//        }
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            OrderProcessingComponent(
                modifier = Modifier.fillMaxWidth(), state = state
            )
        }
    }
}