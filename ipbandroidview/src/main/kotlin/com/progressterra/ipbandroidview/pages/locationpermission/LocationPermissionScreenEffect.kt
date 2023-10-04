package com.progressterra.ipbandroidview.pages.locationpermission

sealed class LocationPermissionScreenEffect {

    data object OnBack : LocationPermissionScreenEffect()

    data object OnNext : LocationPermissionScreenEffect()
}
