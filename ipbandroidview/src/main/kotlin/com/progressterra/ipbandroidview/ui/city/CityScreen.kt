package com.progressterra.ipbandroidview.ui.city

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun CityScreen(
    state: CityState, interactor: CityInteractor, canBeSkipped: Boolean
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.address),
            onBack = { interactor.onBack() })
    }, bottomBar = {
//        BottomHolder {
//            Button(
//                modifier = Modifier.fillMaxWidth(),
//                onClick = { interactor.onNext() },
//                text = stringResource(id = R.string.ready),
//                enabled = state.isDataValid
//            )
//            if (canBeSkipped) {
//                Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
//                TextButton(
//                    modifier = Modifier.fillMaxWidth(),
//                    onClick = { interactor.onSkip() },
//                    text = stringResource(id = R.string.auth_skip)
//                )
//            }
//        }
    }) { _, _ ->
//        Map(state = state.mapState, onEvent = { })
    }
}

@Preview
@Composable
private fun CityScreenPreview() {
    IpbTheme {}
}