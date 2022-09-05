package com.progressterra.ipbandroidview.ui.chat.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.ItemMessageLibBinding
import com.progressterra.ipbandroidview.databinding.ItemOwnerMessageLibBinding

internal class MessagesListAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(diffUtil) {

    val USER_MESSAGE = 0
    val OWNER_MESSAGE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        if (viewType == USER_MESSAGE) {
            val binding: ItemMessageLibBinding =
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.item_message_lib,
                    parent,
                    false
                )
            return MessageViewHolder(binding)
        } else {
            val binding: ItemOwnerMessageLibBinding =
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.item_owner_message_lib,
                    parent,
                    false
                )
            return OwnerMessageViewHolder(binding)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).idClient == UserData.clientInfo.idUnique) USER_MESSAGE else OWNER_MESSAGE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == USER_MESSAGE) {
            (holder as MessageViewHolder).bind(getItem(position))
        } else {
            (holder as OwnerMessageViewHolder).bind(getItem(position))
        }

    }
}

private val diffUtil = object : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.contentText == newItem.contentText
    }
}

internal class MessageViewHolder(var binding: ItemMessageLibBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(messageData: Message) {
        binding.message = messageData
    }
}

internal class OwnerMessageViewHolder(var binding: ItemOwnerMessageLibBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(messageData: Message) {
        binding.message = messageData
    }
}