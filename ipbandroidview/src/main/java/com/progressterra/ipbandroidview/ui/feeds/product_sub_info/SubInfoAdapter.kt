package com.progressterra.ipbandroidview.ui.feeds.product_sub_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.ItemProductSubInfoHtmlBinding
import com.progressterra.ipbandroidview.databinding.ItemProductSubInfoVideoBinding
import com.progressterra.ipbandroidview.ui.feeds.FeedUiModel


const val TEXT_VIEW_TYPE = 0
const val VIDEO_VIEW_TYPE = 1

class SubInfoAdapter(
    var items: List<FeedUiModel>,
    private var onSubInfoItemClickListener: OnSubInfoItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            TEXT_VIEW_TYPE -> {
                val binding: ItemProductSubInfoHtmlBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_product_sub_info_html,
                        parent,
                        false
                    )
                return SubInfoTextViewHolder(binding)
            }
            VIDEO_VIEW_TYPE -> {
                val binding: ItemProductSubInfoVideoBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_product_sub_info_video,
                        parent,
                        false
                    )
                return SubInfoVideoViewHolder(binding)
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        when (items[position].contentType) {
            1 -> return VIDEO_VIEW_TYPE
            3 -> return TEXT_VIEW_TYPE
        }

        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIDEO_VIEW_TYPE -> (holder as SubInfoVideoViewHolder).bind(
                items[position],
                onSubInfoItemClickListener
            )
            TEXT_VIEW_TYPE -> (holder as SubInfoTextViewHolder).bind(
                items[position],
                onSubInfoItemClickListener
            )
        }
    }

}

class SubInfoTextViewHolder(var binding: ItemProductSubInfoHtmlBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FeedUiModel, onSubInfoItemClickListener: OnSubInfoItemClickListener) {
        binding.productSubInfo = item
        binding.btnShowMore.setOnClickListener {
            onSubInfoItemClickListener.onClick(item)
        }
    }
}

class SubInfoVideoViewHolder(var binding: ItemProductSubInfoVideoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FeedUiModel, onSubInfoItemClickListener: OnSubInfoItemClickListener) {

        // грузим превью видео
        Glide.with(binding.root.context)
            .load(item.urlData)
            .into(binding.ivPreview)
        binding.productSubInfo = item

        binding.ivPreview.setOnClickListener {
            onSubInfoItemClickListener.onClick(item)
        }
    }
}


