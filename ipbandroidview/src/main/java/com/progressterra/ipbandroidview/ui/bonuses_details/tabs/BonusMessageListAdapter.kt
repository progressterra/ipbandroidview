package com.progressterra.android.ipbandroidview.bonuses_details.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusMessage
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.BonusMessageRecyclerItemBinding

class BonusMessageListAdapter(var bonusMessagesList: List<BonusMessage>) :
    Adapter<BonusMessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BonusMessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: BonusMessageRecyclerItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.bonus_message_recycler_item, parent, false);
        return BonusMessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BonusMessageViewHolder, position: Int) {
        holder.bind(bonusMessagesList[position])
    }

    override fun getItemCount(): Int {
        return bonusMessagesList.size
    }
}

class BonusMessageViewHolder(val binding: BonusMessageRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: BonusMessage) {
        binding.bonusMessage = item
    }
}