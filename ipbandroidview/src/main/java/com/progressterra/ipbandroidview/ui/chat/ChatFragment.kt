package com.progressterra.ipbandroidview.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.databinding.FragmentChatLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.chat.utils.MessagesListAdapter
import com.progressterra.ipbandroidview.utils.ScreenState

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
        setupListeners()
    }


    private fun setupListeners() {
        binding.ivSend.setOnClickListener {
            vm.sendMessage(binding.etInputMessage.text.toString())
        }
    }

    private fun setupView() {
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        val params: ViewGroup.LayoutParams = binding.rvMessages.layoutParams
        params.height = 0
        binding.rvMessages.layoutParams = params

        binding.rvMessages.adapter = messageListAdapter
    }

    private fun setupViewModel() {
        with(vm) {
            messageList.observe(viewLifecycleOwner) {
                messageListAdapter.submitList(it.reversed())
            }

            messageSandingStatus.observe(viewLifecycleOwner) {
                when (it) {
                    ScreenState.DEFAULT -> {
                        binding.etInputMessage.setText("")
                        binding.rvMessages.scrollToPosition(0)
                    }
                    ScreenState.ERROR -> Toast.makeText(
                        requireContext(),
                        "Ошибка при отправке сообщения",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> {
                    }
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        view?.requestLayout()
        vm.getDialogInfo()
    }
}