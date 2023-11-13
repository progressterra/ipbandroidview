package com.progressterra.ipbandroidview.integration

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.progressterra.ipbandroidapi.api.workwatch.WorkWatchService
import com.progressterra.ipbandroidapi.api.workwatch.models.RGTrackingEntity
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase

class WorkWatchWorker(
    context: Context,
    parameters: WorkerParameters,
    private val obtainAccessToken: ObtainAccessToken,
    private val locationUseCase: ProvideLocationUseCase,
    private val workWatchService: WorkWatchService
) : CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        val result = runCatching {
            val token = obtainAccessToken().getOrThrow()
            val location = locationUseCase().getOrThrow()
            workWatchService.clientAreaTracking(
                token = token,
                body = RGTrackingEntity(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            )
        }
        return if (result.isSuccess) {
            Result.success()
        } else {
            Result.retry()
        }
    }
}