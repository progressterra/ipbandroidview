package com.progressterra.ipbandroidview.pages.goodsdetails

import com.progressterra.ipbandroidview.shared.mvi.OnBack
import com.progressterra.ipbandroidview.shared.mvi.OpenGoodsDetails
import com.progressterra.ipbandroidview.shared.mvi.OpenPhoto

interface GoodsDetailsScreenNavigation : OnBack, OpenPhoto, OpenGoodsDetails {

    fun onDelivery()
}