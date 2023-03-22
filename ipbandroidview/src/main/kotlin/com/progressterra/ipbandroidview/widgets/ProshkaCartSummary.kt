package com.progressterra.ipbandroidview.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.theme.Preview
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.button.UseButton


@Immutable
data class ProshkaCartSummaryState(
    val total: SimplePrice = SimplePrice(), val proceed: ButtonState = ButtonState()
)

interface UseProshkaCartSummary : UseButton {


    class Empty : UseProshkaCartSummary {

        override fun handleEvent(id: String, event: ButtonEvent) = Unit
    }
}

@Composable
fun ProshkaCartSummary(
    modifier: Modifier = Modifier,
    state: ProshkaCartSummaryState,
    useComponent: UseProshkaCartSummary
) {
    Column(
        modifier = modifier
            .background(IpbTheme.colors.background.asBrush())
            .padding(start = 20.dp, end = 20.dp, top = 14.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BrushedText(
                text = stringResource(R.string.total_to_pay),
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textPrimary1.asBrush(),
            )
            BrushedText(
                text = state.total.toString(),
                style = IpbTheme.typography.title,
                tint = IpbTheme.colors.textPrimary1.asBrush(),
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.proceed,
            useComponent = useComponent
        )
    }
}

@Preview
@Composable
private fun ProshkaCartSummaryPreview() {
    Preview {
        ProshkaCartSummary(
            state = ProshkaCartSummaryState(),
            useComponent = UseProshkaCartSummary.Empty()
        )
    }
}