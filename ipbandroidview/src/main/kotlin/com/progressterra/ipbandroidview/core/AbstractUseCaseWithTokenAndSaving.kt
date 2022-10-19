package com.progressterra.ipbandroidview.core

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.data.ProvideLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

abstract class AbstractUseCaseWithTokenAndSaving(
    scrmRepository: SCRMRepository,
    provideLocation: ProvideLocation,
    private val fileExplorer: FileExplorer
) : AbstractUseCaseWithToken(scrmRepository, provideLocation) {

    protected suspend fun save(inputStream: InputStream, id: String) {
        if (!fileExplorer.exists(id))
            withContext(Dispatchers.IO) { fileExplorer.saveFile(inputStream, id) }
    }
}