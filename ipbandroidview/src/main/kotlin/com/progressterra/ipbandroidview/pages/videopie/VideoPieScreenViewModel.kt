package com.progressterra.ipbandroidview.pages.videopie

import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import com.progressterra.ipbandroidview.processes.media.FileExplorer
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VideoPieScreenViewModel(
    private val fileExplorer: FileExplorer
) : AbstractNonInputViewModel<VideoPieScreenState, Nothing>(), UseVideoPieScreen {

    override fun createInitialState() = VideoPieScreenState()

    override fun refresh() {
        currentState.toDownload.forEachIndexed { index, url ->
            var counter = 0
            viewModelScope.launch(Dispatchers.IO) {
                fileExplorer.downloadFile(url = url,
                    fileName = "Video$index.mp4",
                    progress = { progress ->
                        if (progress == 1F) {
                            counter++
                            if (counter == currentState.toDownload.size) {
                                val mediaItems = List(currentState.toDownload.size) { index ->
                                    MediaItem.fromUri(fileExplorer.uriForFile(fileExplorer.file("Video$index.mp4")))
                                }
                                emitState {
                                    it.copy(
                                        screen = it.screen.copy(state = ScreenState.SUCCESS),
                                        current = mediaItems.firstOrNull() ?: MediaItem.EMPTY,
                                        downloaded = mediaItems
                                    )
                                }
                            }
                        }
                    },
                    handleException = {
                        emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                    })
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        next()
    }

    private fun next() {
        emitState {
            it.copy(
                current = it.next,
                next = it.downloaded.random()
            )
        }
    }

    override fun handle(event: VideoPieScreenEvent) = Unit

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}