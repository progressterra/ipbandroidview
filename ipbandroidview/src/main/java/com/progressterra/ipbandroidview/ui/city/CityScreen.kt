package com.progressterra.ipbandroidview.ui.city

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.*
import com.progressterra.ipbandroidview.theme.AppTheme

private val moveAnimationDuration = 1000L

@Composable
fun CityScreen(state: CityState, interactor: CityInteractor) {
    Scaffold(topBar = {
        TopAppBarWithBackNav(
            title = stringResource(id = R.string.city),
            onBack = { interactor.onBack() })
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = AppTheme.colors.background
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = AppTheme.dimensions.small,
                        top = AppTheme.dimensions.small,
                        end = AppTheme.dimensions.small
                    )
            ) {
                val (buttons, map, address, background, suggestions, fab) = createRefs()
                val extraSmallMargin = AppTheme.dimensions.extraSmall
                val smallMargin = AppTheme.dimensions.small
                val regularMargin = AppTheme.dimensions.regular
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(AppTheme.dimensions.regular))
                    .background(AppTheme.colors.surfaces)
                    .constrainAs(background) {
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(buttons.top, smallMargin)
                    })
                ThemedTextField(modifier = Modifier.constrainAs(address) {
                    width = Dimension.fillToConstraints
                    top.linkTo(background.top, regularMargin)
                    start.linkTo(background.start, regularMargin)
                    end.linkTo(background.end, regularMargin)
                },
                    onFocusChange = { interactor.onAddressFocus(it) },
                    text = state.address,
                    hint = stringResource(id = R.string.address),
                    onChange = { interactor.onAddress(it) })
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(LatLng(55.751244, 37.618423), 5f)
                }
                LaunchedEffect(key1 = state.marker) {
                    if (!state.marker.isEmpty()) cameraPositionState.animate(
                        CameraUpdateFactory.newLatLng(
                            state.marker.latLng
                        )
                    )
                }
                GoogleMap(modifier = Modifier.constrainAs(map) {
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                    top.linkTo(address.bottom, regularMargin)
                    start.linkTo(background.start, regularMargin)
                    end.linkTo(background.end, regularMargin)
                    bottom.linkTo(background.bottom, regularMargin)
                }, cameraPositionState = cameraPositionState, uiSettings = MapUiSettings(
                    myLocationButtonEnabled = false,
                    mapToolbarEnabled = false,
                    zoomControlsEnabled = false,
                    compassEnabled = false
                ), onMapClick = { interactor.onMapClick(it) }) {
                    if (!state.marker.isEmpty()) Marker(latLng = state.marker.latLng)
                }
                AddressSuggestions(modifier = Modifier.constrainAs(suggestions) {
                    width = Dimension.fillToConstraints
                    top.linkTo(address.bottom, extraSmallMargin)
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
                        onClick = { interactor.onNext() },
                        text = stringResource(id = R.string.ready),
                        enabled = state.isDataValid,
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                    ThemedTextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { interactor.onSkip() },
                        text = stringResource(id = R.string.auth_skip)
                    )
                }
                LocationButton(modifier = Modifier.constrainAs(fab) {
                    bottom.linkTo(map.bottom, regularMargin)
                    end.linkTo(map.end, regularMargin)
                }, onClick = { interactor.onMyLocation() })
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