package com.progressterra.ipbandroidview.navigation

import androidx.navigation.NavController

class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) {}
//    = when (navigationFlow) {
//        NavigationFlow.LoginFlow -> navController.navigate(MainNavGraphDirections.actionGlobalLoginFlow())
//    }
}