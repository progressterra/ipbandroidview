package com.progressterra.ipbandroidview.features.authorskip

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
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton

@Composable
fun NextOrSkip(
    modifier: Modifier = Modifier,
    state: AuthOrSkipState,
    useComponent: UseAuthOrSkip
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
            state = state.auth,
            title = stringResource(R.string.next),
            useComponent = useComponent
        )
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            state = state.skip,
            title = stringResource(R.string.auth_skip_button),
            useComponent = useComponent
        )
    }
}