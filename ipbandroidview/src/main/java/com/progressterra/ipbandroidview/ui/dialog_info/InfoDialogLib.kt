package com.progressterra.ipbandroidview.ui.dialog_info

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.databinding.DialogInfoLibBinding

class InfoDialogLib : DialogFragment() {

    private val args by navArgs<InfoDialogLibArgs>()

    private var _binding: DialogInfoLibBinding? = null
    private val binding: DialogInfoLibBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        _binding = DialogInfoLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOk.setOnClickListener {
            dismiss()
        }

        binding.tvTitle.text = args.title
        binding.tvDescription.text = args.description
        binding.btnOk.setText(args.button)
        binding.ivImage.setImageResource(args.imageRes)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}