package com.progressterra.ipbandroidview.widgets.edituser

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Sex
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedRadioButton
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
        IpbAndroidViewSettings.AVAILABLE_PROFILE_FIELDS.forEach {
            when (it) {
                "name" -> {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.name,
                        hint = stringResource(R.string.name),
                        useComponent = useComponent,
                        backgroundColor = IpbTheme.colors.background.asColor()
                    )
                }
                "soname" -> {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.soname,
                        hint = stringResource(R.string.soname),
                        useComponent = useComponent,
                        backgroundColor = IpbTheme.colors.background.asColor()
                    )
                }
                "patronymic" -> {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.patronymic,
                        hint = stringResource(R.string.patronymic),
                        useComponent = useComponent,
                        backgroundColor = IpbTheme.colors.background.asColor()
                    )
                }
                "eMailGeneral" -> {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.email,
                        hint = stringResource(R.string.email),
                        useComponent = useComponent,
                        backgroundColor = IpbTheme.colors.background.asColor()
                    )
                }
                "sex" -> {
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
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ThemedRadioButton(
                                checked = state.sex == Sex.MALE,
                                onClick = { useComponent.handle(EditUserEvent(Sex.MALE)) },
                                enabled = state.sexEnabled)
                            BrushedText(
                                text = stringResource(id = R.string.male),
                                style = IpbTheme.typography.body,
                                tint = IpbTheme.colors.textPrimary.asBrush()
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ThemedRadioButton(
                                checked = state.sex == Sex.FEMALE,
                                onClick = { useComponent.handle(EditUserEvent(Sex.FEMALE)) },
                                enabled = state.sexEnabled)
                            BrushedText(
                                text = stringResource(id = R.string.female),
                                style = IpbTheme.typography.body,
                                tint = IpbTheme.colors.textPrimary.asBrush()
                            )
                        }
                    }
                }
                "dateOfBirth" -> {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.birthday,
                        hint = stringResource(R.string.birthday),
                        useComponent = useComponent,
                        backgroundColor = IpbTheme.colors.background.asColor()
                    )
                }
            }
        }
    }
}