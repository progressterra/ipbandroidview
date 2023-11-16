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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.entities.LocationPoint
import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ui.utils.rememberResourceUri
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

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
                        image = itemState.avatar.ifEmpty {
                            rememberResourceUri(
                                resourceId = when (itemState.sex) {
                                    Sex.MALE -> R.drawable.avatar_male
                                    Sex.FEMALE -> R.drawable.avatar_female
                                }
                            ).toString()
                        },
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
                            if (state.currentUser.interests.isNotEmpty()) state.currentUser.interests.count {
                                itemState.interests.contains(
                                    it
                                )
                            } / state.currentUser.interests.size * 100 else 0
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

@Preview
@Composable
private fun PeopleNearbyScreenPreview() {
    IpbTheme {
        PeopleNearbyScreen(
            state = PeopleNearbyScreenState(
                items = listOf(
                    DatingUser(
                        id = "intellegebat",
                        avatar = "splendide",
                        avatarBitmap = null,
                        name = "Roxanne Bradford",
                        description = "dicit",
                        hideAvatar = false,
                        locationPoint = LocationPoint(
                            id = "sollicitudin",
                            name = "Ed Robbins",
                            address = "iusto",
                            latitude = 4.5,
                            longitude = 6.7
                        ),
                        interests = listOf(
                            Interest(name = "Lyzhi 0"),
                            Interest(name = "Lyzhi 1"),
                            Interest(name = "Lyzhi 2"),
                            Interest(name = "Lyzhi 3"),
                            Interest(name = "Lyzhi 4"),
                            Interest(name = "Lyzhi 5"),
                            Interest(name = "Lyzhi 6"),
                            Interest(name = "Lyzhi 7")
                        ),
                        distance = 8410,
                        target = DatingTarget(
                            id = "hinc",
                            name = "Bernadette Maddox"
                        ),
                        age = "interesset",
                        occupation = Interest(name = "ceteros"),
                        sex = Sex.MALE,
                        own = false
                    ),
                    DatingUser(
                        id = "quaerendum",
                        avatar = "ubique",
                        avatarBitmap = null,
                        name = "Rickey Hughes",
                        description = "molestiae",
                        hideAvatar = false,
                        locationPoint = LocationPoint(
                            id = "gubergren",
                            name = "Blanche Maxwell",
                            address = "mutat",
                            latitude = 12.13,
                            longitude = 14.15
                        ),
                        interests = listOf(
                            Interest(name = "Lyzhi 0"),
                            Interest(name = "Lyzhi 1"),
                            Interest(name = "Lyzhi 2"),
                            Interest(name = "Lyzhi 3")
                        ),
                        distance = 6800,
                        target = DatingTarget(
                            id = "sit",
                            name = "Dewayne Kelley"
                        ),
                        age = "at",
                        occupation = Interest(name = "option"),
                        sex = Sex.MALE,
                        own = false
                    )
                ),
                currentUser = DatingUser(
                    interests = listOf(
                        Interest(name = "Lyzhi 0"),
                        Interest(name = "Lyzhi 1"),
                        Interest(name = "Lyzhi 7"),
                        Interest(name = "Lyzhi 8")
                    )
                )
            ), useComponent = UsePeopleNearbyScreen.Empty()
        )
    }
}
