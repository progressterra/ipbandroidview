package com.progressterra.ipbandroidview.shared.ui.counter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(IpbTheme.colors.onSurface.asBrush())
                .clip(CircleShape)
                .niceClickable {
                    useComponent.handle(CounterEvent.Remove(state.id))
                },
        ) {
            BrushedIcon(
                resId = R.drawable.ic_subtraction,
                tint = IpbTheme.colors.iconPrimary.asBrush()
            )
        }
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(IpbTheme.colors.onSurface.asBrush())
                .clip(CircleShape)
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
                .background(IpbTheme.colors.onSurface.asBrush())
                .clip(CircleShape)
                .niceClickable {
                    useComponent.handle(CounterEvent.Add(state.id))
                },
        ) {
            BrushedIcon(
                resId = R.drawable.ic_add, tint = IpbTheme.colors.iconPrimary.asBrush()
            )
        }
    }
}