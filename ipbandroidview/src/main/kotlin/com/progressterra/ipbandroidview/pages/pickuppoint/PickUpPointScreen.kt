package com.progressterra.ipbandroidview.pages.pickuppoint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.widgets.pickupchoose.PickUpChoose
import com.progressterra.ipbandroidview.widgets.pickupchoose.PickUpChooseState

@Composable
fun PickUpPointScreen(
    state: PickUpPointState, useComponent: UsePickUpPoint
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(id = R.string.pick_up_point),
            showBackButton = true,
            useComponent = useComponent
        )
    }, bottomBar = {
        Column(
            modifier = Modifier
                .padding(vertical = 40.dp, horizontal = 20.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                state = state.confirm,
                useComponent = useComponent,
                title = stringResource(R.string.choose)
            )
        }
    }) { _, _ ->
        PickUpChoose(
            state = state.choose,
            useComponent = useComponent
        )
    }
}

@Preview
@Composable
private fun PickUpPointScreenPreview() {
    IpbTheme {
        PickUpPointScreen(
            state = PickUpPointState(
                choose = PickUpChooseState(
                    isPermissionGranted = false,
                    pickUpPoints = listOf(),
                    currentPickUpPointInfo = null
                ), confirm = ButtonState(
                    id = "id",
                    enabled = false
                )

            ), useComponent = UsePickUpPoint.Empty()
        )
    }
}