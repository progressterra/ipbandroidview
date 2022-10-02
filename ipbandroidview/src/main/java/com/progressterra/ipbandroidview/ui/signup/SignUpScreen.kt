package com.progressterra.ipbandroidview.ui.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.*
import com.progressterra.ipbandroidview.theme.AppTheme
import com.squaredem.composecalendar.ComposeCalendar
import java.time.LocalDate

@Composable
fun SignUpScreen(state: SignUpState, interactor: SignUpInteractor) {
    Scaffold(topBar = {
        TopAppBarWithBackNav(title = stringResource(id = R.string.signup),
            onBack = { interactor.onBack() })
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = AppTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = AppTheme.dimensions.small,
                        top = AppTheme.dimensions.small,
                        end = AppTheme.dimensions.small
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(AppTheme.dimensions.regular))
                        .background(AppTheme.colors.surfaces)
                        .padding(AppTheme.dimensions.regular)
                ) {
                    if (state.showCalendar) {
                        ComposeCalendar(onDone = {
                            interactor.onBirthday(
                                "${it.dayOfMonth}.${it.monthValue}.${it.year}", it
                            )
                            interactor.closeCalendar()
                        }, onDismiss = { interactor.closeCalendar() })
                    }
                    ThemedTextField(modifier = Modifier.fillMaxWidth(),
                        initialText = state.name,
                        hint = stringResource(id = R.string.name_surname),
                        onChange = { interactor.onName(it) })
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.regular))
                    ThemedTextField(modifier = Modifier.fillMaxWidth(),
                        initialText = state.email,
                        hint = stringResource(id = R.string.email),
                        onChange = { interactor.onEmail(it) })
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.regular))
                    ThemedMimicField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { interactor.openCalendar() },
                        text = state.birthday,
                        hint = stringResource(id = R.string.birthday),
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.regular))
                    ThemedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        initialText = state.phoneNumber,
                        hint = stringResource(id = R.string.phone_number),
                        enabled = false
                    )
                }
                BottomHolder {
                    ThemedButton(
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
            }
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreviewEmpty() {
    AppTheme {
        SignUpScreen(SignUpState(phoneNumber = "+7 (996) 697-76-76"), SignUpInteractor.Empty())
    }
}


@Preview
@Composable
fun SignUpScreenPreviewFilled() {
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