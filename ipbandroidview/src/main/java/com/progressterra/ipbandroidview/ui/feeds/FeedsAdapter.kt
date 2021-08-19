package com.progressterra.ipbandroidview.ui.feeds.product_sub_info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.ItemFeedHtmlLibBinding
import com.progressterra.ipbandroidview.databinding.ItemFeedVideoLibBinding

import com.progressterra.ipbandroidview.ui.feeds.models.ContentType
import com.progressterra.ipbandroidview.ui.feeds.models.FeedUi


const val TEXT_VIEW_TYPE = 0
const val VIDEO_VIEW_TYPE = 1

class FeedsAdapter(
    private val onClick: (FeedUi) -> Unit
) :
    ListAdapter<FeedUi, RecyclerView.ViewHolder>(feedDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            TEXT_VIEW_TYPE -> {
                val binding: ItemFeedHtmlLibBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_feed_html_lib,
                        parent,
                        false
                    )
                return FeedHtmlViewHolder(binding)
            }
            VIDEO_VIEW_TYPE -> {
                val binding: ItemFeedVideoLibBinding =
                    DataBindingUtil.inflate(
                        inflater,
                        R.layout.item_feed_video_lib,
                        parent,
                        false
                    )
                return FeedVideoViewHolder(binding)
            }
            else -> {
                throw IllegalStateException("Incorrect view type: $viewType")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).contentType) {
            ContentType.VIDEO -> VIDEO_VIEW_TYPE
            ContentType.HTML -> TEXT_VIEW_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIDEO_VIEW_TYPE -> (holder as FeedVideoViewHolder).bind(
                getItem(position),
                onClick
            )
            TEXT_VIEW_TYPE -> (holder as FeedHtmlViewHolder).bind(
                getItem(position),
                onClick
            )
        }
    }

}

private val feedDiffUtil = object : DiffUtil.ItemCallback<FeedUi>() {
    override fun areItemsTheSame(oldItem: FeedUi, newItem: FeedUi): Boolean {
        return oldItem.idUnique == newItem.idUnique
    }

    override fun areContentsTheSame(oldItem: FeedUi, newItem: FeedUi): Boolean {
        return oldItem.previewText == newItem.previewText && oldItem.urlData == newItem.urlData
    }

}

class FeedHtmlViewHolder(var binding: ItemFeedHtmlLibBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FeedUi, onClick: (FeedUi) -> Unit) {
        binding.productSubInfo = item
        binding.btnShowMore.setOnClickListener {
            onClick(item)
        }
    }
}

class FeedVideoViewHolder(var binding: ItemFeedVideoLibBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FeedUi, onClick: (FeedUi) -> Unit) {

        // грузим превью видео
        Glide.with(binding.root.context)
            .load(item.urlData)
            .into(binding.ivPreview)
        binding.productSubInfo = item

        binding.ivPreview.setOnClickListener {
            onClick(item)
        }
    }
}


