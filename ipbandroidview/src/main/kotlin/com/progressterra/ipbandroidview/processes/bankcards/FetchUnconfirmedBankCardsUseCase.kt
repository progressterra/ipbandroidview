package com.progressterra.ipbandroidview.processes.bankcards

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.processes.utils.CreateId
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface FetchUnconfirmedBankCardsUseCase : PagingUseCase<Nothing, BankCardState> {

    class Base(
        private val documentsRepository: DocumentsRepository,
        private val obtainAccessToken: ObtainAccessToken,
        private val gson: Gson,
        private val createId: CreateId,
        private val fetchMainCardIdUseCase: FetchMainCardIdUseCase
    ) : PagingUseCase.Abstract<Nothing, BankCardState>(), FetchUnconfirmedBankCardsUseCase {

        override fun createSource() =
            UnconfirmedBankCardsSource(
                documentsRepository,
                obtainAccessToken,
                gson,
                createId,
                fetchMainCardIdUseCase
            )
    }
}

