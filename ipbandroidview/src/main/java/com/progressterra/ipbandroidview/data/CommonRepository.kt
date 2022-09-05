package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.iecommerscoreapi.IECommersCore
import com.progressterra.ipbandroidapi.api.iecommerscoreapi.models.CatalogItem
import com.progressterra.ipbandroidapi.api.ipbpromocodeapi.IPBPromoCode
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.data.model.BonusesInfo
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.*

internal class CommonRepository(
    private val promoCodeApi: IPBPromoCode.PromoCodeUse,
    private val iBonusRepository: IBonusRepository,
    private val ieCoreCatalog: IECommersCore.Catalog,
    sCRMRepository: SCRMRepository
) : BaseRepository(sCRMRepository), IRepository.PromoCode, IRepository.Bonuses,
    IRepository.Catalog {


    override suspend fun setPromoCode(accessToken: String, promoCode: String): SResult<*> =
        safeApiCall {
            val response = promoCodeApi.setPromoCode(accessToken, promoCode)

            if (response.isSuccess()) {
                completedResult()
            } else {
                response.result?.message.toFailedResult()
            }
        }

    override suspend fun getBonusesInfo(): SResult<BonusesInfo> = safeApiCall {
        val token = getAccessToken().dataOrFailed { return@safeApiCall it.toFailedResult() }
        val response =
            iBonusRepository.getGeneralInfo(token).getOrNull() ?: return@safeApiCall emptyFailed()
        BonusesInfo(response).toSuccessResult()
    }

    override suspend fun getCatalog(): SResult<List<CatalogItem>> = safeApiCall {
        val token = getAccessToken().dataOrFailed { return@safeApiCall it.toFailedResult() }
        val response = ieCoreCatalog.getCatalog(token)

        val catalogs = response.data?.get(0)?.childItems

        catalogs?.toSuccessResult() ?: response.responseToFailedResult()
    }
}