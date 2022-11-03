package com.progressterra.ipbandroidview.components.yesno

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun YesNoButton(
    modifier: Modifier = Modifier,
    yesNo: YesNo,
    onClick: (Boolean) -> Unit,
    enabled: Boolean = true
) {

    @Composable
    fun Button(
        modifier: Modifier,
        text: String,
        activeColor: Color,
        baseColor: Color,
        active: Boolean,
        onClick: () -> Unit
    ) {
        Row(modifier = modifier
            .clip(AppTheme.shapes.button)
            .background(if (active) activeColor else baseColor)
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true)
            ) { onClick() }
            .padding(
                horizontal = 32.dp,
                vertical = 15.dp
            ),
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
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.yes),
            activeColor = AppTheme.colors.primary,
            active = yesNo == YesNo.YES,
            baseColor = if (yesNo == YesNo.NO) AppTheme.colors.gray3 else Color(0xFFA0ECAC),
            onClick = { onClick(true) })
        Button(modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.no),
            activeColor = AppTheme.colors.error,
            active = yesNo == YesNo.NO,
            baseColor = if (yesNo == YesNo.YES) AppTheme.colors.gray3 else Color(0xFFF5B5B5),
            onClick = { onClick(false) })
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreview() {
    AppTheme {
        YesNoButton(yesNo = YesNo.NONE, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreviewYes() {
    AppTheme {
        YesNoButton(yesNo = YesNo.YES, onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreviewNo() {
    AppTheme {
        YesNoButton(
            modifier = Modifier.fillMaxWidth(),
            yesNo = YesNo.NO,
            onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun YesNoButtonPreviewDisabled() {
    AppTheme {
        YesNoButton(
            modifier = Modifier.fillMaxWidth(),
            yesNo = YesNo.NO,
            onClick = {},
            enabled = false
        )
    }
}