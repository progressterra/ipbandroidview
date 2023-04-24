package com.progressterra.ipbandroidview.pages.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkip
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.MaskVisualTransformation
import com.progressterra.ipbandroidview.shared.ui.Masks.PHONE_MASK
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkText
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextData
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun SignInScreen(
    state: SignInState, useComponent: UseSignIn
) {
    ThemedLayout(topBar = {
        TopBar(
            title = stringResource(R.string.authorization), useComponent = useComponent
        )
    }, bottomBar = {
        AuthOrSkip(
            state = state.authOrSkipState, useComponent = useComponent
        )
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                state = state.phone,
                useComponent = useComponent,
                hint = stringResource(R.string.phone_number),
                visualTransformation = MaskVisualTransformation(PHONE_MASK),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                )
            )
            Spacer(Modifier.height(20.dp))
            LinkText(
                linkTextData = listOf(
                    LinkTextData(stringResource(R.string.auth_warning_0)),
                    LinkTextData(stringResource(R.string.offer), IpbTheme.customization.offerUrl),
                    LinkTextData(stringResource(R.string.and)),
                    LinkTextData(
                        stringResource(R.string.privacy_policy), IpbTheme.customization.privacyUrl
                    )
                ), useComponent = useComponent,
                style = IpbTheme.typography.footnoteRegular,
                brush = IpbTheme.colors.textDisabled.asBrush()
            )
        }
    }
}