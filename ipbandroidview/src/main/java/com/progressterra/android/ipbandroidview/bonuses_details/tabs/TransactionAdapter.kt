package com.progressterra.android.ipbandroidview.bonuses_details.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.Transaction
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.TransactionRecyclerItemBinding

class TransactionAdapter(private var transactions: List<Transaction>) :
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

class TransactionViewHolder(var binding: TransactionRecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Transaction) {
        binding.trasaction = item

        /* //Тип операции
         action.text = when (item.typeOperation!!) {
             1-> context.getString(R.string.type_operation_spending)
             2 -> context.getString(R.string.type_operation_burning)
             3 -> context.getString(R.string.type_operation_charging)
             else -> ""
         }

         //Сумма операции
         if (item.quantity!! <= 0) {
             //Указание цвета текста
             //sum.setTextColor(ContextCompat.getColor(context, R.color.carnation))
             sum.text = String.format(
                 context.getString(R.string.negative_sum), kotlin.math.abs(item.quantity!!)
                     .toString()
             )
         } else {
             //Указание цвета текста
             //sum.setTextColor(ContextCompat.getColor(context, R.color.apple_green))
             sum.text = String.format(
                 context.getString(R.string.positive_sum), kotlin.math.abs(item.quantity!!)
                     .toString()
             )
         }*/
    }
}

