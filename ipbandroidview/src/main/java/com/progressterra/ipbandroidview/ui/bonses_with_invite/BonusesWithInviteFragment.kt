package com.progressterra.ipbandroidview.ui.bonses_with_invite

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.MainNavGraphDirections
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentBonusesWithInviteLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel

class BonusesWithInviteFragment :
    BaseBindingFragment<FragmentBonusesWithInviteLibBinding, BaseBindingViewModel>(
        R.layout.fragment_bonuses_with_invite_lib
    ) {

    override fun onInitBinding(
        binding: FragmentBonusesWithInviteLibBinding,
        savedInstanceState: Bundle?
    ) {
        super.onInitBinding(binding, savedInstanceState)
        setupHeader(R.string.bdwi_title)

        binding.btnInvite.setOnClickListener {
            findNavController().navigate(
                MainNavGraphDirections.actionGlobalInviteFriendsFlow()
            )
        }
    }
}