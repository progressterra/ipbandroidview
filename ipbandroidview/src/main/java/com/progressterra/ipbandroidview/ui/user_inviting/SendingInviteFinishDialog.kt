package com.progressterra.ipbandroidview.ui.user_inviting

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.progressterra.ipbandroidview.databinding.DialogInviteSendingsFinishLibBinding

class SendingInviteFinishDialog : BottomSheetDialogFragment() {
    private val vm by activityViewModels<InviteUserViewModel>()

    private lateinit var binding: DialogInviteSendingsFinishLibBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogInviteSendingsFinishLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnShowListener {
            val d: BottomSheetDialog = dialog
            val sheet: FrameLayout? =
                d.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            if (sheet != null) {
                BottomSheetBehavior.from(sheet).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        return dialog
    }

    private fun setupView() {
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner
    }
}