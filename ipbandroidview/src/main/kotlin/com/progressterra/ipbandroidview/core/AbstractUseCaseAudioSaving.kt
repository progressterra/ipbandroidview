package com.progressterra.ipbandroidview.core

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.data.ProvideLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

abstract class AbstractUseCaseAudioSaving(
    scrmRepository: SCRMRepository,
    provideLocation: ProvideLocation,
    private val fileExplorer: FileExplorer
) : AbstractUseCaseWithToken(scrmRepository, provideLocation) {

    protected suspend fun saveAudio(inputStream: InputStream, id: String) {
        withContext(Dispatchers.IO) { fileExplorer.inputStreamToVoices(inputStream, id) }
    }
}