package com.progressterra.ipbandroidview.ui.bonus_movement_mine.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.ItemTransactionDateBinding
import com.progressterra.ipbandroidview.databinding.ItemTransationBinding


const val DATE_TYPE = 0
const val TRANSACTION_TYPE = 1

class TransactionsAdapter(var items: List<TransactionWithDate>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == TRANSACTION_TYPE) {
            return TransactionsViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.item_transation,
                    parent,
                    false
                )
            )
        } else {
            return TransactionsDateViewHolder(
                DataBindingUtil.inflate(
                    inflater,
                    R.layout.item_transaction_date,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TRANSACTION_TYPE) {
            (holder as TransactionsViewHolder).bind(items[position].transaction)

        } else if (getItemViewType(position) == DATE_TYPE) {
            (holder as TransactionsDateViewHolder).bind(
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
}

class TransactionsViewHolder(var binding: ItemTransationBinding) :
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

class TransactionsDateViewHolder(var binding: ItemTransactionDateBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(date: String) {
        binding.tvTransactionsDate.text = date
    }

}