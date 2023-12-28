package com.progressterra.ipbandroidview.integration

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import com.bumble.appyx.core.integrationpoint.NodeComponentActivity
import com.progressterra.ipbandroidview.BuildConfig
import com.progressterra.ipbandroidview.processes.media.MakePhotoContract
import com.progressterra.ipbandroidview.processes.media.PickPhotoContract
import com.progressterra.ipbandroidview.processes.utils.MakeDialogContract
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManagePermissionContract
import com.progressterra.ipbandroidview.processes.utils.StartActivityContract
import com.progressterra.ipbandroidview.processes.utils.StartActivityForResultContract
import com.progressterra.ipbandroidview.shared.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.SimpleDialog
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

abstract class IpbActivity : NodeComponentActivity(), ManagePermissionContract.Listener,
    StartActivityContract.Listener, MakePhotoContract.Listener, PickPhotoContract.Listener, StartActivityForResultContract.Listener {

    @Composable
    abstract fun Content()

    abstract val messagingService: Class<*>?

    private val managePermission: ManagePermissionContract.Activity by inject()
    private val startActivity: StartActivityContract.Activity by inject()
    private val makePhoto: MakePhotoContract.Activity by inject()
    private val pickPhoto: PickPhotoContract.Activity by inject()
    private val makeDialog: MakeDialogContract.Activity by inject()
    private val makeToastUseCase: MakeToastUseCase by inject()
    private val startActivityForResult: StartActivityForResultContract.Activity by inject()

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

    override fun startForResult(intent: Intent, code: Int) {
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            startActivityForResult.onActivityResult(code, it.resultCode, it.data)
        }.launch(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        messagingService?.let { startService(Intent(this, it)) }
        actionBar?.hide()
        setContent {
            IpbTheme {
                Content()
                var showDialog by remember { mutableStateOf(false) }
                var dialogText by remember { mutableStateOf("") }
                var dialogAction by remember { mutableStateOf("") }
                var dialogOnAction by remember { mutableStateOf({}) }
                LaunchedEffect(Unit) {
                    makeDialog.setListener(object : MakeDialogContract.Listener {

                        override fun start(text: String, action: String, onAction: () -> Unit) {
                            showDialog = true
                            dialogText = text
                            dialogAction = action
                            dialogOnAction = onAction
                        }
                    })
                }
                SimpleDialog(
                    text = dialogText,
                    visible = showDialog,
                    onDismiss = { showDialog = false },
                    action = dialogAction,
                    onAction = dialogOnAction
                )
            }
        }
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