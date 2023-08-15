package com.progressterra.ipbandroidview.pages.bankcarddetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhoto
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState

@Composable
fun BankCardDetailsScreen(
    modifier: Modifier = Modifier,
    state: BankCardDetailsScreenState,
    useComponent: UseBankCardDetailsScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = if (state.isNew) stringResource(id = R.string.card_adding) else stringResource(
                id = R.string.card_viewing
            ),
            showBackButton = true,
            useComponent = useComponent
        )
    },
        bottomBar = {
            if (state.canBeEdited) {
                Column(
                    modifier = modifier
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(IpbTheme.colors.surface.asBrush())
                        .padding(8.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.apply,
                        useComponent = useComponent,
                        title = stringResource(R.string.ready)
                    )
                }
            }
        }) { _, _ ->
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                state.entries.take(2).forEach {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = it,
                        useComponent = useComponent,
                        backgroundColor = IpbTheme.colors.background.asColor()
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    state.entries.takeLast(2).forEach {
                        TextField(
                            modifier = Modifier.weight(1f),
                            state = it,
                            useComponent = useComponent,
                            backgroundColor = IpbTheme.colors.background.asColor()
                        )
                    }
                }
            }
            DocumentPhoto(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.photo,
                useComponent = useComponent
            )
        }
    }
}

@Composable
@Preview
private fun BankCardDetailsScreenPreview() {
    IpbTheme {
        BankCardDetailsScreen(
            state = BankCardDetailsScreenState(
                entries = listOf(
                    TextFieldState(placeholder = "Номер карты"),
                    TextFieldState(placeholder = "Имя"),
                    TextFieldState(placeholder = "Срок действия"),
                    TextFieldState(placeholder = "CVV/CVC")
                )
            ),
            useComponent = UseBankCardDetailsScreen.Empty()
        )
    }
}