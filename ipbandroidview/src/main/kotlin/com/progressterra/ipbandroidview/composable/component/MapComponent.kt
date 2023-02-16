package com.progressterra.ipbandroidview.composable.component

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.progressterra.ipbandroidapi.api.suggestion.model.SuggestionExtendedInfo
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.AddressSuggestions
import com.progressterra.ipbandroidview.composable.ThemedTextField
import com.progressterra.ipbandroidview.core.ComponentEvent
import com.progressterra.ipbandroidview.model.AddressUI
import com.progressterra.ipbandroidview.model.SuggestionUI
import com.progressterra.ipbandroidview.theme.AppTheme

data class MapComponentState(
    val isPermissionGranted: Boolean = false,
    val address: String = "",
    val addressUI: AddressUI = AddressUI(),
    val suggestions: List<SuggestionUI> = emptyList()
)

sealed class MapComponentEvent : ComponentEvent {

    data class AddressChanged(val address: String) : MapComponentEvent()

    data class MapClicked(val latLng: LatLng) : MapComponentEvent()

    data class LocationClicked(val location: Location) : MapComponentEvent()

    data class SuggestionClicked(val suggestion: SuggestionUI) : MapComponentEvent()
}

/**
 * @param modifier - modifier for the component
 * @param state - state of the component
 * @param onEvent - callback for events
 */
@Composable
fun MapComponent(
    modifier: Modifier = Modifier, state: MapComponentState, onEvent: (MapComponentEvent) -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .padding(AppTheme.dimensions.medium)
    ) {
        val (map, address, suggestions) = createRefs()
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(55.751244, 37.618423), 10f)
        }
        ThemedTextField(modifier = Modifier.constrainAs(address) {
            width = Dimension.fillToConstraints
            top.linkTo(parent.top, 12.dp)
            start.linkTo(parent.start, 12.dp)
            end.linkTo(parent.end, 12.dp)
        },
            text = state.address,
            hint = stringResource(id = R.string.address),
            onChange = { onEvent(MapComponentEvent.AddressChanged(it)) })
        GoogleMap(
            modifier = Modifier
                .clip(AppTheme.shapes.small)
                .constrainAs(map) {
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                    top.linkTo(address.bottom, 12.dp)
                    start.linkTo(parent.start, 12.dp)
                    end.linkTo(parent.end, 12.dp)
                    bottom.linkTo(parent.bottom, 12.dp)
                },
            cameraPositionState = cameraPositionState,
            onMapClick = { onEvent(MapComponentEvent.MapClicked(it)) },
            onMyLocationClick = { onEvent(MapComponentEvent.LocationClicked(it)) },
            properties = MapProperties(isMyLocationEnabled = state.isPermissionGranted)
        )
        AddressSuggestions(modifier = Modifier.constrainAs(suggestions) {
            width = Dimension.fillToConstraints
            top.linkTo(address.bottom, 4.dp)
            start.linkTo(address.start)
            end.linkTo(address.end)
        },
            suggestions = state.suggestions,
            isVisible = state.suggestions.isNotEmpty(),
            onSuggestion = { onEvent(MapComponentEvent.SuggestionClicked(it)) })
    }
}

@Composable
@Preview
private fun MapComponentPreview() {
    AppTheme {
        MapComponent(
            state = MapComponentState(
                isPermissionGranted = true, address = "address", suggestions = listOf(
                    SuggestionUI(
                        suggestionExtendedInfo = SuggestionExtendedInfo(),
                        previewOfSuggestion = "previewOfSuggestion"
                    )
                )
            ), onEvent = { }
        )
    }
}