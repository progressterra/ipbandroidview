package com.progressterra.ipbandroidview.utils

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.progressterra.ipbandroidview.R
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*

/**
 * Класс предназначен для инкапсуляции логики создания файлов, а также работы с ними:
 * - Создание временного файла по URI (нужно при выборе файла через файловый менеджер)
 * - Создание временного пустого файла в катигории изображения ( нужно для последующей записи в него фото с камеры)
 * - Создание лаунчеров для камеры, файлового менеджера для выбора файла, файлового менеджера для сохранения файла
 * - Открытие диалога шаринга файла
 * - Создание интента для сохранение файла
 */
class FileHelper {

    fun getFileFromUri(contentResolver: ContentResolver, uri: Uri, directory: File): File {
        val file =
            File.createTempFile("suffix", "prefix", directory)
        file.outputStream().let {
            contentResolver.openInputStream(uri)?.copyTo(it)
        }
        return file
    }

    // создает файл для записи в него изображения с камеры в полном разрешении
    fun createImageFile(context: Context): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }

    // возвращает лаунчер через который можно выбрать файл с устройства с камеры
    fun getCameraOnActivityResultLauncher(
        fragment: Fragment,
        doOnComplete: () -> Unit
    ): ActivityResultLauncher<Intent> {
        return fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            try {
                if (it.resultCode == Activity.RESULT_OK) {
                    doOnComplete.invoke()
                }
            } catch (e: java.lang.Exception) {
                Log.e("cameraResult", e.toString())
            }
        }
    }

    // возвращает лаунчер через который можно выбрать файл с устройства
    fun getFileManagerActivityResultLauncher(
        fragment: Fragment,
        doOnComplete: (resultFile: File) -> Unit
    ): ActivityResultLauncher<Intent> {
        return fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let {
                    val file = getFileFromUri(
                        fragment.requireContext().contentResolver,
                        it,
                        fragment.requireContext().cacheDir
                    )
                    doOnComplete(file)
                }
            }
        }
    }

    // возвращает лаунчер через который можно сохранить файл
    fun getCreationFileActivityResultLauncher(
        fragment: Fragment, inputStream: InputStream?
    ): ActivityResultLauncher<Intent> {
        return fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                it.data?.data?.let { uri ->
                    val outputStream =
                        fragment.requireContext().contentResolver.openOutputStream(uri)

                    inputStream?.let { inputStream ->
                        outputStream.use { output ->
                            val buffer = ByteArray(4 * 1024) // buffer size
                            while (true) {
                                val byteCount = inputStream.read(buffer)
                                if (byteCount < 0) break
                                output!!.write(buffer, 0, byteCount)
                            }
                            output!!.flush()
                        }
                    }

                    Snackbar.make(
                        fragment.requireView(),
                        fragment.requireContext().getString(R.string.document_save_successfuly),
                        Snackbar.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    fun getFileCreationIntent(fileNameWithRash: String, launch: (intent: Intent) -> Unit) {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/vnd.ms-excel"
            putExtra(Intent.EXTRA_TITLE, fileNameWithRash)
        }
        launch.invoke(intent)
    }


    // Открывает диалог, чтобы поделиться файлом
    fun showShareFileDialog(inputStream: InputStream, context: Context) {
        val storeDirectory =
            context.getExternalFilesDir(null) // DCIM folder
        val outputFile = File(storeDirectory, "Contract.xls")

        outputFile.createNewFile()
        inputStream.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024) // buffer size
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }

        outputFile.also {
            val fileUri: Uri = FileProvider.getUriForFile(
                context,
                "com.progressterra.fileprovider",
                it
            )
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "application/*"
                putExtra(Intent.EXTRA_STREAM, fileUri)
            }

            if (intent.resolveActivity(context.packageManager) != null)
                context.startActivity(intent) else {
                Toast.makeText(
                    context,
                    "Действие не поддерживается ни в одном приложении",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun showFileViewDialog(inputStream: InputStream, context: Context, authority: String) {
        val storeDirectory =
            context.getExternalFilesDir(null) // DCIM folder
        val outputFile = File(storeDirectory, "ListOfFiles.pdf")

        outputFile.createNewFile()
        inputStream.use { input ->
            val outputStream = FileOutputStream(outputFile)
            outputStream.use { output ->
                val buffer = ByteArray(4 * 1024) // buffer size
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }

        outputFile.also {
            val fileUri: Uri = FileProvider.getUriForFile(
                context,
                authority,
                it
            )

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(fileUri, "application/pdf")
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }

            if (intent.resolveActivity(context.packageManager) != null)
                context.startActivity(intent) else {
                Toast.makeText(
                    context,
                    context.getText(R.string.incorrect_action),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}