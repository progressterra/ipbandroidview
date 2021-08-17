package com.progressterra.ipbandroidview.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.databinding.FragmentChatLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment

class ChatFragment : BaseFragment() {

    private var _binding: FragmentChatLibBinding? = null
    private val binding: FragmentChatLibBinding
        get() = _binding!!

    override val vm by viewModels<ChatViewModel>()

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

    }
}