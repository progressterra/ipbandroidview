package com.progressterra.ipbandroidview.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.databinding.FragmentChatDrawableLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.chat.utils.MessageWithDrawableAdapter
import com.progressterra.ipbandroidview.utils.SResult

class ChatDrawableFragment : BaseFragment() {
    private var _binding: FragmentChatDrawableLibBinding? = null
    private val binding: FragmentChatDrawableLibBinding
        get() = _binding!!

    override val vm by viewModels<ChatDrawableViewModel>()

    private val messageListAdapter = MessageWithDrawableAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatDrawableLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvMessages.adapter = messageListAdapter
    }

    private fun setupViewModel() {
        with(vm) {
            messagesList.observe(viewLifecycleOwner) {
                messageListAdapter.submitList(it.data) {
                    binding.rvMessages.smoothScrollToPosition(0)
                }
            }

            messageSendingStatus.observe(viewLifecycleOwner) {
                when (it) {
                    is SResult.Toast -> it.handleToastResult()
                    else -> kotlin.Unit
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getDialogInfo()
    }
}