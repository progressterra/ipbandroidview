package com.progressterra.ipbandroidview.shared

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider.getUriForFile
import java.io.File

interface FileExplorer {

    fun pictureFile(id: String): File

    fun audioFile(id: String): File

    fun uriForFile(file: File): Uri

    fun fileForUri(uri: Uri): File

    class Redi(
        private val context: Context,
        private val authority: String,
        private val createId: CreateId
    ) : FileExplorer {

        private val folder = context.cacheDir.absolutePath

        override fun uriForFile(file: File): Uri = getUriForFile(
            context, authority, file
        )

        override fun fileForUri(uri: Uri): File {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = pictureFile(createId())
            inputStream.use { input ->
                file.outputStream().use { output ->
                    input?.copyTo(output)
                }
            }
            return file
        }

        override fun audioFile(id: String): File = File("$folder/$id.m4a")

        override fun pictureFile(id: String): File = File("$folder/$id.jpg")
    }
}