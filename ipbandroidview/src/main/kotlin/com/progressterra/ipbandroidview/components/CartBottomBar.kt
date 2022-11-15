package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.utils.SideBorder
import com.progressterra.ipbandroidview.components.utils.sideBorder
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.model.component.Price
import com.progressterra.ipbandroidview.theme.AppTheme

private val lineWidth = 0.5.dp

@Immutable
interface CartBottomBarState : Price {

    val userExist: Boolean
}

@Composable
fun CartBottomBar(
    modifier: Modifier = Modifier,
    screenState: () -> ScreenState,
    state: () -> CartBottomBarState,
    onNext: () -> Unit,
    onAuth: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.surfaces)
            .sideBorder(top = SideBorder(lineWidth, AppTheme.colors.gray2))
            .padding(
                start = AppTheme.dimensions.large,
                top = AppTheme.dimensions.large,
                end = AppTheme.dimensions.large,
                bottom = AppTheme.dimensions.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state().price, style = AppTheme.typography.price, color = AppTheme.colors.black
        )
        if (state().userExist)
            ThemedButton(
                onClick = onNext,
                text = stringResource(id = R.string.in_cart),
                enabled = screenState()::isSuccess
            )
        else
            ThemedButton(
                onClick = onAuth,
                text = stringResource(id = R.string.go_to_auth),
                enabled = screenState()::isSuccess
            )
    }
}