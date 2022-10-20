package com.progressterra.ipbandroidview.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


interface FileExplorer {

    fun savePicture(inputStream: InputStream, id: String, force: Boolean = false)

    fun obtainPictureAsBitmap(id: String): Bitmap

    fun obtainPictureAsFile(id: String): File

    fun deletePicture(id: String)

    fun obtainOrCreateAudioFile(id: String): File

    fun uriForFile(file: File): Uri

    fun picturesFolder(): File

    fun exist(id: String): Boolean

    class Base(
        private val context: Context,
        private val authority: String
    ) : FileExplorer {

        private val voiceFolderPath =
            "${context.getExternalFilesDir(Environment.DIRECTORY_RECORDINGS)}"
        private val picturesFolderPath =
            "${context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)}"

        override fun uriForFile(file: File): Uri = FileProvider.getUriForFile(
            context,
            authority,
            file
        )

        override fun savePicture(inputStream: InputStream, id: String, force: Boolean) {
            if (!File("$picturesFolderPath/$id.jpg").exists() || force)
                inputStream.use { input ->
                    val fos = FileOutputStream(File("$picturesFolderPath/$id.jpg"))
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

        override fun obtainOrCreateAudioFile(id: String): File =
            File("$voiceFolderPath/Voice $id.m4a")

        override fun obtainPictureAsBitmap(id: String): Bitmap =
            BitmapFactory.decodeFile("$picturesFolderPath/$id.jpg")

        override fun obtainPictureAsFile(id: String): File = File("$picturesFolderPath/$id.jpg")

        override fun deletePicture(id: String) {
            File("$picturesFolderPath/$id.jpg").delete()
        }

        override fun picturesFolder(): File = File(picturesFolderPath)

        override fun exist(id: String): Boolean =
            File("$voiceFolderPath/Voice $id.m4a").exists() || File("$picturesFolderPath/$id.jpg").exists()

    }
}