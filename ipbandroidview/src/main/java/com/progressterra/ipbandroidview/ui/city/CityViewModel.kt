package com.progressterra.ipbandroidview.ui.city

import android.Manifest
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.base.ManagePermission
import com.progressterra.ipbandroidview.domain.CurrentLocationUseCase
import com.progressterra.ipbandroidview.domain.GuessLocationUseCase
import com.progressterra.ipbandroidview.domain.SuggestionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CityViewModel(
    private val managePermission: ManagePermission,
    private val currentLocationUseCase: CurrentLocationUseCase,
    private val guessLocationUseCase: GuessLocationUseCase,
    private val suggestionUseCase: SuggestionUseCase
) : ViewModel(), ContainerHost<CityState, CityEffect>, CityInteractor {

    override val container: Container<CityState, CityEffect> = container(CityState(), buildSettings = {
    })

    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    override fun onBack() = intent {
        postSideEffect(CityEffect.Back)
    }

    override fun onSkip() = intent {
        postSideEffect(CityEffect.Skip)
    }

    override fun onNext() = intent {
        postSideEffect(CityEffect.Next)
    }

    override fun onAddress(address: String) = intent {
        reduce { state.copy(address = address) }
        suggestionUseCase.suggestions(address).map {
            reduce { state.copy(suggestions = it) }
        }
    }

    override fun onMyLocation() = intent {
        if (!managePermission.checkPermission(locationPermission)) managePermission.requirePermission(
            locationPermission
        )
        else {
            currentLocationUseCase.currentLocation().map {
                reduce { state.copy(suggestions = it) }
            }
        }
    }

    override fun onMapClick(latLng: LatLng) = intent {
        guessLocationUseCase.guessLocation(latLng).map {
            reduce { state.copy(address = it) }
        }
    }

    override fun onSuggestion(suggestion: Suggestion) = intent {
        reduce {
            state.copy(address = "${suggestion.city}, ${suggestion.address}")
        }
    }

    override fun onAddressFocus(focused: Boolean) = intent {
        reduce {
            state.copy(isAddressInFocus = focused)
        }
    }
}