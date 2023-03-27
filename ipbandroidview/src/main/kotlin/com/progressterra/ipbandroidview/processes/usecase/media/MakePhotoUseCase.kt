package com.progressterra.ipbandroidview.processes.usecase.media

import android.Manifest
import android.content.Intent
import android.provider.MediaStore
import com.progressterra.ipbandroidview.core.CreateId
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.MakePhotoContract
import com.progressterra.ipbandroidview.processes.exception.PhotoWasNotTakenException
import com.progressterra.ipbandroidview.processes.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.ext.throwOnFailure
import com.progressterra.ipbandroidview.model.MultisizedImage

interface MakePhotoUseCase {

    suspend operator fun invoke(): Result<MultisizedImage>

    class Base(
        private val makePhotoContract: MakePhotoContract.Client,
        private val fileExplorer: FileExplorer,
        private val createId: CreateId,
        private val checkPermissionUseCase: CheckPermissionUseCase
    ) : MakePhotoUseCase {

        override suspend fun invoke(): Result<MultisizedImage> = runCatching {
            checkPermissionUseCase(Manifest.permission.CAMERA).throwOnFailure()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val newPhotoId = createId()
            val uri = fileExplorer.uriForFile(fileExplorer.pictureFile(newPhotoId))
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            if (makePhotoContract.makePhoto(intent))
                MultisizedImage(
                    id = newPhotoId,
                    local = true,
                    toRemove = false,
                    thumbnail = uri.toString(),
                    fullSize = uri.toString()
                )
            else
                throw PhotoWasNotTakenException()
        }
    }
}