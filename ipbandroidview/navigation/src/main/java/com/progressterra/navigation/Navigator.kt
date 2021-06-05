package com.progressterra.navigation

import androidx.navigation.NavController

public class Navigator {
    lateinit var navController: NavController

    fun navigateToFlow(navigationFlow: NavigationFlow) = when (navigationFlow) {
        NavigationFlow.LoginFlow -> navController.navigate(MainNavGraphDirections.actionGlobalLoginFlow())
    }
}