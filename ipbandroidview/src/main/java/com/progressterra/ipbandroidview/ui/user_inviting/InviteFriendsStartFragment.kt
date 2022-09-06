package com.progressterra.ipbandroidview.ui.user_inviting

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentInviteFriendsStartLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class InviteFriendsStartFragment :
    BaseBindingFragment<FragmentInviteFriendsStartLibBinding, UserInviteInfoViewModel>(
        R.layout.fragment_invite_friends_start_lib
    ) {

    override val vm by viewModel<UserInviteInfoViewModel>()

    override fun onInitBinding(
        binding: FragmentInviteFriendsStartLibBinding,
        savedInstanceState: Bundle?
    ) {
        super.onInitBinding(binding, savedInstanceState)
        setupListeners()
        setupHeader(R.string.invite_friends, true)

    }

    private fun setupListeners() {
        binding.btnContactList.setOnClickListener {
            findNavController().navigate(InviteFriendsStartFragmentDirections.toInvite())
        }

        binding.btnCopyLink.setOnClickListener {
            copyTextOfInviting()
        }

        binding.btnShare.setOnClickListener {
            showShareDialog()
        }
    }

    private fun copyTextOfInviting() {
        val clipboard: ClipboardManager? =
            getSystemService(requireContext(), ClipboardManager::class.java)
        val clip = ClipData.newPlainText(
            "label",
            vm.infoForInvitingMembers.value?.data?.textInvite ?: ""
        )
        clipboard?.setPrimaryClip(clip)
        Toast.makeText(
            requireContext(),
            getString(R.string.text_copied_to_clipboard),
            Toast.LENGTH_SHORT
        )
            .show()
    }

    private fun showShareDialog() {
        val intent = Intent(Intent.ACTION_SEND).apply {

            type = "text/plain"

            val extraString = vm.infoForInvitingMembers.value?.data?.textInvite ?: ""

            putExtra(
                Intent.EXTRA_TEXT,
                extraString
            )
        }
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            requireActivity().startActivity(intent)
        } else {
            Toast.makeText(
                context,
                getString(R.string.incorrect_action),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}