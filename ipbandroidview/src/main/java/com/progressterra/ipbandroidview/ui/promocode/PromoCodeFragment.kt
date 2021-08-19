package com.progressterra.ipbandroidview.ui.promocode

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentPromocodeLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import permissions.dispatcher.*

@RuntimePermissions
class PromoCodeFragment :
    BaseBindingFragment<FragmentPromocodeLibBinding, PromoCodeViewModel>(R.layout.fragment_promocode_lib) {

    override val vm by viewModels<PromoCodeViewModel>()

    private object CodeContract : ActivityResultContract<String, String>() {
        override fun createIntent(context: Context, input: String?): Intent {
            return Intent(context, BarScannerActivity::class.java)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): String? {
            return if (resultCode == AppCompatActivity.RESULT_OK) {
                intent?.getStringExtra(INTENT_EXTRA_NAME)
            } else {
                null
            }
        }
    }

    private val showCameraFragment =
        registerForActivityResult(CodeContract) { code: String? ->
            code?.let {
                vm.code.value = it
            }
        }

    override fun onInitBinding(binding: FragmentPromocodeLibBinding, savedInstanceState: Bundle?) {
        super.onInitBinding(binding, savedInstanceState)
        binding.ivQr.setOnClickListener {
            showCameraWithPermissionCheck()
        }

        vm.result.observe(viewLifecycleOwner) {
            it.defaultResultHandling()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    fun showCamera() {
        showCameraFragment.launch(javaClass.simpleName)
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    fun showRationale(request: PermissionRequest) {
        request.proceed()
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    fun onCameraDenied() {
        Toast.makeText(requireContext(), R.string.permission_rationale, Toast.LENGTH_LONG)
            .show()
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    fun onCameraNeverAskAgain() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.promocode_never_dialog_title)
            .setMessage(R.string.promocode_never_dialog_description)
            .setPositiveButton(R.string.promocode_never_ok) { d, _ ->
                startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", requireActivity().packageName, null)
                })
                d.dismiss()
            }
            .setNegativeButton(R.string.promocode_never_cancel) { d, _ ->
                d.dismiss()
            }
            .show()
    }

    companion object {
        internal const val INTENT_EXTRA_NAME = "PromoCodeFragment_Extra"
    }
}