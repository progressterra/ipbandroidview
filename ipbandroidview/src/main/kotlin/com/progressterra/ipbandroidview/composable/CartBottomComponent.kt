package com.progressterra.ipbandroidview.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.composable.utils.SideBorder
import com.progressterra.ipbandroidview.composable.utils.sideBorder
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

private val lineWidth = 0.5.dp

data class CartBottomComponentState(
    val userExist: Boolean = false,
    val totalPrice: SimplePrice = SimplePrice(),
    val nextButtonState: ButtonState = ButtonState(),
    val authButtonState: ButtonState = ButtonState()
)

interface UseCartBottomComponent : UseButton

/**
 * auth, next - button components
 */
@Composable
fun CartBottomComponent(
    modifier: Modifier = Modifier,
    state: CartBottomComponentState,
    useComponent: UseCartBottomComponent
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(IpbTheme.colors.surfaces)
            .sideBorder(top = SideBorder(lineWidth, IpbTheme.colors.gray2))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.totalPrice.toString(),
            style = IpbTheme.typography.price,
            color = IpbTheme.colors.black
        )
        if (state.userExist) Button(
            id = "next", state = state.nextButtonState, useComponent = useComponent
        )
        else Button(
            id = "auth", state = state.authButtonState, useComponent = useComponent
        )
    }
}