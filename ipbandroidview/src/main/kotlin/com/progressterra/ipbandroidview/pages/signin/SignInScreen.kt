package com.progressterra.ipbandroidview.pages.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkText
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextData
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier, state: SignInScreenState, useComponent: UseSignInScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.authorization), useComponent = useComponent
            )
        }, bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.auth,
                    title = stringResource(R.string.auth_button),
                    useComponent = useComponent
                )
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.skip,
                    title = stringResource(R.string.skip_yet),
                    useComponent = useComponent
                )
            }
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
                focusOnAppear = true
            )
            Spacer(Modifier.height(20.dp))
            LinkText(
                linkTextData = listOf(
                    LinkTextData(stringResource(R.string.auth_warning_0)),
                    LinkTextData(stringResource(R.string.offer), IpbAndroidViewSettings.OFFER_URL),
                    LinkTextData(stringResource(R.string.and)),
                    LinkTextData(
                        stringResource(R.string.privacy_policy), IpbAndroidViewSettings.PRIVACY_URL
                    )
                ),
                useComponent = useComponent,
                style = IpbTheme.typography.footnoteRegular,
                brush = IpbTheme.colors.textDisabled.asBrush()
            )
        }
    }
}