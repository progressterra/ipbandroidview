package com.progressterra.ipbandroidview.ui.media_data

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentMediaDataLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.media_data.models.ContentType
import com.progressterra.ipbandroidview.utils.FileHelper

class MediaDataListFragment :
    BaseBindingFragment<FragmentMediaDataLibBinding, MediaDataListViewModel>(R.layout.fragment_media_data_lib) {

    private val fileHelper by lazy { FileHelper() }

    override val vm by viewModels<MediaDataListViewModel>()

    private val adapter = FeedsAdapter {
        when (it.contentType) {
            ContentType.VIDEO -> findNavController().navigate(
                R.id.subInfoDialogFragmentVideo,
                bundleOf("url" to it.urlData)
            )

            ContentType.HTML -> findNavController().navigate(
                R.id.subInfoDialogFragmentHtml,
                bundleOf("url" to it.urlData)
            )

            ContentType.PDF -> {
                vm.downloadFile(it.urlData)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.rvTraining.adapter = adapter
    }

    private fun setupViewModel() {
        with(vm) {
            mediaDataList.observe(viewLifecycleOwner) {
                adapter.submitList(it.data ?: emptyList())
            }

            downloadedFileStream.observe(viewLifecycleOwner) {
                fileHelper.showFileViewDialog(
                    it,
                    requireContext(),
                    "com.progressterra.fileprovider"
                )
            }
        }
    }
}