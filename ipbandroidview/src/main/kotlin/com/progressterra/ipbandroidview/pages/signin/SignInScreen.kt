package com.progressterra.ipbandroidview.pages.signin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.authorskip.AuthOrSkip
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
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
        ProshkaTopBar(
            title = stringResource(R.string.authorization), useComponent = useComponent
        )
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
        ) {
            TextField(
                state = state.phone,
                useComponent = useComponent,
                hint = stringResource(R.string.phone_number),
                visualTransformation = MaskVisualTransformation(PHONE_MASK)
            )
            Spacer(Modifier.height(20.dp))
            LinkText(
                linkTextData = listOf(
                    LinkTextData(stringResource(R.string.auth_warning_0)),
                    LinkTextData(stringResource(R.string.offer), IpbTheme.customization.offerUrl),
                    LinkTextData(stringResource(R.string.and)),
                    LinkTextData(
                        stringResource(R.string.privacy_policy),
                        IpbTheme.customization.privacyUrl
                    )
                ),
                useComponent = useComponent
            )
            BrushedText(
                text = stringResource(R.string.sign_in_text),
                textAlign = TextAlign.Center,
                style = IpbTheme.typography.footnoteRegular,
                tint = IpbTheme.colors.textDisabled.asBrush()
            )
            Spacer(Modifier.weight(1f))
            AuthOrSkip(
                state = state.authOrSkipState, useComponent = useComponent
            )
        }
    }
}