package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.data.ProvideLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileOutputStream

interface DownloadFileUseCase {

    suspend fun downloadFile(url: String, pathToSave: String): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        val repo: IPBMediaDataRepository
    ) : AbstractUseCaseWithToken(scrmRepository, provideLocation), DownloadFileUseCase {

        override suspend fun downloadFile(url: String, pathToSave: String): Result<Unit> = handle {
            val body = repo.downloadFile(url).getOrThrow()
            val fos = withContext(Dispatchers.IO) {
                FileOutputStream(pathToSave)
            }
            fos.use { output ->
                val buffer = ByteArray(4 * 1024)
                var read: Int
                while (body.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
        }
    }
}