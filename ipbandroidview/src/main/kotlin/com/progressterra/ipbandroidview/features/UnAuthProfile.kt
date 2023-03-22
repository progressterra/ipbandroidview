package com.progressterra.ipbandroidview.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

@Immutable
data class UnAuthProfileState(
    val auth: ButtonState = ButtonState()
)

interface UseUnAuthProfile : UseButton

@Composable
fun UnAuthProfile(
    modifier: Modifier = Modifier,
    state: UnAuthProfileState,
    useComponent: UseUnAuthProfile
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.surface1.asBrush())
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BrushedText(
            text = stringResource(R.string.please_auth),
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary1.asBrush()
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.auth,
            useComponent = useComponent
        )
    }
}