package com.progressterra.ipbandroidview.ui.bonuses_movements.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.ItemBonusesMovementsDateLibBinding
import com.progressterra.ipbandroidview.databinding.ItemBonusesMovementsLibBinding


const val DATE_TYPE = 0
const val TRANSACTION_TYPE = 1

internal class BonusesMovementsAdapter(var items: List<TransactionWithDate>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == TRANSACTION_TYPE) {
            return QuantityViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.item_bonuses_movements_lib,
                    parent,
                    false
                )
            )
        } else {
            return DateViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.item_bonuses_movements_date_lib,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TRANSACTION_TYPE) {
            (holder as QuantityViewHolder).bind(items[position].transaction)

        } else if (getItemViewType(position) == DATE_TYPE) {
            (holder as DateViewHolder).bind(
                items[position].dateType ?: ""
            )
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].dateType != null) {
            DATE_TYPE
        } else
            TRANSACTION_TYPE
    }

    inner class QuantityViewHolder(var binding: ItemBonusesMovementsLibBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Transaction?) {
            if (item == null) return

            binding.item = item

            if (item.quantity >= 0) {
                binding.ivTransactionStatus.setImageResource(R.drawable.ic_transaction_positive)
            } else {
                binding.ivTransactionStatus.setImageResource(R.drawable.ic_transaction_negative)
            }
        }
    }

    inner class DateViewHolder(var binding: ItemBonusesMovementsDateLibBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(date: String) {
            binding.tvTransactionsDate.text = date
        }

    }
}