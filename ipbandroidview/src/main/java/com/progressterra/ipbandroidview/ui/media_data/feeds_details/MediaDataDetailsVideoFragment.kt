package com.progressterra.ipbandroidview.ui.media_data.feeds_details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.progressterra.ipbandroidview.databinding.DialogDetailFeedVideoLibBinding


class MediaDataDetailsVideoFragment : DialogFragment() {

    private val args: FeedsDetailsFragmentVideoArgs by navArgs()

    private var currentPlayPos: Long? = null

    private lateinit var binding: DialogDetailFeedVideoLibBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogDetailFeedVideoLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentPlayPos = savedInstanceState?.getLong("currentPlayPosition")

        setupVideoPlayer(args.url)

        binding.btnClose.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onPause() {
        super.onPause()
        binding.playerView.player?.stop()
        binding.playerView.player?.release()
    }

    private fun setupVideoPlayer(url: String) {

        val playerView = binding.playerView
        val player = SimpleExoPlayer.Builder(requireContext()).build()

        val mediaItem: MediaItem = MediaItem.fromUri(Uri.parse(url))

        player.setMediaItem(mediaItem)
        player.prepare()
        currentPlayPos?.let {
            player.seekTo(it)
        }
        player.play()

        playerView.player = player
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.playerView.player?.currentPosition?.let {
            outState.putLong("currentPlayPosition", it)
        }
        super.onSaveInstanceState(outState)
    }
}