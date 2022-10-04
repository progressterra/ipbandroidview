package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

private val iconSize: Dp = 20.dp
private val buttonSize: Dp = 40.dp

@Composable
fun LocationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier.size(buttonSize),
        onClick = onClick,
        backgroundColor = AppTheme.colors.primary
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            tint = AppTheme.colors.surfaces,
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = stringResource(id = R.string.my_location)
        )
    }
}

@Preview
@Composable
private fun LocationButtonPreview() {
    AppTheme {
        LocationButton(onClick = {})
    }
}
