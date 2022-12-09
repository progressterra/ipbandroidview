package com.progressterra.ipbandroidview.model.store

import android.graphics.Bitmap

sealed class StoreNotification {

    data class BonusExpiring(
        val amount: String,
        val date: String
    ) : StoreNotification()

    data class Main(
        val qr: Bitmap,
        val bonusesAvailable: String
    ) : StoreNotification()
}
