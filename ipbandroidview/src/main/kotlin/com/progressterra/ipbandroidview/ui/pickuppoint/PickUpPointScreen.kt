package com.progressterra.ipbandroidview.ui.pickuppoint

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.composable.ThemedTopAppBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun PickUpPointScreen(
    state: PickUpPointState,
    interactor: PickUpPointInteractor
) {
    ThemedLayout(topBar = {
        ThemedTopAppBar(
            title = stringResource(id = R.string.pick_up_point), onBack = { interactor.onBack() }
        )
    }, bottomBar = {
//        BottomHolder {
//            Button(
//                modifier = Modifier.fillMaxWidth(),
//                onClick = { interactor.onNext() },
//                text = stringResource(id = R.string.choose),
//                enabled = state.currentPickUpPointInfo != null,
//            )
//        }
    }) { _, _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .clip(IpbTheme.shapes.medium)
                .background(IpbTheme.colors.surfaces)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(LatLng(55.751244, 37.618423), 10f)
            }
            GoogleMap(
                modifier = Modifier
                    .weight(1f)
                    .clip(IpbTheme.shapes.small),
                cameraPositionState = cameraPositionState,
                onMyLocationClick = {
                    cameraPositionState.move(
                        CameraUpdateFactory.newLatLng(
                            LatLng(
                                it.latitude,
                                it.longitude
                            )
                        )
                    )
                },
                properties = MapProperties(isMyLocationEnabled = state.isPermissionGranted)
            ) {
                state.pickUpPoints.forEach { pickUpPoint ->
                    Marker(
                        state = rememberMarkerState(
                            position = LatLng(
                                pickUpPoint.latitude,
                                pickUpPoint.longitude
                            )
                        ),
                        onClick = {
                            interactor.choose(pickUpPoint)
                            false
                        }
                    )
                }
            }
            AnimatedVisibility(
                modifier = Modifier.weight(1f),
                visible = state.currentPickUpPointInfo != null,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = state.currentPickUpPointInfo?.address ?: "",
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.title
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(
                        text = state.currentPickUpPointInfo?.pickupPointCode ?: "",
                        color = IpbTheme.colors.gray2,
                        style = IpbTheme.typography.tertiary
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = stringResource(R.string.work_hour),
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.primary
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = state.currentPickUpPointInfo?.workHour ?: "",
                        color = IpbTheme.colors.gray2,
                        style = IpbTheme.typography.tertiary
                    )
                    Spacer(modifier = Modifier.size(12.dp))
                    Text(
                        text = stringResource(R.string.how_to_go),
                        color = IpbTheme.colors.black,
                        style = IpbTheme.typography.primary
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = state.currentPickUpPointInfo?.path ?: "",
                        color = IpbTheme.colors.gray2,
                        style = IpbTheme.typography.tertiary
                    )
                }
            }
        }
    }
}