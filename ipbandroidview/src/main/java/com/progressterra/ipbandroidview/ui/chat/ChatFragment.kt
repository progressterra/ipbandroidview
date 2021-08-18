package com.progressterra.ipbandroidview.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.databinding.FragmentChatLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.chat.utils.MessagesListAdapter
import com.progressterra.ipbandroidview.utils.SResult

class ChatFragment : BaseFragment() {

    private var _binding: FragmentChatLibBinding? = null
    private val binding: FragmentChatLibBinding
        get() = _binding!!

    override val vm by viewModels<ChatViewModel>()

    private val messageListAdapter: MessagesListAdapter = MessagesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatLibBinding.inflate(inflater, container, false)
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
                messageListAdapter.submitList(it.data)
            }

            messageSendingStatus.observe(viewLifecycleOwner) {
                when (it) {
                    is SResult.Completed -> {
                        binding.rvMessages.smoothScrollToPosition(0)
                    }
                    is SResult.Toast -> it.handleToastResult()
                    else -> Unit
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getDialogInfo()
    }
}