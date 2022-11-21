package com.progressterra.ipbandroidview.ui.city

import android.Manifest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.core.ManagePermissionContract
import com.progressterra.ipbandroidview.domain.usecase.GuessLocationUseCase
import com.progressterra.ipbandroidview.domain.usecase.SaveUserAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.SuggestionUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CityViewModel(
    private val managePermissionContract: ManagePermissionContract.Client,
    private val guessLocationUseCase: GuessLocationUseCase,
    private val suggestionUseCase: SuggestionUseCase,
    private val saveUserAddressUseCase: SaveUserAddressUseCase
) : ViewModel(), ContainerHost<CityState, CityEffect> {

    override val container: Container<CityState, CityEffect> = container(CityState())

    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    init {
        viewModelScope.launch {
            delay(3000)
            checkPermission()
        }
    }

    private fun checkPermission() = intent {
        val result = managePermissionContract.checkPermission(locationPermission)
        reduce { state.copy(isPermissionGranted = result) }
    }

    fun skip() = intent { postSideEffect(CityEffect.Next) }

    fun next() = intent {
        saveUserAddressUseCase.saveAddress(address = state.address)
        postSideEffect(CityEffect.Next)
    }

    fun editAddress(address: String) = intent {
        reduce { state.copy(address = address) }
        suggestionUseCase.suggestions(address).map {
            reduce { state.copy(suggestions = it) }
        }
    }

    fun onMapClick(latLng: LatLng) = intent {
        guessLocationUseCase.guessLocation(latLng).map {
            reduce { state.copy(address = it) }
        }
    }

    fun onSuggestion(suggestion: Suggestion) = intent {
        reduce { state.copy(address = "${suggestion.city}, ${suggestion.address}") }
    }

    fun back() = intent {
        postSideEffect(CityEffect.Back)
    }
}