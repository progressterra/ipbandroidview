package com.progressterra.ipbandroidview.pages.locationpermission

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button

@Composable
fun LocationPermissionScreen(
    modifier: Modifier = Modifier,
    state: LocationPermissionScreenState,
    useComponent: UseLocationPermissionScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = stringResource(R.string.location),
            showBackButton = true,
            useComponent = useComponent
        )
    }, bottomBar = {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                state = state.give,
                useComponent = useComponent,
                title = stringResource(R.string.give_access)
            )
        }
    }) { _, _ ->
        Column(
            modifier = Modifier.padding(top = 36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BrushedText(
                modifier = Modifier.padding(start = 34.dp, end = 34.dp),
                text = stringResource(R.string.location_welcome),
                style = IpbTheme.typography.largeTitle,
                tint = IpbTheme.colors.textPrimary.asBrush(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(63.dp))
            BrushedIcon(
                resId = R.drawable.ic_location,
                tint = IpbTheme.colors.iconPrimary.asBrush()
            )
            Spacer(modifier = Modifier.height(12.dp))
            BrushedText(
                modifier = Modifier.padding(start = 53.dp, end = 53.dp),
                text = stringResource(R.string.location_reasoning),
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush(),
                textAlign = TextAlign.Center
            )
        }
    }

}

@Preview
@Composable
private fun LocationPermissionScreenPreview() {
    IpbTheme {
        LocationPermissionScreen(
            state = LocationPermissionScreenState(),
            useComponent = UseLocationPermissionScreen.Empty()
        )
    }
}
