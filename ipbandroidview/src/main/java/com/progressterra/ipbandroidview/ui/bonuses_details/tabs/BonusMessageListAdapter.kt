package com.progressterra.ipbandroidview.ui.bonuses_details.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.models.BonusMessage
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.ItemBonusMessageLibBinding

class BonusMessageListAdapter(var bonusMessagesList: List<BonusMessage>) :
    Adapter<BonusMessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BonusMessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemBonusMessageLibBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_bonus_message_lib, parent, false)
        return BonusMessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BonusMessageViewHolder, position: Int) {
        holder.bind(bonusMessagesList[position])
    }

    override fun getItemCount(): Int {
        return bonusMessagesList.size
    }
}

class BonusMessageViewHolder(val binding: ItemBonusMessageLibBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BonusMessage) {
        binding.bonusMessage = item
    }
}