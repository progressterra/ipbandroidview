package com.progressterra.ipbandroidview.ui.media_data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.progressterra.ipbandroidview.databinding.ItemMediaDataHtmlLibBinding
import com.progressterra.ipbandroidview.databinding.ItemMediaDataPdfLibBinding
import com.progressterra.ipbandroidview.databinding.ItemMediaDataVideoLibBinding
import com.progressterra.ipbandroidview.ui.media_data.models.ContentType
import com.progressterra.ipbandroidview.ui.media_data.models.MediaDataUi


class MediaDataListAdapter(
    private val onClick: (MediaDataUi) -> Unit
) :
    ListAdapter<MediaDataUi, RecyclerView.ViewHolder>(feedDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        when (viewType) {
            TEXT_VIEW_TYPE -> {
                val binding: ItemMediaDataHtmlLibBinding =
                    ItemMediaDataHtmlLibBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                return MediaDataHtmlViewHolder(binding)
            }
            VIDEO_VIEW_TYPE -> {
                val binding: ItemMediaDataVideoLibBinding =
                    ItemMediaDataVideoLibBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                return MediaDataVideoViewHolder(binding)
            }

            PDF_VIEW_TYPE -> {
                val binding: ItemMediaDataPdfLibBinding =
                    ItemMediaDataPdfLibBinding.inflate(
                        inflater,
                        parent,
                        false
                    )
                return MediaDataPdfViewHolder(binding)
            }

            else -> {
                throw error("Incorrect view type: $viewType")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)?.contentType) {
            ContentType.VIDEO -> VIDEO_VIEW_TYPE
            ContentType.HTML -> TEXT_VIEW_TYPE
            ContentType.PDF -> PDF_VIEW_TYPE
            ContentType.UNKNOWN -> error("Unknown content type")
            else -> error("Unknown content type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIDEO_VIEW_TYPE -> (holder as MediaDataVideoViewHolder).bind(
                getItem(position),
                onClick
            )
            TEXT_VIEW_TYPE -> (holder as MediaDataHtmlViewHolder).bind(
                getItem(position),
                onClick
            )
            PDF_VIEW_TYPE -> (holder as MediaDataPdfViewHolder).bind(
                getItem(position),
                onClick
            )
        }
    }

    companion object {
        const val TEXT_VIEW_TYPE = 0
        const val VIDEO_VIEW_TYPE = 1
        const val PDF_VIEW_TYPE = 2
    }

}

private val feedDiffUtil = object : DiffUtil.ItemCallback<MediaDataUi>() {
    override fun areItemsTheSame(oldItem: MediaDataUi, newItem: MediaDataUi): Boolean {
        return oldItem.idUnique == newItem.idUnique
    }

    override fun areContentsTheSame(oldItem: MediaDataUi, newItem: MediaDataUi): Boolean {
        return oldItem.previewText == newItem.previewText && oldItem.urlData == newItem.urlData
    }

}

class MediaDataHtmlViewHolder(var binding: ItemMediaDataHtmlLibBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MediaDataUi, onClick: (MediaDataUi) -> Unit) {
        binding.productSubInfo = item
        binding.btnShowMore.setOnClickListener {
            onClick(item)
        }
    }
}

class MediaDataVideoViewHolder(var binding: ItemMediaDataVideoLibBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MediaDataUi, onClick: (MediaDataUi) -> Unit) {

        // грузим превью видео
        Glide.with(binding.root.context)
            .load(item.urlData)
            .thumbnail(0.5f)
            .into(binding.ivPreview)

        binding.productSubInfo = item

        binding.ivPreview.setOnClickListener {
            onClick(item)
        }
    }
}

class MediaDataPdfViewHolder(var binding: ItemMediaDataPdfLibBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: MediaDataUi, onClick: (MediaDataUi) -> Unit) {

        binding.productSubInfo = item

        binding.btnShowMore.setOnClickListener {
            onClick(item)
        }
    }
}


