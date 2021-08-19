package com.progressterra.ipbandroidview.ui.promocode

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentPromocodeLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment

class PromoCodeFragment :
    BaseBindingFragment<FragmentPromocodeLibBinding, PromoCodeViewModel>(R.layout.fragment_promocode_lib) {

    override val vm by viewModels<PromoCodeViewModel>()

    private val showCameraFragment =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                result.data?.getStringExtra(INTENT_EXTRA_NAME)?.let {
                    vm.code.value = it
                }
            }
        }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                showCameraFragment.launch(Intent(requireContext(), BarScannerActivity::class.java))
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA))
                    onCameraDenied()
                else
                    onCameraNeverAskAgain()
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

    private fun showCameraWithPermissionCheck() {
        when {
            isPermissionGranted(Manifest.permission.CAMERA) -> showCamera()
            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> launchPermission()
            else -> launchPermission()
        }
    }

    private fun launchPermission() {
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }


    private fun showCamera() {
        showCameraFragment.launch(Intent(requireContext(), BarScannerActivity::class.java))
    }

    private fun onCameraDenied() {
        Toast.makeText(requireContext(), R.string.permission_rationale, Toast.LENGTH_LONG)
            .show()
    }

    private fun onCameraNeverAskAgain() {
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