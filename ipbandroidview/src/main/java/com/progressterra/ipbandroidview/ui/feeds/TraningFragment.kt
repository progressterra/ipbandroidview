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
import com.progressterra.ipbandroidview.ui.feeds.product_sub_info.OnSubInfoItemClickListener
import com.progressterra.ipbandroidview.ui.feeds.product_sub_info.SubInfoAdapter

class TrainingFragment : Fragment(), OnSubInfoItemClickListener {
    private lateinit var binding: FragmentTrainingBinding
    private val trainingTabViewModel: TrainingTabViewModel by viewModels()
    private val adapter = SubInfoAdapter(emptyList(), this)

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
        binding.rvTraining.adapter = adapter
        trainingTabViewModel.feedsList.observe(viewLifecycleOwner) {
            adapter.items = it
            adapter.notifyDataSetChanged()
            binding.vm = trainingTabViewModel
        }
    }

    override fun onClick(feedUiModel: FeedUiModel) {
        if (feedUiModel.contentType == 1) {
            findNavController().navigate(
                R.id.subInfoDialogFragmentVideo,
                bundleOf("url" to feedUiModel.urlData)
            )
        } else {
            findNavController().navigate(
                R.id.subInfoDialogFragmentHtml,
                bundleOf("url" to feedUiModel.urlData)
            )
        }
    }
}