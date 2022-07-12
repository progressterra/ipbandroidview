package com.progressterra.ipbandroidview.ui.login.personal.sexselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.DialogSexSelectionBinding
import com.progressterra.ipbandroidview.utils.extensions.dpToPx

class SexSelectionDialog : BottomSheetDialogFragment() {

    private var _binding: DialogSexSelectionBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogSexSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.NoBackgroundDialogTheme
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addMargin(view)
        binding.ivCloseDialog.setOnClickListener {
            dismiss()
        }
        binding.tvFemale.setOnClickListener {
            chooseSex(0)
        }
        binding.tvMale.setOnClickListener {
            chooseSex(1)
        }
    }

    //0 for female, 1 for male
    private fun chooseSex(sex: Int) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(
            "sex selection",
            bundleOf(
                "sex" to sex
            )
        )
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addMargin(view: View) {
        val layoutParams: FrameLayout.LayoutParams = view.layoutParams as FrameLayout.LayoutParams
        val margin = 10.dpToPx
        layoutParams.setMargins(margin, 0, margin, 0)
        view.layoutParams = layoutParams
    }
}