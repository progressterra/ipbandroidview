package com.progressterra.ipbandroidview.ui.contacts

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentInviteUserLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel


class UserInviteFragment :
    BaseBindingFragment<FragmentInviteUserLibBinding, BaseBindingViewModel>(R.layout.fragment_invite_user_lib) {

    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                showContactList()
            } else {
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS))
                    onPermissionDenied()
                else
                    onPermissionNeverAskAgain()
            }
        }

    private val settingsLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (isPermissionGranted(Manifest.permission.READ_CONTACTS)) {
                showContactList()
            } else {
                onPermissionNeverAskAgain()
            }
        }


    override fun onInitBinding(binding: FragmentInviteUserLibBinding, savedInstanceState: Bundle?) {
        super.onInitBinding(binding, savedInstanceState)


        binding.btnSelectContacts.setOnClickListener {
            when {
                isPermissionGranted(Manifest.permission.READ_CONTACTS) -> showContactList()
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) -> launchPermission()
                else -> launchPermission()
            }
        }

        setupHeader(R.string.invite_friends, true)
    }

    private fun showContactList() {
        findNavController().navigate(UserInviteFragmentDirections.toContactList())
    }

    private fun launchPermission() {
        permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
    }

    private fun onPermissionDenied() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.permission_on_denied_title)
            .setMessage(R.string.permission_on_denied_description)
            .setPositiveButton(R.string.yes) { d, _ ->
                launchPermission()
                d.dismiss()
            }
            .setNegativeButton(R.string.no) { d, _ ->
                d.dismiss()
            }
            .show()
    }

    private fun onPermissionNeverAskAgain() {
        val snackbar = Snackbar.make(
            requireView(),
            "Предоставить разрешение теперь можно только в настройках",
            Snackbar.LENGTH_LONG
        ).setAction(R.string.settings_tab) {
            settingsLauncher.launch(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", requireActivity().packageName, null)
            })
        }

        snackbar.show()
    }
}