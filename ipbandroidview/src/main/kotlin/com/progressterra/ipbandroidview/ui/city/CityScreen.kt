package com.progressterra.ipbandroidview.ui.city

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.AddressSuggestions
import com.progressterra.ipbandroidview.composable.BottomHolder
import com.progressterra.ipbandroidview.composable.component.ButtonComponent
import com.progressterra.ipbandroidview.composable.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTextButton
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CityScreen(
    state: CityState,
    interactor: CityInteractor,
    settings: CitySettings
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            title = stringResource(id = R.string.address),
            onBack = { interactor.onBack() }
        )
    }, bottomBar = {
        BottomHolder {
            ButtonComponent(
                modifier = Modifier.fillMaxWidth(),
                onClick = { interactor.onNext() },
                text = stringResource(id = R.string.ready),
                enabled = state.isDataValid,
            )
            if (settings.passable) {
                Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                ThemedTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { interactor.onSkip() },
                    text = stringResource(id = R.string.auth_skip)
                )
            }
        }
    }) { _, _ ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.small)
        ) {
            val (map, address, background, suggestions) = createRefs()
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(55.751244, 37.618423), 10f)
            }
            Box(modifier = Modifier
                .clip(AppTheme.shapes.medium)
                .background(AppTheme.colors.surfaces)
                .constrainAs(background) {
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                })
            ThemedTextField(
                modifier = Modifier.constrainAs(address) {
                    width = Dimension.fillToConstraints
                    top.linkTo(background.top, 12.dp)
                    start.linkTo(background.start, 12.dp)
                    end.linkTo(background.end, 12.dp)
                },
                text = state.address,
                hint = stringResource(id = R.string.address),
                onChange = { interactor.editAddress(it) }
            )
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
                onMapClick = { interactor.mapClick(it) },
                onMyLocationClick = { interactor.myLocationClick(it) },
                properties = MapProperties(isMyLocationEnabled = state.isPermissionGranted)
            )
            AddressSuggestions(
                modifier = Modifier.constrainAs(suggestions) {
                    width = Dimension.fillToConstraints
                    top.linkTo(address.bottom, 4.dp)
                    start.linkTo(address.start)
                    end.linkTo(address.end)
                },
                suggestions = state.suggestions,
                isVisible = state.suggestions.isNotEmpty(),
                onSuggestion = { interactor.onSuggestion(it) }
            )
        }
    }
}

@Preview
@Composable
private fun CityScreenPreview() {
    AppTheme {
    }
}