package com.progressterra.ipbandroidview.processes.location

import android.location.Location
import com.progressterra.ipbandroidview.shared.activity.SubscribeLocationContract
import kotlinx.coroutines.flow.Flow

interface SubscribeOnLocationUseCase {

    suspend fun start()

    suspend fun stop()

    val resultFlow: Flow<Result<Location>>

    class Base(
        private val subscribeLocationContract: SubscribeLocationContract.Client
    ) : SubscribeOnLocationUseCase {

        override suspend fun start() {
            subscribeLocationContract.start()
        }

        override suspend fun stop() {
            subscribeLocationContract.stop()
        }

        override val resultFlow = subscribeLocationContract.resultFlow
    }
}