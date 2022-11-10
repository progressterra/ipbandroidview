package com.progressterra.ipbandroidview.ui.city

import android.Manifest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.core.ManagePermission
import com.progressterra.ipbandroidview.domain.usecase.GuessLocationUseCase
import com.progressterra.ipbandroidview.domain.usecase.SuggestionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CityViewModel(
    private val managePermission: ManagePermission,
    private val guessLocationUseCase: GuessLocationUseCase,
    private val suggestionUseCase: SuggestionUseCase
) : ViewModel(), ContainerHost<CityState, CityEffect>, CityInteractor {

    override val container: Container<CityState, CityEffect> = container(CityState())
    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION


    init {
        viewModelScope.launch(Dispatchers.Default) {
            delay(3000)
            checkPermission()
        }
    }

    private fun checkPermission() = intent {
        val result = managePermission.checkPermission(locationPermission)
        reduce { state.copy(isPermissionGranted = result) }
    }


    override fun skip() = intent { postSideEffect(CityEffect.Skip) }

    override fun next() = intent { postSideEffect(CityEffect.Next) }

    override fun editAddress(address: String) = intent {
        reduce { state.copy(address = address) }
        suggestionUseCase.suggestions(address).map {
            reduce { state.copy(suggestions = it) }
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

    override fun back() = intent {
        postSideEffect(CityEffect.Back)
    }
}