package com.progressterra.ipbandroidview.ui.city

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.progressterra.ipbandroidview.components.ThemedLayout
import com.progressterra.ipbandroidview.components.ThemedTextButton
import com.progressterra.ipbandroidview.components.ThemedTextField
import com.progressterra.ipbandroidview.components.topbar.ThemedTopAppBar
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CityScreen(
    state: () -> CityState,
    back: () -> Unit,
    skip: () -> Unit,
    next: () -> Unit,
    editAddress: (String) -> Unit,
    onMapClick: (LatLng) -> Unit,
    onSuggestion: (Suggestion) -> Unit
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            title = { stringResource(id = R.string.verification_code) },
            onBack = back
        )
    }, bottomBar = {
        BottomHolder {
            ThemedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = next,
                text = { stringResource(id = R.string.ready) },
                enabled = state()::isDataValid,
            )
            Spacer(modifier = Modifier.size(8.dp))
            ThemedTextButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = skip,
                text = { stringResource(id = R.string.auth_skip) }
            )
        }
    }) { _, _ ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.medium)
        ) {
            val (map, address, background, suggestions) = createRefs()
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(55.751244, 37.618423), 10f)
            }
            var isAddressFocused by remember {
                mutableStateOf(false)
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
                onFocusChange = { isAddressFocused = it },
                text = state()::address,
                hint = { stringResource(id = R.string.address) },
                onChange = editAddress
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
                onMapClick = onMapClick, onMyLocationClick = {
                    onMapClick(LatLng(it.latitude, it.longitude))
                }, properties = MapProperties(isMyLocationEnabled = state().isPermissionGranted)
            )
            AddressSuggestions(
                modifier = Modifier.constrainAs(suggestions) {
                    width = Dimension.fillToConstraints
                    top.linkTo(address.bottom, 4.dp)
                    start.linkTo(address.start)
                    end.linkTo(address.end)
                },
                suggestions = state()::suggestions,
                isVisible = { state().isAddressInFocus && state().suggestions.isNotEmpty() },
                onSuggestion = onSuggestion
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