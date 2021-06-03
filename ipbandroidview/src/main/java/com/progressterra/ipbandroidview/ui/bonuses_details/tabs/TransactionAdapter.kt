package com.progressterra.ipbandroidview.ui.bonuses_details.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.Transaction
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.ItemTransactionBinding

internal class TransactionAdapter(private var transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTransactionBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_transaction, parent, false);
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}

internal class TransactionViewHolder(var binding: ItemTransactionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Transaction) {
        binding.trasaction = item
    }
}

