package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.progressterra.ipbandroidview.model.Notification
import com.progressterra.ipbandroidview.theme.AppTheme

//TODO remove all raw lists from composables

@Immutable
interface NotificationsState {

    val notifications: List<Notification>
}

private val horizontalPadding: Dp = 8.dp

private val height = 130.dp

private val qrSize = 114.dp

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Notifications(
    modifier: Modifier = Modifier,
    state: () -> NotificationsState
) {
    HorizontalPager(
        modifier = modifier,
        count = state().notifications.size,
        contentPadding = PaddingValues(horizontal = horizontalPadding)
    ) {
        when (val notification = state().notifications[it]) {
            is Notification.BonusExpiring -> Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.failed),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${notification.amount} ваших бонусов сгорят ${notification.date}!",
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.title
                )

            }
            is Notification.Main -> Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .clip(AppTheme.shapes.medium)
                    .background(AppTheme.colors.surfaces)
                    .padding(AppTheme.dimensions.small),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)
            ) {
                Image(
                    modifier = Modifier
                        .size(qrSize),
                    bitmap = notification.qr.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
                Text(
                    text = "У вас ${notification.bonusesAvailable} бонусов",
                    color = AppTheme.colors.black,
                    style = AppTheme.typography.title
                )
            }
        }
    }
}