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
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.shared.ui.MaskVisualTransformation
import com.progressterra.ipbandroidview.shared.ui.Masks.PASSPORT_NUMBER_MASK
import com.progressterra.ipbandroidview.shared.ui.Masks.PASSPORT_PROVIDER_CODE_MASK
import com.progressterra.ipbandroidview.shared.ui.Masks.PHONE_MASK

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
            useComponent = useComponent
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.birthday,
            actionIcon = R.drawable.ic_cal_pro,
            useComponent = useComponent
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.phone,
            visualTransformation = MaskVisualTransformation(PHONE_MASK),
            useComponent = useComponent
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.email,
            useComponent = useComponent
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.citizenship,
            useComponent = useComponent
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.address,
            useComponent = useComponent
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.passport,
            visualTransformation = MaskVisualTransformation(PASSPORT_NUMBER_MASK),
            useComponent = useComponent
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.passportProvider,
            useComponent = useComponent
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.passportProviderCode,
            visualTransformation = MaskVisualTransformation(PASSPORT_PROVIDER_CODE_MASK),
            useComponent = useComponent
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.patent,
            useComponent = useComponent
        )
    }
}