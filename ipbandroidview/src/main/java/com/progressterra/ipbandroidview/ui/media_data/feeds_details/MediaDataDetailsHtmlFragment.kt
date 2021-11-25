package com.progressterra.ipbandroidview.ui.media_data.feeds_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.databinding.DialogDetailFeedHtmlLibBinding

class MediaDataDetailsHtmlFragment : DialogFragment() {

    private lateinit var binding: DialogDetailFeedHtmlLibBinding

    private val args: MediaDataDetailsHtmlFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogDetailFeedHtmlLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.webView.apply {
            loadUrl(args.url)
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

}