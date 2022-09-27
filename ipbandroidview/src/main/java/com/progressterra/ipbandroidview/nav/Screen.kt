package com.progressterra.ipbandroidview.nav

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Screen : Parcelable {

    @Parcelize
    object Splash : Screen()

    @Parcelize
    object SignIn : Screen()

    @Parcelize
    object SignUp : Screen()

    @Parcelize
    object City : Screen()

    @Parcelize
    object ConfirmationCode : Screen()

    @Parcelize
    object Main : Screen()
}
