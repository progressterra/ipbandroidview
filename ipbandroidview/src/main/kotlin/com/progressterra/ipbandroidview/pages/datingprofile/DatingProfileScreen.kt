package com.progressterra.ipbandroidview.pages.datingprofile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DatingProfileScreen(
    modifier: Modifier = Modifier,
    state: DatingProfileScreenState,
    useComponent: UseDatingProfileScreen
) {

    @Composable
    fun Item(
        itemState: Interest
    ) {
        val backgroundBrush = IpbTheme.colors.secondary.asBrush()
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .clip(CircleShape)
                .background(backgroundBrush)
                .border(
                    width = 2.dp, brush = IpbTheme.colors.secondary.asBrush(), shape = CircleShape
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            BrushedText(
                text = itemState.name,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
        }
    }
    ThemedLayout(
        modifier = modifier,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .background(IpbTheme.colors.background.asBrush())
                    .padding(horizontal = 16.dp)
            ) {
                if (state.ownProfile && !state.editMode) {
                    IconButton(
                        modifier = Modifier.size(32.dp).align(Alignment.CenterStart),
                        onClick = { useComponent.handle(DatingProfileScreenEvent.Edit) }) {
                        BrushedIcon(
                            modifier = Modifier.size(32.dp),
                            resId = R.drawable.ic_profile_edit,
                            tint = IpbTheme.colors.iconPrimary.asBrush()
                        )
                    }
                }
                BrushedText(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 40.dp),
                    text = if (state.ownProfile) stringResource(id = R.string.own_profile) else state.user.name,
                    maxLines = 1,
                    style = IpbTheme.typography.title2,
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                )
                if (state.ownProfile && !state.editMode) {
                    IconButton(
                        modifier = Modifier.size(32.dp).align(Alignment.CenterEnd),
                        onClick = { useComponent.handle(DatingProfileScreenEvent.OnSettings) }) {
                        BrushedIcon(
                            modifier = Modifier.size(32.dp),
                            resId = R.drawable.ic_settings,
                            tint = IpbTheme.colors.iconPrimary.asBrush()
                        )
                    }
                }
            }
        }
    ) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SimpleImage(
                modifier = Modifier
                    .size(137.dp)
                    .clip(CircleShape),
                image = state.user.image, backgroundColor = IpbTheme.colors.background.asColor()
            )
            BrushedText(
                modifier = Modifier,
                text = state.user.name,
                style = IpbTheme.typography.headline,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BrushedIcon(
                    resId = R.drawable.ic_address,
                    tint = IpbTheme.colors.iconPrimary.asBrush()
                )
                BrushedText(
                    modifier = Modifier,
                    text = state.user.locationPoint.address,
                    style = IpbTheme.typography.subHeadlineRegular,
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BrushedIcon(
                    resId = R.drawable.ic_target,
                    tint = IpbTheme.colors.iconPrimary.asBrush()
                )
                BrushedText(
                    modifier = Modifier,
                    text = state.user.target,
                    style = IpbTheme.typography.subHeadlineRegular,
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                )
            }
            BrushedText(
                modifier = Modifier,
                text = stringResource(id = R.string.about_me),
                style = IpbTheme.typography.title2,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
            BrushedText(
                modifier = Modifier,
                text = state.user.age,
                style = IpbTheme.typography.subHeadlineRegular,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BrushedIcon(
                    resId = R.drawable.ic_occupation,
                    tint = IpbTheme.colors.iconPrimary.asBrush()
                )
                BrushedText(
                    modifier = Modifier,
                    text = state.user.occupation,
                    style = IpbTheme.typography.subHeadlineRegular,
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                )
            }
            BrushedText(
                modifier = Modifier,
                text = state.user.description,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
            BrushedText(
                modifier = Modifier,
                text = stringResource(id = R.string.interests),
                style = IpbTheme.typography.title2,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.user.interests.forEach {
                    Item(it)
                }
            }
        }
    }
}

@Preview
@Composable
private fun DatingProfileScreenPreviewOwn() {
    IpbTheme {
        val currentUser = DatingUser(
            name = "Daniil Pechorin",
            description = "Android dev. from Komi Republic. Currently live in Yaroslavl. Inspired by zen buddhism",
            interests = listOf(
                Interest(name = "Zen Buddhism"),
                Interest(name = "Cooking"),
                Interest(name = "Programming")
            ),
            target = "Cookout",
            distance = 112,
            age = "90 y.0.",
            occupation = "Android Super-Senior Staff Lead"
        )
        DatingProfileScreen(
            state = DatingProfileScreenState(
                user = currentUser,
                ownProfile = true
            ),
            useComponent = UseDatingProfileScreen.Empty()
        )

    }
}

@Preview
@Composable
private fun DatingProfileScreenPreviewOther() {
    IpbTheme {
        val currentUser = DatingUser(
            name = "Daniil Pechorin",
            description = "Android dev. from Komi Republic. Currently live in Yaroslavl. Inspired by zen buddhism",
            interests = listOf(
                Interest(name = "Zen Buddhism"),
                Interest(name = "Cooking"),
                Interest(name = "Programming")
            ),
            target = "Cookout",
            distance = 112,
            age = "90 y.0.",
            occupation = "Android Super-Senior Staff Lead"
        )
        DatingProfileScreen(
            state = DatingProfileScreenState(
                user = currentUser,
                ownProfile = false
            ),
            useComponent = UseDatingProfileScreen.Empty()

        )

    }
}

@Preview
@Composable
private fun DatingProfileScreenPreviewEdit() {
    IpbTheme {
        val currentUser = DatingUser(
            name = "Daniil Pechorin",
            description = "Android dev. from Komi Republic. Currently live in Yaroslavl. Inspired by zen buddhism",
            interests = listOf(
                Interest(name = "Zen Buddhism"),
                Interest(name = "Cooking"),
                Interest(name = "Programming")
            ),
            target = "Cookout",
            distance = 112,
            age = "90 y.0.",
            occupation = "Android Super-Senior Staff Lead"
        )
        DatingProfileScreen(
            state = DatingProfileScreenState(
                user = currentUser,
                ownProfile = true,
                editMode = true
            ),
            useComponent = UseDatingProfileScreen.Empty()
        )

    }
}



