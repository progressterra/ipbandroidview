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
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase

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
            val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
            val provideLocationUseCase = ProvideLocationUseCase.Base(fusedLocationProviderClient)
            val obtainAccessTokenUseCase = ObtainAccessToken.Base(authService, provideLocationUseCase)
            val token = obtainAccessTokenUseCase().getOrThrow()
            val location = provideLocationUseCase().getOrThrow()
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