package com.progressterra.ipbandroidview.ui.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentTrainingBinding
import com.progressterra.ipbandroidview.ui.feeds.models.ContentType
import com.progressterra.ipbandroidview.ui.feeds.product_sub_info.FeedsAdapter

class TrainingFragment : Fragment() {
    private lateinit var binding: FragmentTrainingBinding
    private val feedsViewModel: FeedsViewModel by viewModels()

    private val adapter = FeedsAdapter {
        if (it.contentType == ContentType.VIDEO) {
            findNavController().navigate(
                R.id.subInfoDialogFragmentVideo,
                bundleOf("url" to it.urlData)
            )
        } else if (it.contentType == ContentType.HTML) {
            findNavController().navigate(
                R.id.subInfoDialogFragmentHtml,
                bundleOf("url" to it.urlData)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = feedsViewModel
        }

        binding.rvTraining.adapter = adapter
        feedsViewModel.feedsList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}