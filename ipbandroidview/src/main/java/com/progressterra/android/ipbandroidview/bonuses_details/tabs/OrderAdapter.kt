package com.progressterra.android.ipbandroidview.bonuses_details.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.Purchase
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.OrderRecyclerItemBinding

internal class OrderAdapter(
    var orders: List<Purchase>,
    var onPurchaseClickListener: OnPurchaseClickListener?
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: OrderRecyclerItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.order_recycler_item, parent, false);
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(orders[position])
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    inner class OrderViewHolder(val binding: OrderRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Purchase) {
            binding.purchase = item
            binding.root.setOnClickListener {
                onPurchaseClickListener?.openDetailPurchase(item)
            }
        }
    }

}



