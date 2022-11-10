package com.progressterra.ipbandroidview.ui.city

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.components.AddressSuggestions
import com.progressterra.ipbandroidview.components.BottomHolder
import com.progressterra.ipbandroidview.components.ThemedButton
import com.progressterra.ipbandroidview.components.ThemedTextButton
import com.progressterra.ipbandroidview.components.ThemedTextField
import com.progressterra.ipbandroidview.components.topbar.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CityScreen(state: CityState, interactor: CityInteractor) {
    Scaffold(topBar = {
        ThemedTopAppBar(
            title = stringResource(id = R.string.verification_code),
            onBack = { interactor.back() })
    }) { padding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.background)
                .padding(padding)
                .padding(
                    start = 8.dp, top = 8.dp, end = 8.dp
                )
        ) {
            val (buttons, map, address, background, suggestions) = createRefs()
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(55.751244, 37.618423), 10f)
            }
            var isAddressFocused by remember {
                mutableStateOf(false)
            }
            val scope = rememberCoroutineScope()
            Box(modifier = Modifier
                .clip(AppTheme.shapes.medium)
                .background(AppTheme.colors.surfaces)
                .constrainAs(background) {
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(buttons.top, 8.dp)
                })
            ThemedTextField(modifier = Modifier.constrainAs(address) {
                width = Dimension.fillToConstraints
                top.linkTo(background.top, 12.dp)
                start.linkTo(background.start, 12.dp)
                end.linkTo(background.end, 12.dp)
            },
                onFocusChange = { isAddressFocused = it },
                text = state.address,
                hint = stringResource(id = R.string.address),
                onChange = { interactor.editAddress(it) })
            GoogleMap(
                modifier = Modifier
                    .clip(AppTheme.shapes.small)
                    .constrainAs(map) {
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                        top.linkTo(address.bottom, 12.dp)
                        start.linkTo(background.start, 12.dp)
                        end.linkTo(background.end, 12.dp)
                        bottom.linkTo(background.bottom, 12.dp)
                    },
                cameraPositionState = cameraPositionState,
                onMapClick = { interactor.onMapClick(it) }, onMyLocationClick = {
                    interactor.onMapClick(LatLng(it.latitude, it.longitude))
                }, properties = MapProperties(isMyLocationEnabled = state.isPermissionGranted)
            )
            AddressSuggestions(modifier = Modifier.constrainAs(suggestions) {
                width = Dimension.fillToConstraints
                top.linkTo(address.bottom, 4.dp)
                start.linkTo(address.start)
                end.linkTo(address.end)
            },
                suggestions = state.suggestions,
                isVisible = state.isAddressInFocus && state.suggestions.isNotEmpty(),
                onSuggestion = { interactor.onSuggestion(it) })
            BottomHolder(modifier = Modifier.constrainAs(
                buttons
            ) {
                width = Dimension.fillToConstraints
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                ThemedButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { interactor.next() },
                    text = stringResource(id = R.string.ready),
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
private fun CityScreenPreview() {
    AppTheme {
        CityScreen(CityState(), CityInteractor.Empty())
    }
}

@Preview
@Composable
private fun CityScreenExpandedSuggestionPreview() {
    AppTheme {
        CityScreen(
            CityState(
                suggestions = listOf(
                    Suggestion("Some address 1", "Some city 1"),
                    Suggestion("Some address 2", "Some city 2"),
                    Suggestion("Some address 3", "Some city 3")
                )
            ), CityInteractor.Empty()
        )
    }
}