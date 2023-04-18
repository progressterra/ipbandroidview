package com.progressterra.ipbandroidview.features.mainorreceipt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonStyle
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

@Immutable
data class MainOrReceiptState(
    val main: ButtonState = ButtonState(
        id = "main"
    ), val receipt: ButtonState = ButtonState(
        id = "check"
    )
)

interface UseMainOrReceipt : UseButton

@Composable
fun MainOrReceipt(
    modifier: Modifier = Modifier, state: MainOrReceiptState, useComponent: UseMainOrReceipt
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.main,
            useComponent = useComponent,
            title = stringResource(R.string.to_main)
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.receipt,
            useComponent = useComponent,
            style = ButtonStyle.OUTLINE,
            title = stringResource(R.string.see_receipt)
        )
    }
}
