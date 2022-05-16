package com.progressterra.ipbandroidview.ui.set_personal_info

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.progressterra.ipbandroidview.databinding.FragmentUserInfoLibBinding
import com.progressterra.ipbandroidview.utils.DEFAULT_RES
import com.progressterra.ipbandroidview.utils.FileHelper
import com.progressterra.ipbandroidview.utils.SResult
import com.squareup.picasso.Picasso
import java.io.File
import java.io.IOException
import java.io.InputStream

const val CAMERA_PERMISSION_REQUEST_SNILS = 12
const val CAMERA_PERMISSION_REQUEST_PASSPORT = 13
const val CAMERA_PERMISSION_REQUEST_CONTRACT = 14

const val SELECT_SNILS_PHOTO = 11
const val SELECT_PASSPORT_PHOTO = 12
const val SELECT_CONTRACT_PHOTO = 13

class UserInfoFragment : Fragment() {

    private val viewModel: UserInfoViewModel by viewModels()
    private var currentPhotoPath: String? = null
    private lateinit var binding: FragmentUserInfoLibBinding
    private val fileHelper by lazy { FileHelper() }
    private var saveFileInputStream: InputStream? = null
    private val arg: UserInfoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserInfoLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListeners()
        setupViewModel()
    }

    private fun setupView() {
        binding.apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        if (arg.headerRes != DEFAULT_RES) {
            binding.ivHeader.setImageResource(arg.headerRes)
        }
    }


    private fun setupListeners() {

        binding.uploadPhotoLayout.ivSelectPassportFromCamera.setOnClickListener {

            if (viewModel.snilsImageUploadState.value?.isLoading() == true || viewModel.passportImageUploadState.value?.isLoading() == true) {
                return@setOnClickListener
            }

            SelectImageSourceDialog().apply {
                selectFromDeviceListener = View.OnClickListener {
                    openFileManager(SELECT_PASSPORT_PHOTO)
                    this.dismiss()
                }
                selectFromCameraListener = View.OnClickListener {
                    selectImageFromCamera(SELECT_PASSPORT_PHOTO)
                    this.dismiss()
                }
            }.show(childFragmentManager, null)
        }

        binding.tvGphContractShare.setOnClickListener {
            viewModel.downloadContractOfAmbassador(isForShare = true)
        }

        binding.ivSelectContractFromCamera.setOnClickListener {
            selectImageFromCamera(SELECT_CONTRACT_PHOTO)
        }

        binding.ivSelectContractFromDevice.setOnClickListener {
            openFileManager(SELECT_CONTRACT_PHOTO)
        }

        binding.tvGphContractDownload.setOnClickListener {
            viewModel.downloadContractOfAmbassador(isForShare = false)
        }

        binding.uploadPhotoLayout.ivSelectSnilsFromCamera.setOnClickListener {

            if (viewModel.snilsImageUploadState.value?.isLoading() == true || viewModel.passportImageUploadState.value?.isLoading() == true) {
                return@setOnClickListener
            }

            SelectImageSourceDialog().apply {

                selectFromDeviceListener = View.OnClickListener {
                    openFileManager(SELECT_SNILS_PHOTO)
                    this.dismiss()
                }

                selectFromCameraListener = View.OnClickListener {
                    selectImageFromCamera(SELECT_SNILS_PHOTO)
                    this.dismiss()
                }

            }
                .show(childFragmentManager, null)
        }

        binding.etName.doOnTextChanged { text, _, _, _ -> viewModel.updateName(text.toString()) }
        binding.etLastName.doOnTextChanged { text, _, _, _ ->
            viewModel.updateLastName(text.toString())
        }
        binding.etMiddleName.doOnTextChanged { text, _, _, _ ->
            viewModel.updateMiddleName(text.toString())
        }
        binding.etAccount.doOnTextChanged { text, _, _, _ ->
            viewModel.updateAccount(text.toString())
        }
        binding.etBankName.doOnTextChanged { text, _, _, _ ->
            viewModel.updateBankName(text.toString())
        }
        binding.etBic.doOnTextChanged { text, _, _, _ ->
            viewModel.updateBic(text.toString())
        }
        binding.etCorrAccount.doOnTextChanged { text, _, _, _ ->
            viewModel.updateCorrespondentAccount(text.toString())
        }
        binding.etInn.doOnTextChanged { text, _, _, _ ->
            viewModel.updateInn(text.toString())
        }
        binding.etCpp.doOnTextChanged { text, _, _, _ -> viewModel.updateCpp(text.toString()) }
        binding.etClientInn.doOnTextChanged { text, _, _, _ -> viewModel.updateClientInn(text.toString()) }
    }

    private fun setupViewModel() {
        viewModel.apply {
            showMessage.observe(viewLifecycleOwner, this@UserInfoFragment::showMessage)
            ambassadorStateInfo.observe(viewLifecycleOwner) {
                Picasso.get().load(it.ambassadorInfo.dataAmbassador?.urlImagePassport)
                    .into(binding.uploadPhotoLayout.passportPreview)

                Picasso.get().load(it.ambassadorInfo.dataAmbassador?.urlImageSNILS)
                    .into(binding.uploadPhotoLayout.snilsPreview)

                Picasso.get().load(it.ambassadorInfo.dataAmbassador?.urlImagesContract).into(
                    binding.actPreview
                )
            }
            inputStreamForDownloadedContractFile.observe(viewLifecycleOwner) {
                it?.let {
                    saveFileInputStream = it
                    fileHelper.getFileCreationIntent("Договор.xls") {
                        registerToOpenCreationFileActivityResult.launch(it)
                    }
                }
            }
            inputStreamForDownloadedContractFileForShare.observe(viewLifecycleOwner) {
                fileHelper.showShareFileDialog(it, requireContext())
            }
        }
    }

    private fun showMessage(result: SResult.Toast) {
        if (result.message is Int) {
            Snackbar.make(requireView(), result.message, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun selectImageFromCamera(imageType: Int) {
        when (imageType) {
            SELECT_PASSPORT_PHOTO -> tryToOpenCamera(SELECT_PASSPORT_PHOTO)
            SELECT_SNILS_PHOTO -> tryToOpenCamera(SELECT_SNILS_PHOTO)
            SELECT_CONTRACT_PHOTO -> tryToOpenCamera(SELECT_CONTRACT_PHOTO)
        }
    }

    private fun tryToOpenCamera(imageType: Int) {
        when (imageType) {
            SELECT_PASSPORT_PHOTO -> {
                requestPermissions(
                    arrayOf(
                        "android.permission.CAMERA"
                    ), CAMERA_PERMISSION_REQUEST_PASSPORT
                )
            }
            SELECT_SNILS_PHOTO -> {
                requestPermissions(
                    arrayOf(
                        "android.permission.CAMERA"
                    ), CAMERA_PERMISSION_REQUEST_SNILS
                )
            }
            SELECT_CONTRACT_PHOTO -> {
                requestPermissions(
                    arrayOf(
                        "android.permission.CAMERA"
                    ), CAMERA_PERMISSION_REQUEST_CONTRACT
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_SNILS -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    openCamera(SELECT_SNILS_PHOTO)
                } else {
                    showNeedPermissionError()
                }
            }
            CAMERA_PERMISSION_REQUEST_PASSPORT -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    openCamera(SELECT_PASSPORT_PHOTO)
                } else {
                    showNeedPermissionError()
                }
            }
            CAMERA_PERMISSION_REQUEST_CONTRACT -> {
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    openCamera(SELECT_CONTRACT_PHOTO)
                } else {
                    showNeedPermissionError()
                }
            }
        }
    }

    // подписываемся на завершение выбора изображения с девайса
    private val registerFileManagerActivityForResultForSnilsPhoto =
        fileHelper.getFileManagerActivityResultLauncher(this) {
            viewModel.uploadSnilsImage(it)
        }

    private val registerFileManagerActivityForResultForPassportPhoto =
        fileHelper.getFileManagerActivityResultLauncher(this) {
            viewModel.uploadPassportImage(it)
        }

    private val registerFileManagerActivityForResultForContractPhoto =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let {
                    val file = fileHelper.getFileFromUri(
                        requireContext().contentResolver,
                        it,
                        requireContext().cacheDir
                    )
                    viewModel.uploadContractImage(file)
                }
            }
        }

    // открывает файловый менеджер для получения изображения с девайса
    private fun openFileManager(imageType: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        when (imageType) {
            SELECT_SNILS_PHOTO -> registerFileManagerActivityForResultForSnilsPhoto.launch(intent)
            SELECT_PASSPORT_PHOTO -> registerFileManagerActivityForResultForPassportPhoto.launch(
                intent
            )
            SELECT_CONTRACT_PHOTO -> registerFileManagerActivityForResultForContractPhoto.launch(
                intent
            )
        }
    }

    // подписывается на разультат завершения фотографирования
    private val registerCameraPassportActivityForResult =
        fileHelper.getCameraOnActivityResultLauncher(this) {
            val file = File(currentPhotoPath)
            viewModel.uploadPassportImage(file)
        }

    // подписывается на разультат завершения фотографирования
    private val registerCameraSnilsActivityForResult =
        fileHelper.getCameraOnActivityResultLauncher(this) {
            val file = File(currentPhotoPath)
            viewModel.uploadSnilsImage(file)
        }

    // подписывается на разультат завершения фотографирования
    private val registerCameraContractActivityForResult =
        fileHelper.getCameraOnActivityResultLauncher(this) {
            val file = File(currentPhotoPath)
            viewModel.uploadContractImage(file)
        }

    // создает Intent для открытия камеры
    private fun dispatchTakePictureIntent(): Intent {
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->

            // Создаем файл, в который будет записан снимок с камеры
            val photoFile: File? = try {
                fileHelper.createImageFile(requireContext()).apply {
                    currentPhotoPath = absolutePath
                }
            } catch (ex: IOException) {
                null
            }
            // получаем URI файла для передачи в камеру
            photoFile?.also {
                val photoURI: Uri = FileProvider.getUriForFile(
                    requireContext(),
                    "com.progressterra.fileprovider",
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            }
        }
    }

    // открывает камеру
    private fun openCamera(imageType: Int) {
        val intent = Intent(dispatchTakePictureIntent())
        when (imageType) {
            SELECT_PASSPORT_PHOTO -> registerCameraPassportActivityForResult.launch(intent)
            SELECT_SNILS_PHOTO -> registerCameraSnilsActivityForResult.launch(intent)
            SELECT_CONTRACT_PHOTO -> registerCameraContractActivityForResult.launch(intent)
        }
    }

    private fun showNeedPermissionError() {
        Snackbar.make(requireView(), "Предоставьте необходимые разрешния", Snackbar.LENGTH_SHORT)
            .show()
    }

    private val registerToOpenCreationFileActivityResult =
        fileHelper.getCreationFileActivityResultLauncher(this, saveFileInputStream)

}