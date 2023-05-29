package com.progressterra.ipbandroidview.widgets.peoplenearby

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.avatar.Avatar
import com.progressterra.ipbandroidview.features.avatar.AvatarState
import com.progressterra.ipbandroidview.features.interestsdiff.InterestsDiff
import com.progressterra.ipbandroidview.features.interestsdiff.InterestsDiffState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun PeopleNearby(
    modifier: Modifier = Modifier,
    state: PeopleNearbyState,
    useComponent: UsePeopleNearby
) {

    @Composable
    fun Item(
        itemState: PeopleNearbyState.Item
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Avatar(
                    state = itemState.avatar,
                    useComponent = useComponent
                )
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
                            text = itemState.distance,
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
                        BrushedText(
                            text = itemState.match,
                            style = IpbTheme.typography.footnoteRegular,
                            tint = IpbTheme.colors.textPrimary.asBrush()
                        )
                    }
                }
            }
            InterestsDiff(
                state = itemState.interests,
            )
        }
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        items(state.items) {
            Item(it)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PreviewPeopleNearby() {
    IpbTheme {

        val items = listOf(
            PeopleNearbyState.Item(
                avatar = AvatarState(
                    url = "https://example.com/avatar1.jpg",
                    online = true
                ),
                name = "John Doe",
                distance = "2 km away",
                match = "5 interests in common",
                interests = InterestsDiffState(
                    items = listOf(
                        InterestsDiffState.Item(name = "Photography", match = true),
                        InterestsDiffState.Item(name = "Sport", match = true),
                        InterestsDiffState.Item(name = "CS")
                    )
                )
            ),
            PeopleNearbyState.Item(
                avatar = AvatarState(
                    url = "https://example.com/avatar2.jpg",
                    online = false
                ),
                name = "Jane Doe",
                distance = "1 km away",
                match = "3 interests in common",
                interests = InterestsDiffState(
                    items = listOf(
                        InterestsDiffState.Item(name = "Photography", match = true),
                        InterestsDiffState.Item(name = "Sport", match = true),
                        InterestsDiffState.Item(name = "Cooking")
                    )
                )
            )
        )

        PeopleNearby(
            state = PeopleNearbyState(items),
            useComponent = UsePeopleNearby.Empty()
        )
    }
}

