package com.progressterra.ipbandroidview.ui.user_inviting

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentInviteFriendsStartLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment


internal class InviteFriendsStartFragment :
    BaseBindingFragment<FragmentInviteFriendsStartLibBinding, UserInviteInfoViewModel>(
        R.layout.fragment_invite_friends_start_lib
    ) {

    override val vm by viewModels<UserInviteInfoViewModel>()

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
}