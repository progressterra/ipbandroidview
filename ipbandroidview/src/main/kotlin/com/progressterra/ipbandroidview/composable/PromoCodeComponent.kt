package com.progressterra.ipbandroidview.composable

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField
import com.progressterra.ipbandroidview.model.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Immutable
data class PromoCodeComponentState(

    val promoCode: SimplePrice = SimplePrice(),

    val promoCodeState: TextFieldState = TextFieldState()
)

interface UsePromoCodeComponent : UseTextField

/**
 * code - text field
 */
@Composable
fun PromoCodeComponent(
    modifier: Modifier = Modifier,
    state: PromoCodeComponentState,
    useComponent: UsePromoCodeComponent
) {
    Column(
        modifier = modifier
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .animateContentSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            id = "code",
            state = state.promoCodeState,
            useComponent = useComponent
        )
        if (!state.promoCode.isEmpty()) {
            Text(
                text = "Скидка ${state.promoCode}",
                color = IpbTheme.colors.primary,
                style = IpbTheme.typography.tertiary
            )
        }
    }
}