package com.progressterra.ipbandroidview.pages.pickuppoint

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.widgets.pickupchoose.PickUpChoose

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
                .shadow(6.dp)
        ) {
            Button(
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