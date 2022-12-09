package com.progressterra.ipbandroidview.ui.signup

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
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.StateBox
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedMimicField
import com.progressterra.ipbandroidview.composable.ThemedTextButton
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.ext.print
import com.progressterra.ipbandroidview.theme.AppTheme
import com.squaredem.composecalendar.ComposeCalendar
import java.time.LocalDate

@Composable
fun SignUpScreen(
    state: () -> SignUpState,
    skip: () -> Unit,
    next: () -> Unit,
    refresh: () -> Unit,
    editBirthday: (LocalDate) -> Unit,
    editEmail: (String) -> Unit,
    editName: (String) -> Unit,
    openCalendar: () -> Unit,
    closeCalendar: () -> Unit
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(title = stringResource(id = R.string.sign_up))
    }, bottomBar = {
        BottomHolder {
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = next,
                text = stringResource(id = R.string.next),
                enabled = state()::isDataValid,
            )
            Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
            ThemedTextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = skip,
                text = stringResource(id = R.string.auth_skip)
            )
        }
    }) { _, _ ->
        StateBox(
            state = state()::screenState,
            refresh = refresh
        ) {
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
                    if (state().showCalendar) {
                        ComposeCalendar(onDone = {
                            editBirthday(it)
                            closeCalendar()
                        }, onDismiss = closeCalendar)
                    }
                    ThemedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = state()::name,
                        hint = stringResource(id = R.string.name_surname),
                        onChange = editName
                    )
                    ThemedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = state()::email,
                        hint = stringResource(id = R.string.email),
                        onChange = editEmail
                    )
                    ThemedMimicField(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = openCalendar,
                        text = state().birthday::print,
                        hint = stringResource(id = R.string.birthday),
                    )
                    ThemedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        text = state()::phoneNumber,
                        hint = stringResource(id = R.string.phone_number),
                        enabled = { false }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreviewEmpty() {
    AppTheme {
    }
}