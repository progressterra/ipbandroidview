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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.entities.DatingUser
import com.progressterra.ipbandroidview.entities.Interest
import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.entities.shouldShow
import com.progressterra.ipbandroidview.entities.toString
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ui.utils.rememberResourceUri
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DatingProfileScreen(
    modifier: Modifier = Modifier,
    state: DatingProfileScreenState,
    useComponent: UseDatingProfileScreen
) {

    @Composable
    fun ItemEditMode(
        itemState: Interest
    ) {
        val picked =
            (!state.user.interests.contains(itemState) && state.changedInterests.contains(itemState)) ||
                    (state.user.interests.contains(itemState) && !state.changedInterests.contains(itemState))
        val backgroundBrush =
            if (picked) IpbTheme.colors.secondary.asBrush() else IpbTheme.colors.background.asBrush()
        Box(modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(CircleShape)
            .background(backgroundBrush)
            .border(
                width = 2.dp, brush = IpbTheme.colors.secondary.asBrush(), shape = CircleShape
            )
            .niceClickable { useComponent.handle(DatingProfileScreenEvent.PickInterest(itemState)) }
            .padding(horizontal = 16.dp, vertical = 8.dp)) {
            BrushedText(
                text = itemState.name,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
        }
    }

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
                if (state.user.own && !state.editMode) {
                    IconButton(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterStart),
                        onClick = { useComponent.handle(DatingProfileScreenEvent.Edit) }) {
                        BrushedIcon(
                            modifier = Modifier.size(32.dp),
                            resId = R.drawable.ic_profile_edit,
                            tint = IpbTheme.colors.iconPrimary.asBrush()
                        )
                    }
                }
                if (state.user.own && state.editMode || !state.user.own) {
                    IconButton(modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterStart),
                        onClick = { useComponent.handle(TopBarEvent) }) {
                        BrushedIcon(
                            modifier = Modifier.size(30.dp),
                            resId = R.drawable.ic_back, tint = IpbTheme.colors.iconPrimary.asBrush()
                        )
                    }
                }
                BrushedText(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 40.dp),
                    text = if (state.user.own) stringResource(id = R.string.own_profile) else state.user.name,
                    maxLines = 1,
                    style = IpbTheme.typography.title2,
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                )
                if (state.user.own && !state.editMode) {
                    IconButton(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterEnd),
                        onClick = { useComponent.handle(DatingProfileScreenEvent.OnSettings) }) {
                        BrushedIcon(
                            modifier = Modifier.size(32.dp),
                            resId = R.drawable.ic_settings,
                            tint = IpbTheme.colors.iconPrimary.asBrush()
                        )
                    }
                }
            }
        }, bottomBar = {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 38.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (state.editMode) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.save,
                        title = stringResource(id = R.string.save_changes),
                        useComponent = useComponent
                    )
                }
                if (state.user.connection.shouldShow() && !state.user.own) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.connect,
                        useComponent = useComponent,
                        title = state.user.connection.toString { stringResource(id = it) }
                    )
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.chat,
                        title = stringResource(id = R.string.start_chat),
                        useComponent = useComponent
                    )
                }
            }
        }
    ) { _, _ ->
        StateColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            scrollable = true,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = state.screen,
            useComponent = useComponent
        ) {
            if (state.editMode) {
                SimpleImage(
                    modifier = Modifier
                        .size(137.dp)
                        .clip(CircleShape),
                    image = state.user.avatar,
                    backgroundColor = IpbTheme.colors.background.asColor()
                )
                TextButton(
                    state = state.choosePhoto,
                    title = stringResource(id = R.string.choose_another_photo),
                    useComponent = useComponent
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.name,
                    hint = stringResource(R.string.name),
                    useComponent = useComponent,
                    backgroundColor = IpbTheme.colors.background.asColor()
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.birthday,
                    hint = stringResource(R.string.birthday),
                    useComponent = useComponent,
                    backgroundColor = IpbTheme.colors.background.asColor()
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.phone,
                    hint = stringResource(R.string.phone_number),
                    useComponent = useComponent,
                    backgroundColor = IpbTheme.colors.background.asColor()
                )
                BrushedText(
                    modifier = Modifier,
                    text = stringResource(id = R.string.about_me),
                    style = IpbTheme.typography.title2,
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.about,
                    useComponent = useComponent,
                    hint = stringResource(R.string.about_you_hint),
                    singleLine = false
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
                    state.allInterests.forEach {
                        ItemEditMode(it)
                    }
                }
            } else {
                SimpleImage(
                    modifier = Modifier
                        .size(137.dp)
                        .clip(CircleShape),
                    image = state.user.avatar.ifEmpty {
                        rememberResourceUri(
                            resourceId = when (state.user.sex) {
                                Sex.MALE -> R.drawable.avatar_male
                                Sex.FEMALE -> R.drawable.avatar_female
                            }
                        ).toString()
                    }, backgroundColor = IpbTheme.colors.background.asColor()
                )
                if (state.user.name.isNotEmpty()) {
                    BrushedText(
                        modifier = Modifier,
                        text = state.user.name,
                        style = IpbTheme.typography.headline,
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                    )
                }
                if (state.user.locationPoint.address.isNotEmpty()) {
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
                }
                if (!state.user.target.isEmpty()) {
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
                            text = state.user.target.name,
                            style = IpbTheme.typography.subHeadlineRegular,
                            tint = IpbTheme.colors.textPrimary.asBrush(),
                        )
                    }
                }
                if (state.user.age.isNotEmpty() || !state.user.occupation.isEmpty() || state.user.description.isNotEmpty()) {
                    BrushedText(
                        modifier = Modifier,
                        text = stringResource(id = R.string.about_me),
                        style = IpbTheme.typography.title2,
                        tint = IpbTheme.colors.textPrimary.asBrush(),
                    )
                    if (state.user.age.isNotEmpty()) {
                        BrushedText(
                            modifier = Modifier,
                            text = state.user.age,
                            style = IpbTheme.typography.subHeadlineRegular,
                            tint = IpbTheme.colors.textPrimary.asBrush(),
                        )
                    }
                    if (!state.user.occupation.isEmpty()) {
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
                                text = state.user.occupation.name,
                                style = IpbTheme.typography.subHeadlineRegular,
                                tint = IpbTheme.colors.textPrimary.asBrush(),
                            )
                        }
                    }
                    if (state.user.description.isNotEmpty()) {
                        BrushedText(
                            modifier = Modifier,
                            text = state.user.description,
                            style = IpbTheme.typography.body,
                            tint = IpbTheme.colors.textPrimary.asBrush(),
                        )
                    }
                }
                if (state.user.interests.isNotEmpty()) {
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
            target = DatingTarget(name = "LALALA"),
            distance = 112,
            age = "90 y.0.",
            occupation = Interest(name = "Android Super-Senior Staff Lead")
        )
        DatingProfileScreen(
            state = DatingProfileScreenState(
                user = currentUser,
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
            target = DatingTarget(name = "LALALA"),
            distance = 112,
            age = "90 y.0.",
            occupation = Interest(name = "Android Super-Senior Staff Lead")
        )
        DatingProfileScreen(
            state = DatingProfileScreenState(
                user = currentUser
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
            target = DatingTarget(name = "LALALA"),
            distance = 112,
            age = "90 y.0.",
            occupation = Interest(name = "Android Super-Senior Staff Lead")
        )
        DatingProfileScreen(
            state = DatingProfileScreenState(
                user = currentUser,
                editMode = true
            ),
            useComponent = UseDatingProfileScreen.Empty()
        )

    }
}



