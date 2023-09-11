package com.progressterra.ipbandroidview.widgets.edituser

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
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun EditUser(
    modifier: Modifier = Modifier,
    state: EditUserState,
    useComponent: UseEditUser
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.name,
            hint = stringResource(R.string.name_surname),
            useComponent = useComponent,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.birthday,
            hint = stringResource(R.string.birthday),
            useComponent = useComponent,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.phone,
            hint = stringResource(R.string.phone_number),
            useComponent = useComponent,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.email,
            hint = stringResource(R.string.email),
            useComponent = useComponent,
            backgroundColor = IpbTheme.colors.background.asColor()
        )
    }
}