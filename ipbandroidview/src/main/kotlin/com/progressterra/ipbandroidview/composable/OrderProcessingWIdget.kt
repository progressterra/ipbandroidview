package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.model.OrderResult
import com.progressterra.ipbandroidview.theme.AppTheme

private val verticalPadding = 80.dp

@Composable
fun OrderProcessingWidget(
    modifier: Modifier,
    state: OrderResult
) {
    Column(
        modifier = modifier
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(vertical = verticalPadding, horizontal = AppTheme.dimensions.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (state.success) stringResource(R.string.success_payment) else stringResource(R.string.failed_payment),
            color = if (state.success) AppTheme.colors.primary else AppTheme.colors.error,
            style = AppTheme.typography.title
        )
        Spacer(modifier = Modifier.size(AppTheme.dimensions.medium))
        Text(
            text = state.additionalInfo,
            color = AppTheme.colors.gray2,
            style = AppTheme.typography.secondaryText
        )
    }
}
