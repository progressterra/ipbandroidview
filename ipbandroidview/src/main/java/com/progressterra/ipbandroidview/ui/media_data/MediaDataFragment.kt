package com.progressterra.ipbandroidview.ui.media_data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentMediaDataBinding
import com.progressterra.ipbandroidview.databinding.ItemMediaDataHtmlLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.media_data.models.ContentType
import com.progressterra.ipbandroidview.ui.media_data.models.MediaDataUi

class MediaDataFragment :
    BaseBindingFragment<FragmentMediaDataBinding, MediaDataViewModel>(R.layout.fragment_media_data) {

    override val vm by viewModels<MediaDataViewModel>()

    private val args by navArgs<MediaDataFragmentArgs>()

    override fun onInitBinding(binding: FragmentMediaDataBinding, savedInstanceState: Bundle?) {
        super.onInitBinding(binding, savedInstanceState)
        setupViewModel()
    }

    fun setupViewModel() {
        vm.mediaData.observe(viewLifecycleOwner) { it ->
            it.data?.let { mediaData ->
                val infalter = LayoutInflater.from(requireContext())

                when (mediaData.contentType) {
                    ContentType.HTML -> binding.container.addView(
                        getHtmlView(
                            infalter,
                            mediaData
                        )
                    )
                    ContentType.PDF
                    -> binding.container.addView(
                        infalter.inflate(
                            R.layout.item_media_data_pdf_lib,
                            binding.container,
                            false
                        )
                    )
                    ContentType.VIDEO -> binding.container.addView(
                        infalter.inflate(
                            R.layout.item_media_data_video_lib, binding.container,
                            false
                        )
                    )
                }
            }
        }
    }


    private fun getHtmlView(inflater: LayoutInflater, mediaData: MediaDataUi): View {
        val binding = ItemMediaDataHtmlLibBinding.inflate(inflater)
        binding.productSubInfo = mediaData
        binding.btnShowMore.setOnClickListener {
            findNavController().navigate(
                R.id.dialogWebViewLib,
                bundleOf("url" to mediaData.urlData)
            )
        }

        return binding.root
    }
}