package com.progressterra.ipbandroidview.composable.component

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.progressterra.ipbandroidview.composable.AddressSuggestions
import com.progressterra.ipbandroidview.core.ComponentEvent
import com.progressterra.ipbandroidview.model.AddressUI
import com.progressterra.ipbandroidview.model.SuggestionUI
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

data class MapState(
    val isPermissionGranted: Boolean = false,
    val addressState: TextFieldState = TextFieldState(),
    val addressUI: AddressUI = AddressUI(),
    val suggestions: List<SuggestionUI> = emptyList()
)

sealed class MapEvent : ComponentEvent {

    data class AddressChanged(val address: String) : MapEvent()

    data class MapClicked(val latLng: LatLng) : MapEvent()

    data class LocationClicked(val location: Location) : MapEvent()

    data class SuggestionClicked(val suggestion: SuggestionUI) : MapEvent()
}

interface UseMap : UseTextField {

    fun handleEvent(id: String, event: MapEvent)

    override fun handleEvent(id: String, event: TextFieldEvent) {
        when (id) {
            "address" -> when (event) {
                is TextFieldEvent.TextChanged -> handleEvent(
                    id, MapEvent.AddressChanged(event.text)
                )
                is TextFieldEvent.Action -> Unit
            }
        }
    }
}

@Composable
fun Map(
    modifier: Modifier = Modifier,
    id: String,
    useComponent: UseMap,
    state: MapState
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .padding(12.dp)
    ) {
        val (map, address, suggestions) = createRefs()
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(55.751244, 37.618423), 10f)
        }
        TextField(
            modifier = Modifier.constrainAs(address) {
                width = Dimension.fillToConstraints
                top.linkTo(parent.top, 12.dp)
                start.linkTo(parent.start, 12.dp)
                end.linkTo(parent.end, 12.dp)
            }, id = "address", useComponent = useComponent, state = state.addressState
        )
        GoogleMap(
            modifier = Modifier
                .clip(IpbTheme.shapes.small)
                .constrainAs(map) {
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                    top.linkTo(address.bottom, 12.dp)
                    start.linkTo(parent.start, 12.dp)
                    end.linkTo(parent.end, 12.dp)
                    bottom.linkTo(parent.bottom, 12.dp)
                },
            cameraPositionState = cameraPositionState,
            onMapClick = { useComponent.handleEvent(id, MapEvent.MapClicked(it)) },
            onMyLocationClick = {
                useComponent.handleEvent(
                    id,
                    MapEvent.LocationClicked(it)
                )
            },
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
            onSuggestion = {
                useComponent.handleEvent(
                    id,
                    MapEvent.SuggestionClicked(it)
                )
            })
    }
}