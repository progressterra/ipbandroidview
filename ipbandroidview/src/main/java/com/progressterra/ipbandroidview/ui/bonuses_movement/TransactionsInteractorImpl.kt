package com.progressterra.ipbandroidview.ui.bonuses_movement

//import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
//import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
//import com.progressterra.ipbandroidview.ui.bonuses_movement.TransactionsInteractor
//
//
//class TransactionsInteractorImpl(
//    var ipbRepository: BonusesApi
//) : TransactionsInteractor {
//    override suspend fun getGeneralBonusesInfo(accessToken: String): Result<BonusesInfo> {
//        val response = ipbRepository.getBonusesInfo(accessToken)
//        return if (response.globalResponseStatus == GlobalResponseStatus.SUCCESS) {
//            Result.success(response.responseBody)
//        } else {
//            Result.error(response.errorString)
//        }
//    }
//
//    override suspend fun getAccessToken(): Result<String> {
//        val response = ipbRepository.getAccessToken()
//        return if (response.globalResponseStatus == GlobalResponseStatus.SUCCESS && response.responseBody?.accessToken != null) {
//            Result.success(response.responseBody?.accessToken)
//            Result
//        } else {
//            Result.error(response.errorString)
//        }
//    }
//
//    override suspend fun getTransactionsList(accessToken: String): Result<List<Transaction>> {
//        val response = repository.getTransactionsList(accessToken)
//        return if (response.body()?.result?.status == 0) {
//            Result.success(TransactionListResponse.convertToTransactionList(response.body()))
//        } else {
//            Result.error(response.body()?.message)
//        }
//    }
//}