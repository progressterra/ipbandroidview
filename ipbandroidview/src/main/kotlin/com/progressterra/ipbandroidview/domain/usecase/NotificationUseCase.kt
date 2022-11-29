package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.api.ibonus.IBonusRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidapi.ext.parseToDate
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.Notification

interface NotificationUseCase {

    suspend operator fun invoke(): Result<List<Notification>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        manageResources: ManageResources,
        private val iBonusRepository: IBonusRepository,
        private val createQrUseCase: CreateQrUseCase
    ) : NotificationUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(): Result<List<Notification>> = withToken { token ->
            buildList {
                val bonusesResponse = iBonusRepository.getGeneralInfo(token).getOrThrow()
                bonusesResponse?.forBurningQuantity?.let {
                    if (it > 0)
                        add(
                            Notification.BonusExpiring(
                                amount = it.toString(),
                                date = bonusesResponse.dateBurning?.parseToDate()
                                    ?.format("dd.MM.yyyy") ?: noData
                            )
                        )
                }
                add(
                    Notification.Main(
                        qr = createQrUseCase(token),
                        bonusesAvailable = bonusesResponse?.currentQuantity?.toString() ?: noData
                    )
                )
            }
        }
    }
}