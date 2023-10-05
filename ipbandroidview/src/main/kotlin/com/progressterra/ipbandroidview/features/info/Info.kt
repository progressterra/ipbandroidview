package com.progressterra.ipbandroidview.features.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun Info(
    modifier: Modifier = Modifier,
    state: InfoState,
    useComponent: UseInfo
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        BrushedText(
            text = stringResource(R.string.about_you),
            style = IpbTheme.typography.body,
            tint = IpbTheme.colors.textPrimary.asBrush(),
            textAlign = TextAlign.Center
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.nickName,
            useComponent = useComponent,
            hint = stringResource(R.string.nickname),
            singleLine = false
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.about,
            useComponent = useComponent,
            hint = stringResource(R.string.about_you_hint),
            singleLine = false
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.profession,
            useComponent = useComponent,
            hint = stringResource(R.string.profession_hint)
        )
    }
}
