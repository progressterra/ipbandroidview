package com.progressterra.ipbandroidview.widgets.nextorrepeat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.countdown.CountDown
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.button.Button

@Composable
fun NextOrRepeat(
    modifier: Modifier = Modifier,
    state: NextOrRepeatState,
    useComponent: UseNextOrRepeat
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(IpbTheme.colors.surface1.asBrush())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.next,
            useComponent = useComponent,
            title = stringResource(R.string.next)
        )
        CountDown(
            modifier = Modifier.fillMaxWidth(),
            state = state.repeat,
            useComponent = useComponent
        )
    }
}