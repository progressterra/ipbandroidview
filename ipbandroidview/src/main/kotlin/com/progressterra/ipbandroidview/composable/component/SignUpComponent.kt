package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.shared.ui.StateBox
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.ext.print
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.Button
import com.progressterra.ipbandroidview.shared.ui.ButtonState
import com.progressterra.ipbandroidview.shared.ui.UseButton
import com.squaredem.composecalendar.ComposeCalendar
import java.time.LocalDate

data class SignUpComponentState(
    val phoneNumber: TextFieldState = TextFieldState(),
    val name: TextFieldState = TextFieldState(),
    val email: TextFieldState = TextFieldState(),
    val birthday: LocalDate = LocalDate.now(),
    val isDataValid: Boolean = false,
    val showCalendar: Boolean = false,
    val screenState: ScreenState = ScreenState.LOADING,
    val nextButtonState: ButtonState = ButtonState(),
    val skipButtonState: TextButtonState = TextButtonState(),
)

sealed class SignUpComponentEvent {

    object Refresh : SignUpComponentEvent()

    data class EditBirthday(val birthday: LocalDate) : SignUpComponentEvent()

    object CalendarDismiss : SignUpComponentEvent()

    object CalendarShow : SignUpComponentEvent()
}

interface UseSignUpComponent : UseButton, UseTextButton, UseTextField {

    fun handleEvent(id: String, event: SignUpComponentEvent)
}

@Composable
fun SignUpComponent(
    modifier: Modifier = Modifier,
    id: String,
    state: SignUpComponentState,
    useComponent: UseSignUpComponent,
) {
    ThemedLayout(modifier = modifier, topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.sign_up))
    }, bottomBar = {
        BottomHolder {
            Button(
                modifier = Modifier.fillMaxWidth(),
                id = "next",
                state = state.nextButtonState,
                useComponent = useComponent
            )
            Spacer(modifier = Modifier.size(8.dp))
            TextButton(
                modifier = Modifier.fillMaxWidth(),
                id = "skip",
                state = state.skipButtonState,
                useComponent = useComponent
            )
        }
    }) { _, _ ->
        StateBox(state = state.screenState,
            refresh = { useComponent.handleEvent(id, SignUpComponentEvent.Refresh) }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(IpbTheme.shapes.medium)
                        .background(IpbTheme.colors.surfaces)
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (state.showCalendar) {
                        ComposeCalendar(onDone = {
                            useComponent.handleEvent(id, SignUpComponentEvent.EditBirthday(it))
                            useComponent.handleEvent(id, SignUpComponentEvent.CalendarDismiss)
                        }, onDismiss = {
                            useComponent.handleEvent(
                                id, SignUpComponentEvent.CalendarDismiss
                            )
                        })
                    }
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        id = "name",
                        state = state.name,
                        useComponent = useComponent
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        id = "email",
                        state = state.email,
                        useComponent = useComponent
                    )
                    ThemedMimicField(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            useComponent.handleEvent(
                                id, SignUpComponentEvent.CalendarShow
                            )
                        },
                        text = state.birthday.print(),
                        hint = stringResource(id = R.string.birthday),
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        id = "phone",
                        state = state.phoneNumber,
                        useComponent = useComponent
                    )
                }
            }
        }
    }
}