package com.progressterra.ipbandroidview.features.trace

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(IpbTheme.colors.surface.asColor())
    ) {
        IconButton(modifier = Modifier.align(Alignment.CenterStart),
            onClick = { useComponent.handle(TraceEvent.Back) }) {
            BrushedIcon(
                resId = R.drawable.ic_back_pro, tint = IpbTheme.colors.iconPrimary.asBrush()
            )
        }
        Row(
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 40.dp)
                .align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            state.trace.forEachIndexed { index, trace ->
                if (index != 0) {
                    BrushedText(
                        modifier = Modifier.padding(horizontal = 4.dp),
                        text = trace.name,
                        style = IpbTheme.typography.title,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                    if (index != state.trace.lastIndex) {
                        BrushedIcon(
                            resId = R.drawable.ic_forw_pro,
                            tint = IpbTheme.colors.iconPrimary3.asBrush()
                        )
                    }
                }
            }
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
                        imageUrl = "https://www.google.com/#q=montes"
                    ), CatalogCardState(
                        id = "nunc 1",
                        name = "Cory ",
                        imageUrl = "https://www.google.com/#q=montes"
                    ), CatalogCardState(
                        id = "nunc 2",
                        name = "Chang",
                        imageUrl = "https://www.google.com/#q=montes"
                    )
                )
            ), useComponent = UseTrace.Empty()
        )
    }
}