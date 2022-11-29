package com.progressterra.ipbandroidview.model

import android.graphics.Bitmap

sealed class Notification {

    data class BonusExpiring(
        val amount: String,
        val date: String
    ) : Notification()

    data class Main(
        val qr: Bitmap,
        val bonusesAvailable: String
    ) : Notification()
}
