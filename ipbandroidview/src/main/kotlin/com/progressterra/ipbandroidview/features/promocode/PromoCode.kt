package com.progressterra.ipbandroidview.features.promocode

import androidx.compose.animation.animateContentSize
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
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField
import com.progressterra.processors.IpbSubState

@Immutable
data class PromoCodeState(
    val price: SimplePrice = SimplePrice(),
    @IpbSubState val code: TextFieldState = TextFieldState()
)

interface UsePromoCode : UseTextField

@Composable
fun PromoCode(
    modifier: Modifier = Modifier,
    state: PromoCodeState,
    useComponent: UsePromoCode
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .animateContentSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            state = state.code,
            useComponent = useComponent
        )
        if (!state.price.isEmpty()) {
            BrushedText(
                text = "Скидка ${state.price}",
                tint = IpbTheme.colors.textSecondary.asBrush(),
                style = IpbTheme.typography.footnoteRegular
            )
        }
    }
}