package com.progressterra.ipbandroidview.ui.media_data.media_data_details

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.DialogDetailFeedHtmlLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingDialogFragment
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel

class MediaDataDetailsHtmlFragment :
    BaseBindingDialogFragment<DialogDetailFeedHtmlLibBinding, BaseBindingViewModel>(R.layout.dialog_detail_feed_html_lib) {

    private val args: MediaDataDetailsHtmlFragmentArgs by navArgs()

    override fun onInitBinding(
        binding: DialogDetailFeedHtmlLibBinding,
        savedInstanceState: Bundle?
    ) {
        super.onInitBinding(binding, savedInstanceState)
        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.webView.apply {
            loadUrl(args.url)
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
        }
    }

}