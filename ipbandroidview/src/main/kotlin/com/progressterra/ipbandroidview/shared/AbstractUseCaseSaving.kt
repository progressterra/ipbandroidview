package com.progressterra.ipbandroidview.shared

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

abstract class AbstractUseCaseSaving(
    scrmRepository: SCRMRepository,
    provideLocation: ProvideLocation,
    private val fileExplorer: FileExplorer
) : AbstractUseCase(scrmRepository, provideLocation) {

    protected suspend fun saveAudio(inputStream: InputStream, id: String) {
        withContext(Dispatchers.IO) { fileExplorer.inputStreamToVoices(inputStream, id) }
    }
}