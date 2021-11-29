package com.progressterra.ipbandroidview.ui.media_data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentMediaDataBinding
import com.progressterra.ipbandroidview.databinding.ItemMediaDataHtmlLibBinding
import com.progressterra.ipbandroidview.databinding.ItemMediaDataPdfLibBinding
import com.progressterra.ipbandroidview.databinding.ItemMediaDataVideoLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.media_data.models.ContentType
import com.progressterra.ipbandroidview.ui.media_data.models.MediaDataUi
import com.progressterra.ipbandroidview.utils.FileHelper
import com.progressterra.ipbandroidview.utils.SResult

class MediaDataFragment :
    BaseBindingFragment<FragmentMediaDataBinding, MediaDataViewModel>(R.layout.fragment_media_data) {

    override val vm by viewModels<MediaDataViewModel>()

    private val fileHelper by lazy { FileHelper() }


    private val args by navArgs<MediaDataFragmentArgs>()


    override fun onInitBinding(binding: FragmentMediaDataBinding, savedInstanceState: Bundle?) {
        super.onInitBinding(binding, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
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
                    -> binding.container.addView(getPdfView(infalter, mediaData))
                    ContentType.VIDEO
                    -> binding.container.addView(
                        getVideoView(infalter, mediaData)
                    )
                }
            }
        }

        vm.downloadedFileStream.observeAndHandleSResult {
            if (it is SResult.Success)
                it.handle {
                    fileHelper.showFileViewDialog(
                        it.data,
                        requireContext(),
                        args.authority
                    )
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

    private fun getPdfView(inflater: LayoutInflater, mediaData: MediaDataUi): View {
        val binding = ItemMediaDataPdfLibBinding.inflate(inflater)
        binding.productSubInfo = mediaData
        binding.btnShowMore.setOnClickListener {
            vm.downloadFile(mediaData.urlData)
        }
        return binding.root
    }

    private fun getVideoView(inflater: LayoutInflater, mediaData: MediaDataUi): View {
        val binding = ItemMediaDataVideoLibBinding.inflate(inflater)
        binding.productSubInfo = mediaData

        Glide.with(binding.root.context)
            .load(mediaData.urlData)
            .thumbnail(0.5f)
            .into(binding.ivPreview)

        binding.ivPreview.setOnClickListener {
            findNavController().navigate(
                R.id.subInfoDialogFragmentVideo,
                bundleOf("url" to mediaData.urlData)
            )
        }

        return binding.root
    }
}