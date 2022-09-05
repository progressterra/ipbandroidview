package com.progressterra.ipbandroidview.ui.media_data

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentMediaDataLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.media_data.models.ContentType
import com.progressterra.ipbandroidview.utils.FileHelper
import com.progressterra.ipbandroidview.utils.SResult

class MediaDataListFragment :
    BaseBindingFragment<FragmentMediaDataLibBinding, MediaDataListViewModel>(R.layout.fragment_media_data_lib) {

    private val fileHelper by lazy { FileHelper() }

    private val args: MediaDataListFragmentArgs by navArgs()

    override val vm by viewModels<MediaDataListViewModel>()

    private val adapter = MediaDataListAdapter {
        when (it.contentType) {
            ContentType.VIDEO -> findNavController().navigate(
                R.id.subInfoDialogFragmentVideo,
                bundleOf("url" to it.urlData)
            )

            ContentType.HTML -> findNavController().navigate(
                R.id.dialogWebViewLib,
                bundleOf("url" to it.urlData)
            )

            ContentType.PDF -> {
                vm.downloadFile(it.urlData)
            }
            ContentType.UNKNOWN -> {

            }
        }
    }

    override fun onInitBinding(binding: FragmentMediaDataLibBinding, savedInstanceState: Bundle?) {
        super.onInitBinding(binding, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.rvTraining.adapter = adapter
    }

    private fun setupViewModel() {
        with(vm) {
            mediaDataList.observe(viewLifecycleOwner) {
                adapter.submitList(it.data)
            }

            downloadedFileStream.observeAndHandleSResult {
                if (it is SResult.Success)
                        fileHelper.showFileViewDialog(
                            it.data,
                            requireContext(),
                            args.authority
                        )

            }
        }
    }
}
