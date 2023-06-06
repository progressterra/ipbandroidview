package com.progressterra.ipbandroidview.processes.media

import android.content.Intent
import android.provider.MediaStore
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.FileExplorer
import com.progressterra.ipbandroidview.shared.activity.MakePhotoContract
import com.progressterra.ipbandroidview.shared.activity.PhotoWasNotTakenException

interface MakePhotoUseCase {

    suspend operator fun invoke(id: String? = null): Result<MultisizedImage>

    class Base(
        private val makePhotoContract: MakePhotoContract.Client,
        private val fileExplorer: FileExplorer,
        private val createId: CreateId
    ) : MakePhotoUseCase {

        override suspend fun invoke(id: String?): Result<MultisizedImage> = runCatching {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val newPhotoId = id ?: createId()
            val uri = fileExplorer.uriForFile(fileExplorer.pictureFile(newPhotoId))
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            if (makePhotoContract.makePhoto(intent)) MultisizedImage(
                id = newPhotoId,
                local = true,
                toRemove = false,
                thumbnail = uri.toString(),
                fullSize = uri.toString()
            )
            else throw PhotoWasNotTakenException()
        }
    }
}