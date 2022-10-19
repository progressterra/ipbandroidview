package com.progressterra.ipbandroidview.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


interface FileExplorer {

    fun createAudioName(checkId: String): String

    fun path(id: String): String

    fun home(): File

    fun obtainBitmap(id: String): Bitmap

    fun obtainFile(id: String): File

    fun delete(id: String)

    fun exists(id: String): Boolean

    fun saveFile(inputStream: InputStream, id: String)

    fun uriForFile(file: File): Uri

    class Base(
        private val context: Context,
        private val authority: String
    ) : FileExplorer {

        override fun home(): File = context.filesDir

        override fun uriForFile(file: File): Uri = FileProvider.getUriForFile(
            context,
            authority,
            file
        )

        override fun obtainFile(id: String): File = File(path(id))

        override fun saveFile(inputStream: InputStream, id: String) {
            inputStream.use { input ->
                val fos = FileOutputStream(path(id))
                fos.use { output ->
                    val buffer = ByteArray(4 * 1024)
                    var read: Int
                    while (input.read(buffer).also { read = it } != -1) {
                        output.write(buffer, 0, read)
                    }
                    output.flush()
                }
            }
        }

        override fun path(id: String): String =
            "${context.filesDir.path}/$id"

        override fun obtainBitmap(id: String): Bitmap = BitmapFactory.decodeFile(path(id))

        override fun delete(id: String) {
            File(path(id)).delete()
        }

        override fun exists(id: String): Boolean = File(path(id)).exists()

        override fun createAudioName(checkId: String): String = "Voice message for check $checkId.m4a"
    }
}