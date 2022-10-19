package com.progressterra.ipbandroidview.ui.city

import android.Manifest
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.core.permission.ManagePermission
import com.progressterra.ipbandroidview.domain.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.domain.GuessLocationUseCase
import com.progressterra.ipbandroidview.domain.SuggestionUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CityViewModel(
    private val managePermission: ManagePermission,
    private val currentLocationSuggestionsUseCase: CurrentLocationSuggestionsUseCase,
    private val guessLocationUseCase: GuessLocationUseCase,
    private val suggestionUseCase: SuggestionUseCase
) : ViewModel(), ContainerHost<CityState, CityEffect>, CityInteractor {

    override val container: Container<CityState, CityEffect> = container(CityState())

    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    override fun onSkip() = intent { postSideEffect(CityEffect.Skip) }

    override fun onNext() = intent { postSideEffect(CityEffect.Next) }

    override fun onAddress(address: String) = intent {
        reduce { state.copy(address = address) }
        suggestionUseCase.suggestions(address).map {
            reduce { state.copy(suggestions = it) }
        }
    }

    override fun onMyLocation() {
        if (!managePermission.checkPermission(locationPermission))
            managePermission.requirePermission(locationPermission)
        else {
            intent {
                currentLocationSuggestionsUseCase.currentLocation().map {
                    reduce { state.copy(suggestions = it) }
                }
            }
        }
    }

    override fun onMapClick(latLng: LatLng) = intent {
        guessLocationUseCase.guessLocation(latLng).map {
            reduce { state.copy(address = it) }
        }
    }

    override fun onSuggestion(suggestion: Suggestion) = intent {
        reduce { state.copy(address = "${suggestion.city}, ${suggestion.address}") }
    }

    override fun onAddressFocus(focused: Boolean) = intent {
        reduce { state.copy(isAddressInFocus = focused) }
    }
}