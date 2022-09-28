package com.progressterra.ipbandroidview.city

import android.Manifest
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.progressterra.ipbandroidview.base.ManagePermission
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class CityViewModel(
    private val managePermission: ManagePermission
) : ViewModel(), ContainerHost<CityState, CityEffect>, CityInteractor {

    override val container: Container<CityState, CityEffect> = container(CityState())

    private val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION

    override fun onBack() = intent{
        postSideEffect(CityEffect.Back)
    }

    override fun onSkip() = intent{
        postSideEffect(CityEffect.Skip)
    }

    override fun onNext() = intent{
        postSideEffect(CityEffect.Next)
    }

    override fun onAddress(address: String) {
        TODO("Not yet implemented")
    }

    override fun onMyLocation() {
        if (!managePermission.checkPermission(locationPermission))
            managePermission.requirePermission(locationPermission)

    }

    override fun onMapClick(latLng: LatLng) {
        TODO("Not yet implemented")
    }
}