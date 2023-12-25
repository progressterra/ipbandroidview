package com.progressterra.ipbandroidview.processes.media

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider.getUriForFile
import com.progressterra.ipbandroidview.processes.utils.CreateId
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

interface FileExplorer {

    fun pictureFile(id: String): File

    fun audioFile(id: String): File

    fun uriForFile(file: File): Uri

    fun fileForUri(uri: Uri): File

    fun inputStreamToVoices(inputStream: InputStream, id: String)

    @Suppress("unused")
    class Haccp(
        private val context: Context,
        private val authority: String,
        private val createId: CreateId
    ) : FileExplorer {

        private val folder = context.getExternalFilesDir("HACCP")!!.path

        override fun uriForFile(file: File): Uri = getUriForFile(
            context,
            authority,
            file
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

    }

    @Suppress("unused")
    class Redi(
        private val context: Context,
        private val authority: String,
        private val createId: CreateId
    ) : FileExplorer {

        private val folder = context.cacheDir.absolutePath

        override fun uriForFile(file: File): Uri = getUriForFile(
            context, authority, file
        )

        override fun inputStreamToVoices(inputStream: InputStream, id: String) = Unit

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