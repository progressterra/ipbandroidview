package com.progressterra.ipbandroidview.features.unauthprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.button.Button

@Composable
fun UnAuthProfile(
    modifier: Modifier = Modifier,
    state: UnAuthProfileState,
    useComponent: UseUnAuthProfile
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrushedText(
            text = stringResource(R.string.please_auth),
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary.asBrush()
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.authorization),
            state = state.auth,
            useComponent = useComponent
        )
    }
}