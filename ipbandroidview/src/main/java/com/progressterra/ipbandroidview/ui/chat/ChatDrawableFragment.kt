package com.progressterra.ipbandroidview.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.progressterra.ipbandroidview.databinding.FragmentChatDrawableLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.chat.utils.MessageWithDrawableAdapter
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.ui.adapters.animators.SlideInUpAnimator

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


        binding.rvMessages.itemAnimator = SlideInUpAnimator(AnticipateInterpolator()).apply {
            addDuration = MessageWithDrawableAdapter.ANIMATE_DURATION.div(2L)
            removeDuration = MessageWithDrawableAdapter.ANIMATE_DURATION.div(2L)
            changeDuration = MessageWithDrawableAdapter.ANIMATE_DURATION.div(2L)
        }
    }

    private fun setupViewModel() {
        with(vm) {
            messagesList.observe(viewLifecycleOwner) {
                messageListAdapter.submitList(it.data) {
                    val visiblePosition =
                        (binding.rvMessages.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()

                    visiblePosition?.let {
                        val height =
                            binding.rvMessages.findViewHolderForLayoutPosition(0)
                                ?.itemView?.height ?: 200

                        if (visiblePosition <= 0)
                            binding.rvMessages.smoothScrollBy(
                                0,
                                height.times(3),
                                AnticipateInterpolator(),
                                MessageWithDrawableAdapter.ANIMATE_DURATION
                            )
                    }
                }
            }

            messageSendingStatus.observe(viewLifecycleOwner) {
                when (it) {
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