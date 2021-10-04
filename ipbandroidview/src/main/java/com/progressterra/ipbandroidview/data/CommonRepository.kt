package com.progressterra.ipbandroidview.data


import com.progressterra.ipbandroidapi.api.ipbPromoCodeApi.IPBPromoCode
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.completedResult
import com.progressterra.ipbandroidview.utils.extensions.isSuccess
import com.progressterra.ipbandroidview.utils.extensions.toFailedResult

internal class CommonRepository : BaseRepository(), IRepository.PromoCode {
    private val promoCodeApi = IPBPromoCode.PromoCodeUse()

    override suspend fun setPromoCode(accessToken: String, promoCode: String): SResult<*> {
        val response = promoCodeApi.setPromoCode(accessToken, promoCode)
        return if (response.isSuccess()) {
            completedResult()
        } else {
            response.result?.message.toFailedResult()
        }
    }
}