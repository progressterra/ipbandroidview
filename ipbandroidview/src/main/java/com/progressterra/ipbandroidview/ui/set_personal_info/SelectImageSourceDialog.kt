package com.progressterra.ipbandroidview.ui.set_personal_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.progressterra.ipbandroidview.databinding.DialogSelectPhotoSourceLibBinding

class SelectImageSourceDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogSelectPhotoSourceLibBinding
    var selectFromDeviceListener: View.OnClickListener? = null
    var selectFromCameraListener: View.OnClickListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSelectPhotoSourceLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.btnSelectFromCamera.setOnClickListener(selectFromCameraListener)
        binding.btnSelectFromDevice.setOnClickListener(selectFromDeviceListener)
    }

}