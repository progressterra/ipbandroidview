package com.progressterra.ipbandroidview.ui.chat

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentChatDrawableLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingFragment
import com.progressterra.ipbandroidview.ui.chat.utils.MessageWithDrawableAdapter
import com.progressterra.ipbandroidview.ui.chat.utils.MessagesAnimator
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChatDrawableFragment :
    BaseBindingFragment<FragmentChatDrawableLibBinding, ChatDrawableViewModel>(
        R.layout.fragment_chat_drawable_lib
    ) {

    private val args by navArgs<ChatDrawableFragmentArgs>()

    override val vm by viewModel<ChatDrawableViewModel>(
        parameters = {
            parametersOf(args.idEnterprise, args.imageUrl, args.descriptionDialog)
        }
    )

    private val messageListAdapter = MessageWithDrawableAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvMessages.adapter = messageListAdapter


        binding.rvMessages.itemAnimator = MessagesAnimator(LinearInterpolator()).apply {
            addDuration = MessageWithDrawableAdapter.ANIMATE_DURATION.div(2L)
            removeDuration = MessageWithDrawableAdapter.ANIMATE_DURATION.div(2L)
            changeDuration = MessageWithDrawableAdapter.ANIMATE_DURATION.div(2L)
        }
    }

    private fun setupViewModel() {
        with(vm) {
            messagesList.observe(viewLifecycleOwner) {
                messageListAdapter.submitList(it.data) {
                    val visiblePosition = (binding.rvMessages.layoutManager as? LinearLayoutManager)
                        ?.findFirstVisibleItemPosition()
                    val positionsToScroll =
                        if (visiblePosition != null && visiblePosition != 0) visiblePosition else 1

                    val height =
                        binding.rvMessages.findViewHolderForLayoutPosition(0)
                            ?.itemView?.height ?: 200

                    binding.rvMessages.smoothScrollBy(
                        0,
                        height.times(positionsToScroll).times(3),
                        LinearInterpolator(),
                        MessageWithDrawableAdapter.ANIMATE_DURATION
                    )

                }
            }

            messageSendingStatus.observe(viewLifecycleOwner) {
                it.defaultResultHandling()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getDialogInfo()
    }
}