package com.progressterra.ipbandroidview.ui.city

import android.Manifest
import android.location.Location
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.domain.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.location.GuessLocationUseCase
import com.progressterra.ipbandroidview.domain.usecase.suggestion.ChooseSuggestionUseCase
import com.progressterra.ipbandroidview.domain.usecase.suggestion.SuggestionUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.SaveUserAddressUseCase
import com.progressterra.ipbandroidview.model.address.SuggestionUI
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

    private fun checkPermission() = intent {
        val result = checkPermissionUseCase(locationPermission).isSuccess
        reduce { state.copy(isPermissionGranted = result) }
    }

    override fun onSkip() = intent { postSideEffect(CityEffect.Next) }

    override fun onNext() = intent {
        saveUserAddressUseCase(address = state.addressUI).onSuccess {
            postSideEffect(CityEffect.Next)
        }
    }

    override fun editAddress(address: String) = blockingIntent {
        reduce { state.copy(address = address) }
        updateSuggestions(address)
    }

    private fun updateSuggestions(address: String) = intent {
        suggestionUseCase(address).onSuccess { reduce { state.copy(suggestions = it) } }
    }

    override fun mapClick(latLng: LatLng) = intent {
        guessLocationUseCase(latLng).onSuccess {
            reduce { state.copy(addressUI = it, address = it.printAddress()) }
            suggestionUseCase(it.printAddress()).onSuccess {
                reduce { state.copy(suggestions = it) }
            }
        }
    }

    override fun myLocationClick(location: Location) = intent {
        mapClick(LatLng(location.latitude, location.longitude))
    }

    override fun onSuggestion(suggestion: SuggestionUI) = intent {
        chooseSuggestionUseCase(suggestion).onSuccess {
            reduce {
                state.copy(
                    addressUI = it, address = suggestion.previewOfSuggestion, isDataValid = true
                )
            }
        }
    }

    override fun onBack() = intent {
        postSideEffect(CityEffect.Back)
    }
}