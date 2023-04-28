package com.progressterra.ipbandroidview.features.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun Search(
    modifier: Modifier = Modifier,
    state: SearchState,
    useComponent: UseSearch
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = state.text,
        enabled = state.enabled,
        onValueChange = { useComponent.handle(SearchEvent.OnTextChanged(it)) },
        placeholder = {
            BrushedText(
                text = stringResource(R.string.search),
                tint = IpbTheme.colors.textPrimary.asBrush(),
                style = IpbTheme.typography.subHeadlineRegular
            )
        },
        shape = CircleShape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = IpbTheme.colors.textPrimary.asColor(),
            disabledTextColor = IpbTheme.colors.textDisabled.asColor(),
            cursorColor = IpbTheme.colors.primary.asColor(),
            errorCursorColor = IpbTheme.colors.error.asColor(),
            focusedBorderColor = IpbTheme.colors.onSurface2.asColor(),
            unfocusedBorderColor = IpbTheme.colors.onSurface2.asColor(),
            disabledBorderColor = IpbTheme.colors.onSurface2.asColor(),
            errorBorderColor = IpbTheme.colors.onSurface2.asColor(),
            leadingIconColor = IpbTheme.colors.iconPrimary.asColor(),
            trailingIconColor = IpbTheme.colors.iconPrimary.asColor(),
            disabledLeadingIconColor = IpbTheme.colors.iconDisabled.asColor(),
            disabledTrailingIconColor = IpbTheme.colors.iconDisabled.asColor(),
            focusedLabelColor = IpbTheme.colors.textPrimary.asColor(),
            unfocusedLabelColor = IpbTheme.colors.textPrimary.asColor(),
            disabledLabelColor = IpbTheme.colors.textDisabled.asColor(),
            errorLabelColor = IpbTheme.colors.textPrimary.asColor(),
            errorTrailingIconColor = IpbTheme.colors.error.asColor(),
            errorLeadingIconColor = IpbTheme.colors.error.asColor(),
            placeholderColor = IpbTheme.colors.textPrimary.asColor(),
            disabledPlaceholderColor = IpbTheme.colors.textDisabled.asColor()
        ),
        singleLine = true,
        textStyle = IpbTheme.typography.subHeadlineRegular,
        trailingIcon = {
            BrushedIcon(
                tint = IpbTheme.colors.iconPrimary.asBrush(),
                resId = R.drawable.ic_search
            )
        }
    )
}