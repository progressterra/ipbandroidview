package com.progressterra.ipbandroidview.pages.workwatch

import android.Manifest
import android.os.Build
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.progressterra.ipbandroidview.entities.Fence
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.integration.WorkWatchWorker
import com.progressterra.ipbandroidview.processes.location.SetupGeofencesUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import java.util.concurrent.TimeUnit

class WorkWatchScreenViewModel(
    private val workManager: WorkManager,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val setupGeofencesUseCase: SetupGeofencesUseCase
) : AbstractNonInputViewModel<WorkWatchScreenState, WorkWatchScreenEffect>(), UseWorkWatchScreen {
    override fun createInitialState() = WorkWatchScreenState()

    override fun handle(event: ButtonEvent) {
        onBackground {
            if (event.id == "ask") {
                var isSuccess = true
                checkPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION).onSuccess {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        checkPermissionUseCase(Manifest.permission.ACCESS_BACKGROUND_LOCATION).onFailure {
                            askPermissionUseCase(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                            isSuccess = false
                        }
                    }
                }.onFailure {
                    askPermissionUseCase(Manifest.permission.ACCESS_FINE_LOCATION)
                    isSuccess = false
                }
                emitState { it.copy(enable = it.enable.copy(enabled = isSuccess)) }
            } else if (event.id == "enable") {
                setupGeofencesUseCase(
                    listOf(
                        Fence(
                            id = "Казанский собор",
                            latitude = 59.934379,
                            longitude = 30.323425,
                            radius = 100f
                        ),
                        Fence(
                            id = "Спас на крови",
                            latitude = 59.939965,
                            longitude = 30.329569,
                            radius = 100f
                        ),
                        Fence(
                            id = "Дворцовая площадь",
                            latitude = 59.93963,
                            longitude = 30.315033,
                            radius = 100f
                        )
                    )
                )
                val interval = IpbAndroidViewSettings.WORK_WATCH_PERIOD * 60 * 1000L
                workManager.cancelAllWork()
                val request = PeriodicWorkRequest.Builder(
                    WorkWatchWorker::class.java,
                    interval,
                    TimeUnit.MILLISECONDS
                ).build()
                workManager.enqueue(request)
            }
        }
    }
}