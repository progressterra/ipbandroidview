package com.progressterra.android.ipbandroidview.bonuses_details.tabs

import com.progressterra.ipbandroidapi.interfaces.client.bonuses.Purchase

interface OnPurchaseClickListener {
    fun openDetailPurchase(purchase: Purchase)
}