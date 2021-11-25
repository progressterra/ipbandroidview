package com.progressterra.ipbandroidview.data


import com.progressterra.ipbandroidapi.api.iECommersCoreApi.IECommersCore
import com.progressterra.ipbandroidapi.api.iECommersCoreApi.models.CatalogItem
import com.progressterra.ipbandroidapi.api.ipbPromoCodeApi.IPBPromoCode
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.models.BonusesInfo
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.*

internal class CommonRepository : BaseRepository(), IRepository.PromoCode, IRepository.Bonuses,
    IRepository.Catalog {
    private val promoCodeApi = IPBPromoCode.PromoCodeUse()
    private val bonusesApi = BonusesApi.getInstance()
    private val ieCoreCatalog = IECommersCore.Catalog()

    override suspend fun setPromoCode(accessToken: String, promoCode: String): SResult<*> {
        val response = promoCodeApi.setPromoCode(accessToken, promoCode)
        return if (response.isSuccess()) {
            completedResult()
        } else {
            response.result?.message.toFailedResult()
        }
    }

    override suspend fun getBonusesInfo(): SResult<BonusesInfo> = safeApiCall {
        val token = getAccessToken().data ?: return@safeApiCall emptyFailed()
        val response = bonusesApi.getBonusesInfo(token)

        response.responseBody?.toSuccessResult()
            ?: response.errorString.toFailedResult()
    }

    override suspend fun getCatalog(): SResult<List<CatalogItem>> = safeApiCall {
        val token = getAccessToken().data ?: return@safeApiCall emptyFailed()
        val response = ieCoreCatalog.getCatalog(token)

        val catalogs = response.data?.get(0)?.childItems

        catalogs?.toSuccessResult() ?: return@safeApiCall response.result?.message.toFailedResult()
    }
}