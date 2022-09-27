package com.progressterra.ipbandroidview.city

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.ThemedButton
import com.progressterra.ipbandroidview.composable.ThemedTextButton
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.composable.TopAppBarWithBackNav
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun CityScreen(state: CityState, interactor: CityInteractor) {
    Scaffold(topBar = {
        TopAppBarWithBackNav(title = stringResource(id = R.string.city),
            onBack = { interactor.onBack() })
    }) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = AppTheme.colors.background
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = AppTheme.dimensions.milli,
                        top = AppTheme.dimensions.milli,
                        end = AppTheme.dimensions.milli
                    )
            ) {
                val (buttons, map, address, background, suggestions, fab) = createRefs()
                val smallestMargin = AppTheme.dimensions.nano
                val smallMargin = AppTheme.dimensions.milli
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
                var isFocused by remember { mutableStateOf(false) }
                ThemedTextField(modifier = Modifier
                    .constrainAs(address) {
                        height = Dimension.wrapContent
                        width = Dimension.fillToConstraints
                        top.linkTo(background.top, normalMargin)
                        start.linkTo(background.start, normalMargin)
                        end.linkTo(background.end, normalMargin)
                    }
                    .onFocusChanged { isFocused = it.isFocused },
                    text = state.address,
                    hint = stringResource(id = R.string.address),
                    onChange = { interactor.onAddress(it) })
                val singapore = LatLng(1.35, 103.87)
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition.fromLatLngZoom(singapore, 10f)
                }
                GoogleMap(
                    modifier = Modifier.constrainAs(map) {
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
                AnimatedVisibility(
                    modifier = Modifier.constrainAs(suggestions) {
                        height = Dimension.wrapContent
                        width = Dimension.fillToConstraints
                        top.linkTo(address.bottom, smallestMargin)
                        start.linkTo(address.start)
                        end.linkTo(address.end)
                    },
                    visible = isFocused && state.suggestions.isNotEmpty(),
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    Card(
                        elevation = AppTheme.dimensions.nano,
                        shape = RoundedCornerShape(AppTheme.dimensions.milli)
                    ) {
                        LazyColumn {
                            items(state.suggestions) {
                                Column(
                                    modifier = Modifier
                                        .padding(
                                            horizontal = AppTheme.dimensions.milli,
                                            vertical = AppTheme.dimensions.micro
                                        )
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = it.address,
                                        color = AppTheme.colors.black,
                                        style = AppTheme.typography.secondaryText
                                    )
                                    Spacer(modifier = Modifier.size(AppTheme.dimensions.pico))
                                    Text(
                                        text = it.city,
                                        color = AppTheme.colors.gray2,
                                        style = AppTheme.typography.tertiaryText
                                    )
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.size(AppTheme.dimensions.nano))
                            }
                        }
                    }
                }

                Column(modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = AppTheme.dimensions.mega,
                            topEnd = AppTheme.dimensions.mega
                        )
                    )
                    .background(AppTheme.colors.surfaces)
                    .padding(AppTheme.dimensions.milli)
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
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.milli))
                    ThemedTextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { interactor.onSkip() },
                        text = stringResource(id = R.string.auth_skip)
                    )
                    Spacer(modifier = Modifier.size(AppTheme.dimensions.milli))
                }

                FloatingActionButton(
                    modifier = Modifier.constrainAs(fab) {
                        bottom.linkTo(map.bottom, normalMargin)
                        end.linkTo(map.end, normalMargin)
                    }.size(40.dp),
                    onClick = { /*TODO*/ },
                    backgroundColor = AppTheme.colors.primary
                ) {
                    Icon(
                        modifier = Modifier.size(AppTheme.dimensions.mega),
                        tint = AppTheme.colors.surfaces,
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = stringResource(id = R.string.my_location)
                    )
                }
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