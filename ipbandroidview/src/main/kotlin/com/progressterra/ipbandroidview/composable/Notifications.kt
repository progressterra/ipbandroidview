package com.progressterra.ipbandroidview.composable

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
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.progressterra.ipbandroidview.model.store.StoreNotification
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
interface NotificationsState {

    val notifications: List<StoreNotification>
}

private val height = 130.dp

private val qrSize = 114.dp

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Notifications(
    modifier: Modifier = Modifier,
    state: NotificationsState
) {
    HorizontalPager(
        modifier = modifier,
        count = state.notifications.size,
        contentPadding = PaddingValues(horizontal = AppTheme.dimensions.small),
        itemSpacing = AppTheme.dimensions.small
    ) {
        when (val notification = state.notifications[it]) {
            is StoreNotification.BonusExpiring -> Column(
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
            is StoreNotification.Main -> Row(
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
                    modifier = Modifier.size(qrSize),
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