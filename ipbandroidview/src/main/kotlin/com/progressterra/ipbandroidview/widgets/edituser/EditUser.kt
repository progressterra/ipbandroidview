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
import com.progressterra.ipbandroidview.features.makephoto.MakePhoto
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.MaskVisualTransformation
import com.progressterra.ipbandroidview.shared.ui.Masks.PASSPORT_NUMBER_MASK
import com.progressterra.ipbandroidview.shared.ui.Masks.PASSPORT_PROVIDER_CODE_MASK
import com.progressterra.ipbandroidview.shared.ui.Masks.PHONE_MASK
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun EditUser(
    modifier: Modifier = Modifier, state: EditUserState, useComponent: UseEditUser
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
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
            actionIcon = R.drawable.ic_cancel
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.birthday,
            hint = stringResource(R.string.birthday),
            actionIcon = R.drawable.ic_cal,
            useComponent = useComponent
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.phone,
            hint = stringResource(R.string.phone_number),
            visualTransformation = MaskVisualTransformation(PHONE_MASK),
            useComponent = useComponent,
            actionIcon = R.drawable.ic_cancel
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.email,
            hint = stringResource(R.string.email),
            useComponent = useComponent,
            actionIcon = R.drawable.ic_cancel
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.citizenship,
            hint = stringResource(R.string.citizenship),
            useComponent = useComponent,
            actionIcon = R.drawable.ic_cancel
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.address,
            hint = stringResource(R.string.address),
            useComponent = useComponent,
            actionIcon = R.drawable.ic_cancel
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.passport,
            hint = stringResource(R.string.passport),
            visualTransformation = MaskVisualTransformation(PASSPORT_NUMBER_MASK),
            useComponent = useComponent,
            actionIcon = R.drawable.ic_cancel
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.passportProvider,
            hint = stringResource(R.string.passport_provider),
            useComponent = useComponent,
            actionIcon = R.drawable.ic_cancel
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.passportCode,
            hint = stringResource(R.string.passport_provider_code),
            visualTransformation = MaskVisualTransformation(PASSPORT_PROVIDER_CODE_MASK),
            useComponent = useComponent,
            actionIcon = R.drawable.ic_cancel
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.patent,
            hint = stringResource(R.string.patent_number),
            useComponent = useComponent,
            actionIcon = R.drawable.ic_cancel
        )
        MakePhoto(
            title = stringResource(R.string.passport_photo),
            state = state.makePhoto,
            useComponent = useComponent
        )
    }
}