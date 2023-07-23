package com.progressterra.ipbandroidview.features.pfppicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun PfpPicker(
    modifier: Modifier = Modifier, state: PfpPickerState, useComponent: UsePfpPicker
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(263.dp)
            .clip(CircleShape)
            .niceClickable { useComponent.handle(PfpPickerEvent) }
            .background(IpbTheme.colors.surface.asBrush()), contentAlignment = Alignment.Center) {
            if (state.url == null) {
                BrushedIcon(
                    resId = R.drawable.ic_add_avatar, tint = IpbTheme.colors.primary.asBrush()
                )
            } else {
                SimpleImage(
                    modifier = Modifier.size(263.dp),
                    image = state.url,
                    backgroundColor = IpbTheme.colors.background.asColor()
                )
            }
        }
        BrushedText(
            text = stringResource(R.string.choose_photo_wisely),
            style = IpbTheme.typography.body,
            tint = IpbTheme.colors.textPrimary.asBrush(),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PfpPickerPreview() {
    IpbTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            PfpPicker(
                state = PfpPickerState(url = ""),
                useComponent = UsePfpPicker.Empty()
            )
            PfpPicker(
                state = PfpPickerState(), useComponent = UsePfpPicker.Empty()
            )
        }
    }
}