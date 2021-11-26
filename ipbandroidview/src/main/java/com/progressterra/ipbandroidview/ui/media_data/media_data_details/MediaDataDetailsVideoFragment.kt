package com.progressterra.ipbandroidview.ui.media_data.media_data_details

import android.net.Uri
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.DialogDetailFeedVideoLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseBindingDialogFragment
import com.progressterra.ipbandroidview.ui.base.BaseBindingViewModel


class MediaDataDetailsVideoFragment :
    BaseBindingDialogFragment<DialogDetailFeedVideoLibBinding, BaseBindingViewModel>(
        R.layout.dialog_detail_feed_video_lib
    ) {

    private val args: MediaDataDetailsVideoFragmentArgs by navArgs()

    private var currentPlayPos: Long? = null


    override fun onInitBinding(
        binding: DialogDetailFeedVideoLibBinding,
        savedInstanceState: Bundle?
    ) {
        super.onInitBinding(binding, savedInstanceState)
        currentPlayPos = savedInstanceState?.getLong("currentPlayPosition")

        setupVideoPlayer(args.url)

        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onPause() {
        super.onPause()
        binding.playerView.player?.stop()
        binding.playerView.player?.release()
    }

    private fun setupVideoPlayer(url: String) {
        val player = SimpleExoPlayer.Builder(requireContext()).build()

        val mediaItem: MediaItem = MediaItem.fromUri(Uri.parse(url))

        player.setMediaItem(mediaItem)
        player.prepare()
        currentPlayPos?.let {
            player.seekTo(it)
        }
        player.play()

        binding.playerView.player = player
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.playerView.player?.currentPosition?.let {
            outState.putLong("currentPlayPosition", it)
        }
        super.onSaveInstanceState(outState)
    }
}