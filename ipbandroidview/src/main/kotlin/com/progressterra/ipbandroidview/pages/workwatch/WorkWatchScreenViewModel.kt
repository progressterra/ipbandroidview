package com.progressterra.ipbandroidview.pages.workwatch

import android.Manifest
import android.os.Build
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.integration.WorkWatchWorker
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import java.util.concurrent.TimeUnit

class WorkWatchScreenViewModel(
    private val workManager: WorkManager,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase
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
                val interval = IpbAndroidViewSettings.WORK_WATCH_PERIOD * 1000L
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