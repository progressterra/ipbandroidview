package com.progressterra.ipbandroidview.features.currentcitizenship

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun CurrentCitizenship(
    modifier: Modifier = Modifier,
    state: CurrentCitizenshipState,
    useComponent: UseCurrentCitizenship
) {
    Column(modifier = modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(8.dp))
        .background(IpbTheme.colors.surface.asBrush())
        .niceClickable { useComponent.handle(CurrentCitizenshipEvent.Click) }
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)) {
        if (state.citizenship == null) {
            BrushedText(
                text = stringResource(R.string.citizenship),
                tint = IpbTheme.colors.textDisabled.asBrush(),
                style = IpbTheme.typography.body
            )
        } else {
            BrushedText(
                text = stringResource(R.string.citizenship),
                tint = IpbTheme.colors.textTertiary.asBrush(),
                style = IpbTheme.typography.caption
            )
            BrushedText(
                text = state.citizenship.name,
                tint = IpbTheme.colors.textPrimary.asBrush(),
                style = IpbTheme.typography.body
            )
        }
    }
}

@Preview
@Composable
private fun CurrentCitizenshipPreview() {
    IpbTheme {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            CurrentCitizenship(
                state = CurrentCitizenshipState(
                    citizenship = Citizenship(
                        name = "Albania", id = ""
                    )
                ), useComponent = UseCurrentCitizenship.Empty()
            )
            CurrentCitizenship(
                state = CurrentCitizenshipState(), useComponent = UseCurrentCitizenship.Empty()
            )
        }
    }
}
