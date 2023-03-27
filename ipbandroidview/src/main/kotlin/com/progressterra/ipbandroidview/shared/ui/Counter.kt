package com.progressterra.ipbandroidview.shared.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.IsEmpty
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Immutable
data class CounterState(
    val id: String = "",
    val count: Int = 0
) : IsEmpty {

    override fun isEmpty(): Boolean = count == 0
}

sealed class CounterEvent {

    object Add : CounterEvent()

    object Remove : CounterEvent()
}

interface UseCounter {

    fun handle(id: String, event: CounterEvent)

    class Empty : UseCounter {
        override fun handle(id: String, event: CounterEvent) = Unit
    }
}

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
                .background(IpbTheme.colors.onSurface1.asBrush())
                .clip(CircleShape)
                .niceClickable {
                    useComponent.handle(state.id, CounterEvent.Remove)
                },
        ) {
            BrushedIcon(
                resId = R.drawable.ic_subtraction,
                tint = IpbTheme.colors.iconPrimary1.asBrush()
            )
        }
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(IpbTheme.colors.onSurface1.asBrush())
                .clip(CircleShape)
        ) {
            BrushedText(
                text = state.count.toString(),
                style = IpbTheme.typography.tertiary,
                tint = IpbTheme.colors.textPrimary1.asBrush(),
            )
        }
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(IpbTheme.colors.onSurface1.asBrush())
                .clip(CircleShape)
                .niceClickable {
                    useComponent.handle(state.id, CounterEvent.Add)
                },
        ) {
            BrushedIcon(
                resId = R.drawable.ic_add, tint = IpbTheme.colors.iconPrimary1.asBrush()
            )
        }
    }
}