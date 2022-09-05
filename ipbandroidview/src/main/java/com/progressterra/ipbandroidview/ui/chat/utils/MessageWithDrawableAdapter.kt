package com.progressterra.ipbandroidview.ui.chat.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidapi.user.UserData
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.ItemMessageDateBinding
import com.progressterra.ipbandroidview.databinding.ItemMessageSupportDrawableLibBinding
import com.progressterra.ipbandroidview.databinding.ItemMessageUserDrawableLibBinding

open class MessageWithDrawableAdapter :
    ListAdapter<MessageWithDateUI, RecyclerView.ViewHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            USER_MESSAGE -> {
                val binding: ItemMessageUserDrawableLibBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_message_user_drawable_lib,
                        parent,
                        false
                    )
                return UserMessageViewHolder(binding)
            }
            PARTNER_MESSAGE -> {
                val binding: ItemMessageSupportDrawableLibBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_message_support_drawable_lib,
                        parent,
                        false
                    )
                return PartnerMessageViewHolder(binding)
            }
            DATE_OF_MESSAGE -> {
                val binding: ItemMessageDateBinding =
                    DataBindingUtil.inflate(inflater, R.layout.item_message_date, parent, false)
                return DateViewHolder(binding)
            }
            else -> throw IllegalStateException("Incorrect view type: $viewType")
        }
    }

    /**
     * Если айди клиента в сообщении совпадает с текущим клиентским айди => сообщение от текущего пользователя
     * Если айди клиента в сообщении не совпадает с текущим клиентским айди => сообщение от партнера
     * Если в объекте указан не пустой день отправки сообщения => отрисовываем как айтем даты
     */
    override fun getItemViewType(position: Int): Int {
        return if (getItem(position)?.dayOfMessageSending != null) {
            DATE_OF_MESSAGE
        } else if (getItem(position).message != null && getItem(position).message?.idClient == UserData.clientInfo.idUnique) {
            USER_MESSAGE
        } else if (getItem(position).message != null && getItem(position).message?.idClient != UserData.clientInfo.idUnique) {
            PARTNER_MESSAGE
        } else -1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.animateFade()
        when (getItemViewType(position)) {
            DATE_OF_MESSAGE -> {
                (holder as DateViewHolder).bind(getItem(position).dayOfMessageSending)
            }
            USER_MESSAGE -> {
                (holder as UserMessageViewHolder).bind(getItem(position).message)
            }
            PARTNER_MESSAGE -> {
                (holder as PartnerMessageViewHolder).bind(getItem(position).message)
            }
        }
    }

    companion object {
        const val USER_MESSAGE = 0
        const val PARTNER_MESSAGE = 1
        const val DATE_OF_MESSAGE = 2
        const val ANIMATE_DURATION = 400
    }

    private fun View.animateFade() {
        alpha = 0f
        translationY = 400f
        scaleY = 0.5f
        scaleX = 0.5f
        animate().apply {
            alpha(1f)
            translationY(0f)
            scaleX(1f)
            scaleY(1f)
            duration = ANIMATE_DURATION.toLong()
        }
    }
}

class UserMessageViewHolder(private val binding: ItemMessageUserDrawableLibBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message?) {
        binding.message = message
    }
}

class PartnerMessageViewHolder(private val binding: ItemMessageSupportDrawableLibBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(message: Message?) {
        binding.message = message
    }
}


class DateViewHolder(private val binding: ItemMessageDateBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(date: String?) {
        binding.tvMessageDate.text = date ?: ""
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<MessageWithDateUI>() {
    override fun areItemsTheSame(oldItem: MessageWithDateUI, newItem: MessageWithDateUI): Boolean {
        return oldItem.message?.idUnique == newItem.message?.idUnique
    }

    override fun areContentsTheSame(
        oldItem: MessageWithDateUI,
        newItem: MessageWithDateUI
    ): Boolean {
        return oldItem.message?.contentText == newItem.message?.contentText
                && oldItem.message?.time == newItem.message?.time
    }
}