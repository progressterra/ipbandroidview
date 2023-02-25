package com.progressterra.ipbandroidview.core

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider.getUriForFile
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

interface FileExplorer {

    fun inputStreamToVoices(inputStream: InputStream, id: String)

    fun pictureFile(id: String): File

    fun audioFile(id: String): File

    fun uriForFile(file: File): Uri

    fun reset()

    class Haccp(
        private val context: Context,
        private val authority: String
    ) : FileExplorer {

        private val folder = context.getExternalFilesDir("HACCP")!!.path

        override fun uriForFile(file: File): Uri = getUriForFile(
            context,
            authority,
            file
        )

        override fun inputStreamToVoices(inputStream: InputStream, id: String) {
            if (!exist(id))
                inputStream.use { input ->
                    val fos = FileOutputStream(File("$folder/$id.m4a"))
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
            File("$folder/$id.m4a")

        override fun pictureFile(id: String): File = File("$folder/$id.jpg")

        private fun exist(id: String): Boolean =
            File("$folder/$id.m4a").exists() || File("$folder/$id.jpg").exists()

        override fun reset() {
            File(folder).list()?.forEach {
                File(it).delete()
            }
            File(folder).list()?.forEach {
                File(it).delete()
            }
        }
    }
}