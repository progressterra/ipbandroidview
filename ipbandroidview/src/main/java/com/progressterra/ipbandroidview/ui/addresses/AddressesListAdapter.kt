package com.progressterra.ipbandroidview.ui.addresses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.databinding.ItemAddressLibBinding
import com.progressterra.ipbandroidview.ui.addresses.models.AddressUI


class AddressesListAdapter(
    var onClick: (address: AddressUI) -> Unit
) : ListAdapter<AddressUI, AddressViewHolder>(addressDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AddressViewHolder(ItemAddressLibBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}

class AddressViewHolder(var binding: ItemAddressLibBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(addressUI: AddressUI, onClick: (address: AddressUI) -> Unit) {
        binding.item = addressUI
        binding.root.setOnClickListener {
            onClick(addressUI)
        }
    }
}

private val addressDiffUtil = object : DiffUtil.ItemCallback<AddressUI>() {
    override fun areItemsTheSame(oldItem: AddressUI, newItem: AddressUI): Boolean {
        return oldItem.idUnique == newItem.idUnique
    }

    override fun areContentsTheSame(oldItem: AddressUI, newItem: AddressUI): Boolean {
        return oldItem.getFullAddress() == newItem.getFullAddress() && oldItem.isDefaultShippingAddress == newItem.isDefaultShippingAddress

    }
}