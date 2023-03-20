package com.progressterra.ipbandroidview.features

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Immutable
data class ProshkaSearchState(
    val id: String = "",
    val text: String = "",
    val enabled: Boolean = true
)

sealed class ProshkaSearchEvent {

    class OnTextChanged(val text: String) : ProshkaSearchEvent()
}

interface UseProshkaSearch {

    fun handleEvent(id: String, event: ProshkaSearchEvent)
}

@Composable
fun ProshkaSearch(
    modifier: Modifier = Modifier,
    state: ProshkaSearchState,
    useComponent: UseProshkaSearch
) {
    OutlinedTextField(
        modifier = modifier,
        value = state.text,
        enabled = state.enabled,
        onValueChange = { useComponent.handleEvent(state.id, ProshkaSearchEvent.OnTextChanged(it)) },
        placeholder = {
            BrushedText(
                text = stringResource(R.string.search),
                tint = IpbTheme.colors.textPrimary1.asBrush(),
                style = IpbTheme.typography.secondaryText
            )
        },
        shape = CircleShape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = IpbTheme.colors.textPrimary1.asColor(),
            disabledTextColor = IpbTheme.colors.textDisabled.asColor(),
            cursorColor = IpbTheme.colors.primary.asColor(),
            errorCursorColor = IpbTheme.colors.error.asColor(),
            focusedBorderColor = IpbTheme.colors.onSurface2.asColor(),
            unfocusedBorderColor = IpbTheme.colors.onSurface2.asColor(),
            disabledBorderColor = IpbTheme.colors.onSurface2.asColor(),
            errorBorderColor = IpbTheme.colors.onSurface2.asColor(),
            leadingIconColor = IpbTheme.colors.iconPrimary1.asColor(),
            trailingIconColor = IpbTheme.colors.iconPrimary1.asColor(),
            disabledLeadingIconColor = IpbTheme.colors.iconDisabled1.asColor(),
            disabledTrailingIconColor = IpbTheme.colors.iconDisabled1.asColor(),
            focusedLabelColor = IpbTheme.colors.textPrimary1.asColor(),
            unfocusedLabelColor = IpbTheme.colors.textPrimary1.asColor(),
            disabledLabelColor = IpbTheme.colors.textDisabled.asColor(),
            errorLabelColor = IpbTheme.colors.textPrimary1.asColor(),
            errorTrailingIconColor = IpbTheme.colors.error.asColor(),
            errorLeadingIconColor = IpbTheme.colors.error.asColor(),
            placeholderColor = IpbTheme.colors.textPrimary1.asColor(),
            disabledPlaceholderColor = IpbTheme.colors.textDisabled.asColor()
        ),
        singleLine = true,
        textStyle = IpbTheme.typography.secondaryText,
        trailingIcon = {
            BrushedIcon(
                tint = IpbTheme.colors.iconPrimary1.asBrush(),
                resId = R.drawable.ic_search_pro
            )
        }
    )
}