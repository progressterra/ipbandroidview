package com.progressterra.ipbandroidview.widgets.edituser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
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
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
        if (IpbAndroidViewSettings.SHOW_SEX_PICKER) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BrushedText(
                    text = stringResource(id = R.string.your_sex),
                    style = IpbTheme.typography.headline,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.sex == Sex.MALE,
                        onClick = { useComponent.handle(EditUserEvent(Sex.MALE)) })
                    BrushedText(
                        text = stringResource(id = R.string.male),
                        style = IpbTheme.typography.body,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.sex == Sex.FEMALE,
                        onClick = { useComponent.handle(EditUserEvent(Sex.FEMALE)) })
                    BrushedText(
                        text = stringResource(id = R.string.male),
                        style = IpbTheme.typography.body,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                }
            }
        }
    }
}