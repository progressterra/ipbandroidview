package com.progressterra.ipbandroidview.widgets.pickupchoose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
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
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText

@Composable
fun PickUpChoose(
    modifier: Modifier = Modifier,
    state: PickUpChooseState,
    useComponent: UsePickUpChoose
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(IpbTheme.colors.surface1.asBrush())
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(55.751244, 37.618423), 10f)
        }
        GoogleMap(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(8.dp)),
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
                        useComponent.handle(PickUpChooseEvent.Choose(pickUpPoint))
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
                BrushedText(
                    text = state.currentPickUpPointInfo?.address ?: "",
                    tint = IpbTheme.colors.textPrimary1.asBrush(),
                    style = IpbTheme.typography.title
                )
                Spacer(modifier = Modifier.size(2.dp))
                BrushedText(
                    text = state.currentPickUpPointInfo?.pickupPointCode ?: "",
                    tint = IpbTheme.colors.textTertiary1.asBrush(),
                    style = IpbTheme.typography.footnoteRegular
                )
                Spacer(modifier = Modifier.size(12.dp))
                BrushedText(
                    text = stringResource(R.string.work_hour),
                    tint = IpbTheme.colors.textPrimary1.asBrush(),
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
                    tint = IpbTheme.colors.textPrimary1.asBrush(),
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