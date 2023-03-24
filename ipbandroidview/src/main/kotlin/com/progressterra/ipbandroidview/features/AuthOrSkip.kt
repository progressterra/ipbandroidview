package com.progressterra.ipbandroidview.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.button.UseButton

@Immutable
data class AuthOrSkipState(
    val auth: ButtonState = ButtonState(),
    val skip: ButtonState = ButtonState()
)

interface UseAuthOrSkip : UseButton

@Composable
fun AuthOrSkipWelcome(
    modifier: Modifier = Modifier,
    state: AuthOrSkipState,
    useAuthOrSkip: UseAuthOrSkip
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.auth,
            useComponent = useAuthOrSkip
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.skip,
            useComponent = useAuthOrSkip
        )
    }
}

@Composable
fun AuthOrSkip(
    modifier: Modifier = Modifier,
    state: AuthOrSkipState,
    useComponent: UseAuthOrSkip
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(IpbTheme.colors.surface1.asBrush())
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.auth,
            useComponent = useComponent
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            state = state.skip,
            useComponent = useComponent
        )
    }
}