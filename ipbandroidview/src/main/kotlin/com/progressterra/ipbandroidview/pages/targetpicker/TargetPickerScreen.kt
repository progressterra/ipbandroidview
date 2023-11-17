package com.progressterra.ipbandroidview.pages.targetpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TargetPickerScreen(
    modifier: Modifier = Modifier,
    state: TargetPickerScreenState,
    useComponent: UseTargetPickerScreen
) {
    @Composable
    fun Item(
        itemState: DatingTarget
    ) {
        val picked = itemState.id == state.selectedTarget.id
        val backgroundBrush =
            if (picked) IpbTheme.colors.secondary.asBrush() else IpbTheme.colors.background.asBrush()
        Box(modifier = Modifier
            .padding(vertical = 4.dp)
            .clip(CircleShape)
            .background(backgroundBrush)
            .border(
                width = 2.dp, brush = IpbTheme.colors.secondary.asBrush(), shape = CircleShape
            )
            .niceClickable { useComponent.handle(TargetPickerScreenEvent(itemState)) }
            .padding(horizontal = 16.dp, vertical = 8.dp)) {
            BrushedText(
                text = itemState.name,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
        }
    }

    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = stringResource(R.string.dating_target),
            showBackButton = true,
            useComponent = useComponent
        )
    }, bottomBar = {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                state = state.save,
                useComponent = useComponent,
                title = stringResource(R.string.save)
            )
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                state = state.skip,
                useComponent = useComponent,
                title = stringResource(R.string.skip_yet)
            )
        }
    }) { _, _ ->
        StateColumn(
            state = state.screen, useComponent = useComponent,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BrushedText(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 36.dp),
                text = stringResource(R.string.choose_occupation),
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush(),
                textAlign = TextAlign.Center
            )
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                state.targets.forEach {
                    Item(it)
                }
            }
        }
    }
}