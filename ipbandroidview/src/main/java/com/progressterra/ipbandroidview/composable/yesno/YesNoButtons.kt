package com.progressterra.ipbandroidview.composable.yesno

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun YesNoButton(
    modifier: Modifier = Modifier,
    state: YesNoState,
    onClick: (Boolean) -> Unit,
    gap: Dp = 12.dp,
    cornerRadius: Dp = 14.dp,
    horizontalPadding: Dp = 32.dp,
    verticalPadding: Dp = 15.dp
) {

    @Composable
    fun YesNo(
        modifier: Modifier, text: String, color: Color, active: Boolean, onClick: () -> Unit
    ) {
        Row(modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(if (active) color else AppTheme.colors.gray3)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true)
            ) { onClick() }
            .padding(horizontal = horizontalPadding, vertical = verticalPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text(
                text = text,
                style = AppTheme.typography.button,
                color = if (active) AppTheme.colors.surfaces else AppTheme.colors.gray2
            )
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        YesNo(modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.yes),
            color = AppTheme.colors.primary,
            active = state == YesNoState.YES,
            onClick = { onClick(true) })
        Spacer(modifier = Modifier.size(gap))
        YesNo(modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.no),
            color = AppTheme.colors.error,
            active = state == YesNoState.NO,
            onClick = { onClick(false) })
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreview() {
    AppTheme {
        YesNoButton(state = YesNoState.NONE, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreviewYes() {
    AppTheme {
        YesNoButton(state = YesNoState.YES, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreviewNo() {
    AppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            YesNoButton(modifier = Modifier.fillMaxWidth(), state = YesNoState.NO, onClick = {})
        }
    }
}