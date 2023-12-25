package com.progressterra.ipbandroidview.features.editbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun EditButton(
    modifier: Modifier = Modifier, state: EditButtonState, useComponent: UseEditButton
) {
    Column(
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(8.dp)
    ) {
        if (state.editing) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                state = state.save,
                title = stringResource(R.string.ready),
                useComponent = useComponent
            )
            Spacer(Modifier.height(8.dp))
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                state = state.cancel,
                title = stringResource(R.string.cancel),
                useComponent = useComponent
            )
        } else {
            Button(
                modifier = Modifier.fillMaxWidth(),
                state = state.edit,
                title = stringResource(R.string.change_data),
                useComponent = useComponent
            )
        }
    }
}