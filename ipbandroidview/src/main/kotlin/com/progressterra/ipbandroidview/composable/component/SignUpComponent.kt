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
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedMimicField
import com.progressterra.ipbandroidview.composable.ThemedTextButton
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.ext.print
import com.progressterra.ipbandroidview.theme.AppTheme
import com.squaredem.composecalendar.ComposeCalendar
import java.time.LocalDate

data class SignUpComponentState(
    val phoneNumber: String = "",
    val name: String = "",
    val email: String = "",
    val birthday: LocalDate = LocalDate.now(),
    val isDataValid: Boolean = false,
    val showCalendar: Boolean = false,
    val screenState: ScreenState = ScreenState.LOADING
)

interface SignUpComponentInteractor {

    fun onNext()

    fun onSkip()

    fun refresh()

    fun editName(name: String)

    fun editEmail(email: String)

    fun editBirthday(birthday: LocalDate)

    fun calendarDismiss()

    fun openCalendar()

    class Empty : SignUpComponentInteractor {

        override fun openCalendar() = Unit

        override fun onNext() = Unit

        override fun onSkip() = Unit

        override fun refresh() = Unit

        override fun editName(name: String) = Unit

        override fun editEmail(email: String) = Unit

        override fun editBirthday(birthday: LocalDate) = Unit

        override fun calendarDismiss() = Unit
    }
}

/**
 * @param modifier - modifier for the whole component
 * @param state - state of the component
 * @param interactor - interactor of the component
 */
@Composable
fun SignUpComponent(
    modifier: Modifier = Modifier,
    state: SignUpComponentState,
    interactor: SignUpComponentInteractor
) {
    ThemedLayout(modifier = modifier, topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.sign_up))
    }, bottomBar = {
        BottomHolder {
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.onNext() },
                text = stringResource(id = R.string.next),
                enabled = state.isDataValid,
            )
            Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
            ThemedTextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.onSkip() },
                text = stringResource(id = R.string.auth_skip)
            )
        }
    }) { _, _ ->
        StateBox(state = state.screenState, refresh = { interactor.refresh() }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(AppTheme.dimensions.small)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(AppTheme.shapes.medium)
                        .background(AppTheme.colors.surfaces)
                        .padding(AppTheme.dimensions.medium),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.medium)
                ) {
                    if (state.showCalendar) {
                        ComposeCalendar(onDone = {
                            interactor.editBirthday(it)
                            interactor.calendarDismiss()
                        }, onDismiss = { interactor.calendarDismiss() })
                    }
                    ThemedTextField(modifier = Modifier.fillMaxWidth(),
                        text = state.name,
                        hint = stringResource(id = R.string.name_surname),
                        onChange = { interactor.editName(it) })
                    ThemedTextField(modifier = Modifier.fillMaxWidth(),
                        text = state.email,
                        hint = stringResource(id = R.string.email),
                        onChange = { interactor.editEmail(it) })
                    ThemedMimicField(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { interactor.openCalendar() },
                        text = state.birthday.print(),
                        hint = stringResource(id = R.string.birthday),
                    )
                    ThemedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.phoneNumber,
                        hint = stringResource(id = R.string.phone_number),
                        enabled = false
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun SignUpComponentPreview() {
    AppTheme {
        SignUpComponent(
            state = SignUpComponentState(), interactor = SignUpComponentInteractor.Empty()
        )
    }
}