package com.progressterra.ipbandroidview.features.trace

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.catalogcard.CatalogCardState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Immutable
data class TraceState(
    val trace: List<CatalogCardState> = emptyList()
) {

    fun addTrace(newTrace: CatalogCardState) = copy(trace = trace + newTrace)

    fun removeTrace() = copy(trace = trace.dropLast(1))
}

interface UseTrace {

    fun handle(event: TraceEvent)
}

sealed class TraceEvent {

    object Back : TraceEvent()
}

@Composable
fun Trace(
    modifier: Modifier = Modifier, state: TraceState, useComponent: UseTrace
) {
    Box(
        modifier = modifier.fillMaxWidth().background(IpbTheme.colors.surface.asColor())
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
                .align(Alignment.Center), horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            state.trace.forEachIndexed { index, trace ->
                if (index != 0) {
                    BrushedIcon(
                        resId = R.drawable.ic_forw_pro,
                        tint = IpbTheme.colors.iconPrimary3.asBrush()
                    )
                }
                BrushedText(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = trace.name,
                    style = IpbTheme.typography.title,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
            }
        }
    }
}