package com.progressterra.ipbandroidview.integration

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.bumble.appyx.core.integrationpoint.NodeComponentActivity
import com.progressterra.ipbandroidview.BuildConfig
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.activity.MakePhotoContract
import com.progressterra.ipbandroidview.shared.activity.ManagePermissionContract
import com.progressterra.ipbandroidview.shared.activity.PickPhotoContract
import com.progressterra.ipbandroidview.shared.activity.StartActivityContract
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

abstract class IpbActivity : NodeComponentActivity(), ManagePermissionContract.Listener,
    StartActivityContract.Listener, MakePhotoContract.Listener, PickPhotoContract.Listener {

    @Composable
    abstract fun Content()

    abstract val messagingService: Class<*>?

    private val managePermission: ManagePermissionContract.Activity by inject()
    private val startActivity: StartActivityContract.Activity by inject()
    private val makePhoto: MakePhotoContract.Activity by inject()
    private val pickPhoto: PickPhotoContract.Activity by inject()
    private val makeToastUseCase: MakeToastUseCase by inject()

    private val pictureResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            makePhoto.completePhoto(it.resultCode == Activity.RESULT_OK)
        }

    private val pickPhotoLauncher =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            pickPhoto.completePhoto(uri)
        }

    override fun pickPhoto() {
        pickPhotoLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messagingService?.let { startService(Intent(this, it)) }
        actionBar?.hide()
        setContent { Content() }
        managePermission.setListener(this)
        startActivity.setListener(this)
        makePhoto.setListener(this)
        pickPhoto.setListener(this)
        if (IpbAndroidViewSettings.DEBUG) {
            lifecycleScope.launch { makeToastUseCase("built upon ${BuildConfig.VERSION}") }
        }
    }

    override fun startPhoto(photoIntent: Intent) {
        pictureResultLauncher.launch(photoIntent)
    }

    override fun start(intent: Intent) {
        if (intent.resolveActivity(packageManager) != null) startActivity(intent)
    }

    override fun checkPermission(permission: String): Boolean =
        checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

    override fun requestPermission(permission: String) = requestPermissions(arrayOf(permission), 0)
}