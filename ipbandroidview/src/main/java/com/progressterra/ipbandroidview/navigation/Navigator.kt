package com.progressterra.ipbandroidview.navigation

import androidx.navigation.NavController
import com.progressterra.ipbandroidview.MainNavGraphDirections

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        NavigationFlow.LoginFlow -> navController.navigate(MainNavGraphDirections.actionGlobalLoginFlow())
    }
}