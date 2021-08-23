package com.progressterra.ipbandroidview.ui.set_personal_info.models

import com.progressterra.ipbandroidapi.remoteData.ipbAmbassador.models.client_info.BankInfoResponse
import com.progressterra.ipbandroidapi.remoteData.ipbAmbassador.models.client_info.UpdateBankInfoResponse

data class UserBankData(
    val bankName: String,
    val bik: String,
    val correspondentAccount: String,
    val kppBank: String,
    val numberAccount: String,
    val tinOfBank: String,
    val tinOnClient: String
) {
    companion object {
        fun convertToUiModel(data: BankInfoResponse): UserBankData {
            return UserBankData(
                data.clientBankData.bankName ?: "",
                data.clientBankData.bik ?: "",
                data.clientBankData.correspondentAccount ?: "",
                data.clientBankData.kppBank ?: "",
                data.clientBankData.numberAccount ?: "",
                data.clientBankData.tinOfBank ?: "",
                data.clientBankData.tinOfClient ?: ""
            )
        }

        fun convertToUiModel(data: UpdateBankInfoResponse): UserBankData {
            return UserBankData(
                data.data?.bankAccountDetails?.bankName ?: "",
                data.data?.bankAccountDetails?.bik ?: "",
                data.data?.bankAccountDetails?.correspondentAccount ?: "",
                data.data?.bankAccountDetails?.kppBank ?: "",
                data.data?.bankAccountDetails?.numberAccount ?: "",
                data.data?.bankAccountDetails?.tinOfBank ?: "",
                data.data?.bankAccountDetails?.tinOfClient ?: ""
            )
        }
    }

}