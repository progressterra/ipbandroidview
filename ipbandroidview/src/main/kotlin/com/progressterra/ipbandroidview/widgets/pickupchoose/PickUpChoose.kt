package com.progressterra.ipbandroidview.widgets.pickupchoose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun PickUpChoose(
    modifier: Modifier = Modifier, state: PickUpChooseState, useComponent: UsePickUpChoose
) {
    Column(
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface.asBrush())
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(55.751244, 37.618423), 10f)
        }
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(330.dp)
                .clip(RoundedCornerShape(8.dp)),
            cameraPositionState = cameraPositionState,
            onMyLocationClick = {
                cameraPositionState.move(
                    CameraUpdateFactory.newLatLng(
                        LatLng(
                            it.latitude, it.longitude
                        )
                    )
                )
            },
            properties = MapProperties(isMyLocationEnabled = state.isPermissionGranted)
        ) {
            state.pickUpPoints.forEach { pickUpPoint ->
                Marker(state = rememberMarkerState(
                    position = LatLng(
                        pickUpPoint.latitude, pickUpPoint.longitude
                    )
                ), onClick = {
                    useComponent.handle(PickUpChooseEvent.Choose(pickUpPoint))
                    false
                })
            }
        }
        AnimatedVisibility(
            visible = state.currentPickUpPointInfo != null,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                BrushedText(
                    text = state.currentPickUpPointInfo?.address ?: "",
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.title
                )
                Spacer(modifier = Modifier.size(2.dp))
                BrushedText(
                    text = state.currentPickUpPointInfo?.pickupPointCode ?: "",
                    tint = IpbTheme.colors.textTertiary.asBrush(),
                    style = IpbTheme.typography.footnoteRegular
                )
                Spacer(modifier = Modifier.size(12.dp))
                BrushedText(
                    text = stringResource(R.string.work_hour),
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.body
                )
                Spacer(modifier = Modifier.size(4.dp))
                BrushedText(
                    text = state.currentPickUpPointInfo?.workHour ?: "",
                    tint = IpbTheme.colors.textSecondary.asBrush(),
                    style = IpbTheme.typography.footnoteRegular
                )
                Spacer(modifier = Modifier.size(12.dp))
                BrushedText(
                    text = stringResource(R.string.how_to_go),
                    tint = IpbTheme.colors.textPrimary.asBrush(),
                    style = IpbTheme.typography.body
                )
                Spacer(modifier = Modifier.size(4.dp))
                BrushedText(
                    text = state.currentPickUpPointInfo?.path ?: "",
                    tint = IpbTheme.colors.textSecondary.asBrush(),
                    style = IpbTheme.typography.footnoteRegular
                )
            }
        }
    }
}

@Composable
@Preview
private fun PickUpScreenPreview() {
    IpbTheme {
        PickUpChoose(
            state = PickUpChooseState(
                isPermissionGranted = false, pickUpPoints = listOf(
                    PickUpPointInfo(
                        price = SimplePrice(price = 0.1),
                        address = "sociis",
                        workHour = "detraxit",
                        latitude = 2.3,
                        type = "comprehensam",
                        longitude = 4.5,
                        pickupPointCode = "graecis",
                        path = "sed"
                    ), PickUpPointInfo(
                        price = SimplePrice(price = 6.7),
                        address = "cursus",
                        workHour = "feugiat",
                        latitude = 8.9,
                        type = "turpis",
                        longitude = 10.11,
                        pickupPointCode = "cursus",
                        path = "libris"
                    )
                ), currentPickUpPointInfo = null
            ), useComponent = UsePickUpChoose.Empty()
        )
    }
}