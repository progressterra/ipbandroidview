package com.progressterra.ipbandroidview.shared.ui.counter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun Counter(
    modifier: Modifier = Modifier,
    state: CounterState,
    useComponent: UseCounter = UseCounter.Empty()
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(IpbTheme.colors.onSurface.asBrush())

                .niceClickable {
                    useComponent.handle(CounterEvent.Remove(state.id))
                }, contentAlignment = Alignment.Center
        ) {
            BrushedIcon(
                resId = R.drawable.ic_subtraction, tint = IpbTheme.colors.iconPrimary.asBrush()
            )
        }
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(IpbTheme.colors.onSurface.asBrush()),
            contentAlignment = Alignment.Center
        ) {
            BrushedText(
                text = state.count.toString(),
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textPrimary.asBrush(),
            )
        }
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(IpbTheme.colors.onSurface.asBrush())
                .niceClickable {
                    useComponent.handle(CounterEvent.Add(state.id))
                }, contentAlignment = Alignment.Center
        ) {
            BrushedIcon(
                resId = R.drawable.ic_add, tint = IpbTheme.colors.iconPrimary.asBrush()
            )
        }
    }
}

@Preview
@Composable
private fun CounterPreview() {
    IpbTheme {
        Counter(
            state = CounterState(
                id = "a", count = 1
            )
        )
    }
}