package com.progressterra.ipbandroidview.processes.media

import android.content.Context
import android.net.Uri
import com.progressterra.ipbandroidview.shared.log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException
import java.io.InputStream

interface FileExplorer {

    fun file(fileName: String): File

    fun uriForFile(file: File): Uri

    fun fileForUri(uri: Uri, fileName: String): File

    fun saveInputStream(inputStream: InputStream, fileName: String)

    fun downloadFile(
        url: String,
        fileName: String,
        progress: (Float) -> Unit,
        handleException: (Exception) -> Unit
    )

    class Base(
        private val context: Context
    ) : FileExplorer {

        private val folder = context.filesDir.absolutePath

        override fun uriForFile(file: File): Uri = Uri.parse(file.absolutePath)

        //TODO
        override fun saveInputStream(inputStream: InputStream, fileName: String) = Unit

        override fun file(fileName: String) = File("$folder/$fileName")


        override fun fileForUri(uri: Uri, fileName: String): File {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = file(fileName)
            inputStream.use { input ->
                file.outputStream().use { output ->
                    input?.copyTo(output)
                }
            }
            return file
        }

        override fun downloadFile(
            url: String,
            fileName: String,
            progress: (Float) -> Unit,
            handleException: (Exception) -> Unit
        ) {
            val request = Request.Builder()
                .url(url)
                .build()
            val client = OkHttpClient.Builder()
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val responseBody = response.body
                    if (responseBody != null) {
                        try {
                            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
                                val sink = output.sink().buffer()
                                val source = responseBody.source()
                                val total = responseBody.contentLength()
                                var totalRead: Long = 0
                                val buffer = ByteArray(8 * 1024)
                                var read: Long
                                while (source.read(buffer).also { read = it.toLong() } != -1) {
                                    sink.write(buffer, 0, read.toInt())
                                    totalRead += read
                                    progress(totalRead / total.toFloat() * 100)
                                }
                                sink.flush()
                            }
                        } catch (e: IOException) {
                            log(e.toString())
                            handleException(e)
                        }
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    log(e.toString())
                }
            })
        }
    }
}