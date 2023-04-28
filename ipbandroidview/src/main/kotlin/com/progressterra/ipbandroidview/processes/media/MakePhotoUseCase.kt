package com.progressterra.ipbandroidview.processes.media

import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.FileExplorer
import com.progressterra.ipbandroidview.shared.activity.MakePhotoContract
import com.progressterra.ipbandroidview.shared.activity.PhotoWasNotTakenException

interface MakePhotoUseCase {

    suspend operator fun invoke(): Result<MultisizedImage>

    class Base(
        private val makePhotoContract: MakePhotoContract.Client,
        private val fileExplorer: FileExplorer,
        private val createId: CreateId
    ) : MakePhotoUseCase {

        override suspend fun invoke(): Result<MultisizedImage> = runCatching {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            Log.d("CAMERA", "$intent")
            val newPhotoId = createId()
            Log.d("CAMERA", "$newPhotoId")
            val uri = fileExplorer.uriForFile(fileExplorer.pictureFile(newPhotoId))
            Log.d("CAMERA", "$uri")
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