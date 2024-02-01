package com.progressterra.ipbandroidview.processes.media

import android.content.Intent
import android.provider.MediaStore
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.processes.utils.CreateId
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractLoggingUseCase

interface MakePhotoUseCase {

    suspend operator fun invoke(id: String? = null): Result<MultisizedImage>

    class Base(
        private val makePhotoContract: MakePhotoContract.Client,
        private val fileExplorer: FileExplorer,
        private val createId: CreateId,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : MakePhotoUseCase, AbstractLoggingUseCase(makeToastUseCase, manageResources) {

        override suspend fun invoke(id: String?): Result<MultisizedImage> = runCatching {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val newPhotoId = id ?: createId()
            val uri = fileExplorer.uriForFile(fileExplorer.file("$newPhotoId.jpg"))
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            if (makePhotoContract.makePhoto(intent)) MultisizedImage(
                id = newPhotoId,
                local = true,
                toRemove = false,
                url = uri.toString()
            )
            else throw ToastedException(R.string.photo_was_not_taken)
        }
    }
}