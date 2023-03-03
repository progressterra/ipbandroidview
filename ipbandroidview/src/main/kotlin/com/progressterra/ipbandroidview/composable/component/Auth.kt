package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.LinkText
import com.progressterra.ipbandroidview.composable.LinkTextData
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.utils.PhoneVisualTransformation
import com.progressterra.ipbandroidview.composable.utils.RUSSIAN_PHONE_MASK
import com.progressterra.ipbandroidview.theme.AppTheme

@Immutable
data class AuthState(
    val next: ButtonState = ButtonState(),
    val skip: TextButtonState = TextButtonState(),
    val phone: TextFieldState = TextFieldState()
) {

    fun updateNextEnabled(
        enabled: Boolean
    ) = copy(
        next = next.updateEnabled(enabled)
    )

    fun updatePhoneText(
        text: String
    ) = copy(
        phone = phone.updateText(text)
    )
}

sealed class AuthEvent {

    data class UrlClick(val url: String) : AuthEvent()
}

interface UseAuth : UseButton, UseTextButton, UseTextField {
    fun handleEvent(id: String, event: AuthEvent)
}

/**
 * phone - text field
 * next - button
 * skip - text button
 */
@Composable
fun AuthComponent(
    id: String,
    modifier: Modifier = Modifier,
    state: AuthState,
    useComponent: UseAuth,
    canBeSkipped: Boolean,
    offerUrl: String,
    policyUrl: String
) {
    ThemedLayout(modifier = modifier, topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.authorization))
    }, bottomBar = {
        BottomHolder {
            Button(
                id = "next",
                modifier = Modifier.fillMaxWidth(),
                useComponent = useComponent,
                state = state.next
            )
            if (canBeSkipped) {
                Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                TextButton(
                    id = "skip",
                    modifier = Modifier.fillMaxWidth(),
                    useComponent = useComponent,
                    state = state.skip
                )
            }
        }
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.small)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppTheme.shapes.medium)
                        .background(AppTheme.colors.surfaces)
                        .padding(AppTheme.dimensions.medium)
                ) {
                    TextField(
                        id = "phone",
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                        state = state.phone,
                        useComponent = useComponent,
                        visualTransformation = PhoneVisualTransformation(RUSSIAN_PHONE_MASK)
                    )
                }
                LinkText(
                    linkTextData = listOf(
                        LinkTextData(text = stringResource(id = R.string.auth_warning_0)),
                        LinkTextData(text = stringResource(id = R.string.offer),
                            tag = "offer",
                            annotation = offerUrl,
                            onClick = { useComponent.handleEvent(id, AuthEvent.UrlClick(it)) }),
                        LinkTextData(text = stringResource(id = R.string.and)),
                        LinkTextData(text = stringResource(id = R.string.privacy_policy),
                            tag = "privacy policy",
                            annotation = policyUrl,
                            onClick = { useComponent.handleEvent(id, AuthEvent.UrlClick(it)) }),
                    ), modifier = Modifier.padding(top = AppTheme.dimensions.small)
                )
            }
        }
    }
}