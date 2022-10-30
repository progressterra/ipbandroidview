package com.progressterra.ipbandroidview.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.BottomHolder
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.ThemedMimicField
import com.progressterra.ipbandroidview.components.ThemedTextButton
import com.progressterra.ipbandroidview.components.ThemedTextField
import com.progressterra.ipbandroidview.components.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme
import com.squaredem.composecalendar.ComposeCalendar
import java.time.LocalDate

@Composable
fun SignUpScreen(state: SignUpState, interactor: SignUpInteractor) {
    Scaffold(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.sign_up))
    }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)
                .padding(padding)
                .padding(
                    start = 8.dp, top = 8.dp, end = 8.dp
                ),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(AppTheme.roundings.mediumRounding))
                    .background(AppTheme.colors.surfaces)
                    .padding(12.dp)
            ) {
                if (state.showCalendar) {
                    ComposeCalendar(onDone = {
                        interactor.editBirthday(
                            "${it.dayOfMonth}.${it.monthValue}.${it.year}", it
                        )
                        interactor.closeCalendar()
                    }, onDismiss = { interactor.closeCalendar() })
                }
                ThemedTextField(modifier = Modifier.fillMaxWidth(),
                    text = state.name,
                    hint = stringResource(id = R.string.name_surname),
                    onChange = { interactor.editName(it) })
                Spacer(modifier = Modifier.size(12.dp))
                ThemedTextField(modifier = Modifier.fillMaxWidth(),
                    text = state.email,
                    hint = stringResource(id = R.string.email),
                    onChange = { interactor.editEmail(it) })
                Spacer(modifier = Modifier.size(12.dp))
                ThemedMimicField(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { interactor.openCalendar() },
                    text = state.birthday,
                    hint = stringResource(id = R.string.birthday),
                )
                Spacer(modifier = Modifier.size(12.dp))
                ThemedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.phoneNumber,
                    hint = stringResource(id = R.string.phone_number),
                    enabled = false
                )
            }
            BottomHolder {
                ThemedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { interactor.next() },
                    text = stringResource(id = R.string.next),
                    enabled = state.isDataValid,
                )
                Spacer(modifier = Modifier.size(8.dp))
                ThemedTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { interactor.skip() },
                    text = stringResource(id = R.string.auth_skip)
                )
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreviewEmpty() {
    AppTheme {
        SignUpScreen(SignUpState(phoneNumber = "+7 (996) 697-76-76"), SignUpInteractor.Empty())
    }
}


@Preview
@Composable
private fun SignUpScreenPreviewFilled() {
    AppTheme {
        SignUpScreen(
            SignUpState(
                "+7 (996) 697-76-76",
                "Даниил",
                "lala@email.com",
                "12.12.2012",
                LocalDate.now(),
                true
            ), SignUpInteractor.Empty()
        )
    }
}