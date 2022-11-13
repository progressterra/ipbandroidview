package com.progressterra.ipbandroidview.components.bottombar

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.utils.SideBorder
import com.progressterra.ipbandroidview.components.utils.sideBorder
import com.progressterra.ipbandroidview.model.component.Price
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface CartBottomBarState : Price {

    val userExist: Boolean
}

@Composable
fun CartBottomBar(
    modifier: Modifier = Modifier,
    state: () -> CartBottomBarState,
    onNext: () -> Unit,
    onAuth: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.surfaces)
            .sideBorder(top = SideBorder(0.5.dp, AppTheme.colors.gray2))
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state().price, style = TextStyle(
                color = AppTheme.colors.black,
                fontSize = 23.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 27.6.sp
            )
        )
        if (state().userExist)
            ThemedButton(onClick = onNext, text = { stringResource(id = R.string.in_cart) })
        else
            ThemedButton(onClick = onAuth, text = { stringResource(id = R.string.go_to_auth) })
    }
}