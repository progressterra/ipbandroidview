package com.progressterra.ipbandroidview.integration

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.android.gms.location.LocationServices
import com.progressterra.ipbandroidapi.IpbAndroidApiSettings
import com.progressterra.ipbandroidapi.api.auth.AuthService
import com.progressterra.ipbandroidapi.api.workwatch.WorkWatchService
import com.progressterra.ipbandroidapi.api.workwatch.models.RGTrackingEntity
import com.progressterra.ipbandroidapi.core.NetworkService
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase
import com.progressterra.ipbandroidview.shared.log

class WorkWatchWorker(
    private val context: Context,
    parameters: WorkerParameters
) : CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        val result = runCatching {
            val networkService = NetworkService.Base()
            val authService = networkService.createService(
                AuthService::class.java, IpbAndroidApiSettings.AUTH_URL
            )
            val workWatchService = networkService.createService(
                WorkWatchService::class.java, IpbAndroidApiSettings.WORK_WATCH_URL
            )
            val fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)
            val provideLocationUseCase = ProvideLocationUseCase.Base(fusedLocationProviderClient)
            val obtainAccessTokenUseCase =
                ObtainAccessToken.Base(authService, provideLocationUseCase)
            val token = obtainAccessTokenUseCase().getOrThrow().also { log("WorkWatch", "token: $it") }
            val location = provideLocationUseCase().getOrThrow().also { log("WorkWatch", "location: $it") }
            workWatchService.clientAreaTracking(
                token = token,
                body = RGTrackingEntity(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            ).also { log("WorkWatch", "request: $it") }
        }
        return if (result.isSuccess) {
            Result.success()
        } else {
            log("WorkWatch", "error: ${result.exceptionOrNull()}")
            Result.retry()
        }
    }
}