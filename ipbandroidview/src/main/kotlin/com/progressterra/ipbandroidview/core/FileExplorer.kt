package com.progressterra.ipbandroidview.core

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

interface FileExplorer {

    fun inputStreamToVoices(inputStream: InputStream, id: String)

    fun pictureFile(id: String): File

    fun audioFile(id: String): File

    fun uriForFile(file: File): Uri

    fun reset()

    class Base(
        private val context: Context,
        private val authority: String
    ) : FileExplorer {

        private val voiceFolderPath by lazy {
            "${context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)}"
        }
        private val picturesFolderPath by lazy {
            "${context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)}"
        }


        override fun uriForFile(file: File): Uri = FileProvider.getUriForFile(
            context,
            authority,
            file
        )


        override fun inputStreamToVoices(inputStream: InputStream, id: String) {
            if (!exist(id))
                inputStream.use { input ->
                    val fos = FileOutputStream(File("$voiceFolderPath/$id.m4a"))
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

        override fun audioFile(id: String): File =
            File("$voiceFolderPath/$id.m4a")


        override fun pictureFile(id: String): File = File("$picturesFolderPath/$id.jpg")

        private fun exist(id: String): Boolean =
            File("$voiceFolderPath/$id.m4a").exists() || File("$picturesFolderPath/$id.jpg").exists()

        override fun reset() {
            File(voiceFolderPath).list()?.forEach {
                File(it).delete()
            }
            File(picturesFolderPath).list()?.forEach {
                File(it).delete()
            }
        }
    }
}