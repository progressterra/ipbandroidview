package com.progressterra.ipbandroidview.ui.user_inviting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.progressterra.ipbandroidview.databinding.ItemContactLibBinding
import com.progressterra.ipbandroidview.ui.user_inviting.models.ContactUi

internal class ContactsAdapter :
    ListAdapter<ContactUi, ContactViewHolder>(diffUtil) {

    var onItemSelected: ((ContactUi) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactLibBinding.inflate(inflater, parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position), onItemSelected)
    }

    override fun onViewRecycled(holder: ContactViewHolder) {
        super.onViewRecycled(holder)
        holder.binding.cbSelectContact.isSelected = false
    }
}

private val diffUtil = object : DiffUtil.ItemCallback<ContactUi>() {
    override fun areItemsTheSame(oldItem: ContactUi, newItem: ContactUi): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ContactUi, newItem: ContactUi): Boolean {
        return oldItem == newItem
    }
}

internal class ContactViewHolder(
    var binding: ItemContactLibBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(
        contactUi: ContactUi, onItemSelected: ((ContactUi) -> Unit)?
    ) {
        binding.item = contactUi
        binding.cbSelectContact.setOnCheckedChangeListener(null)
        binding.cbSelectContact.isChecked = contactUi.isSelected

        binding.cbSelectContact.setOnCheckedChangeListener { buttonView, isChecked ->
            contactUi.isSelected = isChecked
            onItemSelected?.invoke(contactUi)
        }
    }
}