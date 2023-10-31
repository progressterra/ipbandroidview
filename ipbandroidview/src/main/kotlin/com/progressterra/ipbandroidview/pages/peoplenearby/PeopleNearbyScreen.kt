package com.progressterra.ipbandroidview.pages.peoplenearby

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.widgets.peoplenearby.PeopleNearbyScreenEvent
import com.progressterra.ipbandroidview.widgets.peoplenearby.PeopleNearbyScreenState
import com.progressterra.ipbandroidview.widgets.peoplenearby.UsePeopleNearbyScreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PeopleNearbyScreen(
    modifier: Modifier = Modifier,
    state: PeopleNearbyScreenState,
    useComponent: UsePeopleNearbyScreen
) {

    @Composable
    fun Interest(
        itemState: Interest
    ) {
        val backgroundBrush =
            if (state.currentUser.interests.contains(itemState)) IpbTheme.colors.secondary.asBrush() else IpbTheme.colors.secondary2.asBrush()
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clip(CircleShape)
                .background(backgroundBrush)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            BrushedText(
                text = itemState.name,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
        }
    }

    @Composable
    fun Item(
        itemState: DatingUser
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier,
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier.size(79.dp),
                        painter = painterResource(id = R.drawable.avatar_background),
                        contentDescription = null
                    )
                    SimpleImage(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .niceClickable { useComponent.handle(PeopleNearbyScreenEvent(itemState)) },
                        image = itemState.avatar,
                        backgroundColor = IpbTheme.colors.background.asColor()
                    )
                }
                Column {
                    BrushedText(
                        text = itemState.name,
                        style = IpbTheme.typography.headline,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        BrushedIcon(
                            resId = R.drawable.ic_distance_small,
                            tint = IpbTheme.colors.primary.asBrush()
                        )
                        BrushedText(
                            text = "${itemState.distance} м",
                            style = IpbTheme.typography.footnoteRegular,
                            tint = IpbTheme.colors.textPrimary.asBrush()
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        BrushedIcon(
                            resId = R.drawable.ic_interests_small,
                            tint = IpbTheme.colors.primary.asBrush()
                        )
                        val matchPercent = remember(state.currentUser, itemState) {
                            state.currentUser.interests.count { itemState.interests.contains(it) } / state.currentUser.interests.size * 100
                        }
                        BrushedText(
                            text = "${matchPercent}% ${stringResource(id = R.string.of_interests_match)}",
                            style = IpbTheme.typography.footnoteRegular,
                            tint = IpbTheme.colors.textPrimary.asBrush()
                        )
                    }
                }
            }
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemState.interests.forEach {
                    Interest(itemState = it)
                }
            }
        }
    }

    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(title = stringResource(id = R.string.interests), useComponent = useComponent)
        }
    ) { _, _ ->
        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            items(state.items) {
                Item(it)
            }
        }
    }
}
