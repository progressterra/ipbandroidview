package com.progressterra.ipbandroidview.pages.bankcards

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidview.features.bankcard.BankCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface FetchBankCardsUseCase : PagingUseCase<Nothing, BankCardState> {

    class Base(
        private val documentsRepository: DocumentsRepository,
        private val obtainAccessToken: ObtainAccessToken,
        private val gson: Gson,
        private val createId: CreateId,
        private val fetchMainCardIdUseCase: FetchMainCardIdUseCase
    ) : PagingUseCase.Abstract<Nothing, BankCardState>(), FetchBankCardsUseCase {

        override fun createSource() =
            BankCardsSource(
                documentsRepository,
                obtainAccessToken,
                gson,
                createId,
                fetchMainCardIdUseCase
            )
    }
}

