package com.progressterra.ipbandroidview.features.trace

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(IpbTheme.colors.surface.asColor())
    ) {
        Row(
            modifier = Modifier.padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier.size(40.dp),
                onClick = { useComponent.handle(TraceEvent) }) {
                BrushedIcon(
                    resId = R.drawable.ic_back, tint = IpbTheme.colors.iconPrimary.asBrush()
                )
            }
            BrushedText(
                text = state.trace.last().name,
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textPrimary.asBrush(),
                maxLines = 1
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BrushedText(
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
                        name = "Cory fdggdgdfg",
                        image = "https://www.google.com/#q=montes"
                    ), CatalogCardState(
                        id = "nunc 2",
                        name = "Changs wefwrefgerg",
                        image = "https://www.google.com/#q=montes"
                    )
                ),
                current = CatalogCardState(
                    id = "nunc 1",
                    name = "Cory fdggdgdfg",
                    image = "https://www.google.com/#q=montes"
                )
            ), useComponent = UseTrace.Empty()
        )
    }
}