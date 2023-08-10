package com.progressterra.ipbandroidview.features.trace

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun Trace(
    modifier: Modifier = Modifier, state: TraceState, useComponent: UseTrace
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(IpbTheme.colors.surface.asColor()),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        IconButton(
            onClick = { useComponent.handle(TraceEvent) }) {
            BrushedIcon(
                resId = R.drawable.ic_back, tint = IpbTheme.colors.iconPrimary.asBrush()
            )
        }
        val scrollState = rememberScrollState()
        LaunchedEffect(Unit) {
            scrollState.animateScrollTo(scrollState.maxValue)
        }
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(vertical = 6.dp, horizontal = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            state.trace.forEach { trace ->
                BrushedText(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = trace.name,
                    style = IpbTheme.typography.title,
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    maxLines = 1
                )
                BrushedIcon(
                    resId = R.drawable.ic_forw,
                    tint = IpbTheme.colors.iconPrimary.asBrush()
                )
            }
            BrushedText(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = state.current.name,
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textPrimary.asBrush(),
                maxLines = 1
            )
        }
    }
}

@Preview
@Composable
private fun TracePreview() {
    IpbTheme {
        Trace(
            state = TraceState(
                listOf(
                    CatalogCardState(
                        id = "nunc PARENT",
                        name = "NUNU",
                        image = "https://www.google.com/#q=montes"
                    ), CatalogCardState(
                        id = "nunc 1",
                        name = "Cory ",
                        image = "https://www.google.com/#q=montes"
                    ), CatalogCardState(
                        id = "nunc 2",
                        name = "Chang",
                        image = "https://www.google.com/#q=montes"
                    )
                )
            ), useComponent = UseTrace.Empty()
        )
    }
}