package com.progressterra.android.ipbandroidview.bonuses_details.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.Transaction
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.TransactionRecyclerItemBinding

internal class TransactionAdapter(private var transactions: List<Transaction>) :
    RecyclerView.Adapter<TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: TransactionRecyclerItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.transaction_recycler_item, parent, false);
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }
}

internal class TransactionViewHolder(var binding: TransactionRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Transaction) {
        binding.trasaction = item
    }
}

