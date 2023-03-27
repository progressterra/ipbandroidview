package com.progressterra.ipbandroidview.ui.city

import android.Manifest
import android.location.Location
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.composable.component.MapEvent
import com.progressterra.ipbandroidview.processes.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.usecase.location.GuessLocationUseCase
import com.progressterra.ipbandroidview.processes.usecase.suggestion.ChooseSuggestionUseCase
import com.progressterra.ipbandroidview.processes.usecase.suggestion.SuggestionUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.SaveUserAddressUseCase
import com.progressterra.ipbandroidview.model.SuggestionUI
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class CityViewModel(
    private val guessLocationUseCase: GuessLocationUseCase,
    private val suggestionUseCase: SuggestionUseCase,
    private val saveUserAddressUseCase: SaveUserAddressUseCase,
    private val chooseSuggestionUseCase: ChooseSuggestionUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase
) : ViewModel(), ContainerHost<CityState, CityEffect>, CityInteractor {

    override val container: Container<CityState, CityEffect> = container(CityState())

    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    init {
        initialCheckPermission()
    }

    private fun initialCheckPermission() = intent {
        delay(3000)
        checkPermission()
    }

    override fun onSkip() = intent { postSideEffect(CityEffect.Next) }

    override fun onNext() = intent {
        saveUserAddressUseCase(state.mapState.addressUI).onSuccess {
            postSideEffect(CityEffect.Next)
        }
    }

    private fun checkPermission() = intent {
        val result = checkPermissionUseCase(locationPermission).isSuccess
        val newMapState = state.mapState.copy(isPermissionGranted = result)
        reduce { state.copy(mapState = newMapState) }
    }

    private fun editAddress(address: String) = blockingIntent {
//        val newMapState = state.mapState.copy(address = address)
//        reduce { state.copy(mapState = newMapState) }
//        updateSuggestions(address)
    }

    private fun updateSuggestions(address: String) = intent {
        suggestionUseCase(address).onSuccess {
            val newMapState = state.mapState.copy(suggestions = it)
            reduce { state.copy(mapState = newMapState) }
        }
    }

    override fun handle(event: MapEvent) = when (event) {
        is MapEvent.AddressChanged -> editAddress(event.address)
        is MapEvent.LocationClicked -> clickLocation(event.location)
        is MapEvent.MapClicked -> clickMap(event.latLng)
        is MapEvent.SuggestionClicked -> clickSuggestion(event.suggestion)
    }

    private fun clickMap(latLng: LatLng) = intent {
//        guessLocationUseCase(latLng).onSuccess { addressUI ->
//            val newMapState = state.mapState.copy(
//                addressUI = addressUI, address = addressUI.printAddress()
//            )
//            reduce { state.copy(mapState = newMapState) }
//            suggestionUseCase(addressUI.printAddress()).onSuccess {
//                val newMapState2 = state.mapState.copy(suggestions = it)
//                reduce { state.copy(mapState = newMapState2) }
//            }
//        }
    }

    private fun clickLocation(location: Location) = intent {
        clickMap(LatLng(location.latitude, location.longitude))
    }

    private fun clickSuggestion(suggestion: SuggestionUI) = intent {
//        chooseSuggestionUseCase(suggestion).onSuccess {
//            reduce {
//                val newMapState = state.mapState.copy(
//                    addressUI = it, address = suggestion.previewOfSuggestion
//                )
//                state.copy(
//                    mapState = newMapState, isDataValid = true
//                )
//            }
//        }
    }

    override fun onBack() = intent {
        postSideEffect(CityEffect.Back)
    }
}