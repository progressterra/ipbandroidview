package com.progressterra.ipbandroidview.city

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.progressterra.ipbandroidview.AppTheme
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedTextButton
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.composable.TopAppBarWithBackNav

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
                val (buttons, map, address, background) = createRefs()
                val smallMargin = AppTheme.dimensions.small
                val normalMargin = AppTheme.dimensions.normal
                Box(modifier = Modifier
                    .clip(RoundedCornerShape(AppTheme.dimensions.normal))
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
                    height = Dimension.wrapContent
                    width = Dimension.fillToConstraints
                    top.linkTo(background.top, normalMargin)
                    start.linkTo(background.start, normalMargin)
                    end.linkTo(background.end, normalMargin)
                },
                    text = state.address,
                    hint = stringResource(id = R.string.address),
                    onChange = { interactor.onAddress(it) })
                val singapore = LatLng(1.35, 103.87)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(singapore, 10f)
                }
                GoogleMap(
                    modifier = Modifier
                        .constrainAs(map) {
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                            top.linkTo(address.bottom, normalMargin)
                            start.linkTo(background.start, normalMargin)
                            end.linkTo(background.end, normalMargin)
                            bottom.linkTo(background.bottom, normalMargin)
                        }, cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(position = singapore),
                        title = "Singapore",
                        snippet = "Marker in Singapore"
                    )
                }
                Column(modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = AppTheme.dimensions.large,
                            topEnd = AppTheme.dimensions.large
                        )
                    )
                    .background(AppTheme.colors.surfaces)
                    .padding(AppTheme.dimensions.small)
                    .constrainAs(
                        buttons
                    ) {
                        height = Dimension.wrapContent
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
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.small))
                }
            }
        }
    }
}

@Preview
@Composable
fun CityScreenPreview() {
    AppTheme {
        CityScreen(CityState(), CityInteractor.Empty())
    }
}