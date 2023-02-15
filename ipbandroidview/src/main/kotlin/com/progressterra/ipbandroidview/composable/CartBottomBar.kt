package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.utils.SideBorder
import com.progressterra.ipbandroidview.composable.utils.sideBorder
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.theme.AppTheme

private val lineWidth = 0.5.dp

@Composable
fun CartBottomBar(
    modifier: Modifier = Modifier,
    userExist: Boolean,
    totalPrice: SimplePrice,
    onNext: () -> Unit,
    onAuth: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.surfaces)
            .sideBorder(top = SideBorder(lineWidth, AppTheme.colors.gray2))
            .padding(AppTheme.dimensions.large),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = totalPrice.toString(),
            style = AppTheme.typography.price,
            color = AppTheme.colors.black
        )
        if (userExist) ThemedButton(
            onClick = onNext, text = stringResource(id = R.string.checkout)
        )
        else ThemedButton(
            onClick = onAuth, text = stringResource(id = R.string.go_to_auth)
        )
    }
}


@Preview
@Composable
private fun CartBottomBarPreview() {
    AppTheme {
        CartBottomBar(userExist = true,
            totalPrice = SimplePrice(3000),
            onNext = {},
            onAuth = {})
    }
}

@Preview
@Composable
private fun CartBottomBarPreviewUnAuth() {
    AppTheme {
        CartBottomBar(userExist = false,
            totalPrice = SimplePrice(3000),
            onNext = {},
            onAuth = {})
    }
}