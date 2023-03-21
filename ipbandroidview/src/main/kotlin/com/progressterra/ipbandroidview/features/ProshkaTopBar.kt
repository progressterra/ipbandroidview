package com.progressterra.ipbandroidview.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Immutable
data class ProshkaTopBarState(
    val id: String = "", val title: String = "", val showBackButton: Boolean = false
)

interface UseProshkaTopBar {

    fun handleEvent(id: String, event: ProshkaTopBarEvent) = Unit

    class Empty : UseProshkaTopBar {

        override fun handleEvent(id: String, event: ProshkaTopBarEvent) = Unit
    }
}

sealed class ProshkaTopBarEvent {

    object Back : ProshkaTopBarEvent()
}

@Composable
fun ProshkaTopBar(
    modifier: Modifier = Modifier,
    state: ProshkaTopBarState,
    useComponent: UseProshkaTopBar
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(IpbTheme.colors.background.asBrush()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if (state.showBackButton) {
            IconButton(onClick = { useComponent.handleEvent(state.id, ProshkaTopBarEvent.Back) }) {
                BrushedIcon(
                    resId = R.drawable.ic_back_pro, tint = IpbTheme.colors.iconPrimary1.asBrush()
                )
            }
        }
        BrushedText(
            text = state.title,
            style = IpbTheme.typography.title,
            tint = IpbTheme.colors.textPrimary1.asBrush(),
        )
    }
}