package com.progressterra.ipbandroidview.signup

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.AppTheme
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedTextButton
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.composable.TopAppBarWithBackNav
import java.util.*

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
                        .clip(RoundedCornerShape(AppTheme.dimensions.normal))
                        .background(AppTheme.colors.surfaces)
                        .padding(AppTheme.dimensions.normal)
                ) {
                    val context = LocalContext.current
                    val calendar = Calendar.getInstance()
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH)
                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                    calendar.time = Date()
                    val datePickerDialog = DatePickerDialog(
                        context,
                        { _: DatePicker, pickedYear: Int, pickedMonth: Int, pickedDay: Int ->
                            interactor.onBirthday("$pickedDay.${pickedMonth + 1}/$pickedYear")
                        },
                        year,
                        month,
                        day
                    )
                    ThemedTextField(modifier = Modifier.fillMaxWidth(),
                        text = state.name,
                        hint = stringResource(id = R.string.name_surname),
                        onChange = { interactor.onName(it) })
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.normal))
                    ThemedTextField(modifier = Modifier.fillMaxWidth(),
                        text = state.email,
                        hint = stringResource(id = R.string.email),
                        onChange = { interactor.onEmail(it) })
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.normal))
                    ThemedTextField(modifier = Modifier
                        .fillMaxWidth()
                        .clickable { datePickerDialog.show() },
                        text = state.birthday,
                        hint = stringResource(id = R.string.birthday),
                        onChange = {})
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.normal))
                    ThemedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.phoneNumber,
                        hint = stringResource(id = R.string.phone_number),
                        onChange = {},
                        enabled = false
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                topStart = AppTheme.dimensions.large,
                                topEnd = AppTheme.dimensions.large
                            )
                        )
                        .background(AppTheme.colors.surfaces)
                        .padding(AppTheme.dimensions.small)
                ) {
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
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
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
                "+7 (996) 697-76-76", "Даниил", "lala@email.com", "12.12.2012", true
            ), SignUpInteractor.Empty()
        )
    }
}