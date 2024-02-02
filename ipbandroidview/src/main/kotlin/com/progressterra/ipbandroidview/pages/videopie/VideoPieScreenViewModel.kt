package com.progressterra.ipbandroidview.pages.videopie

import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import com.progressterra.ipbandroidview.processes.media.FileExplorer
import com.progressterra.ipbandroidview.shared.log
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

class VideoPieScreenViewModel(
    private val fileExplorer: FileExplorer
) : AbstractNonInputViewModel<VideoPieScreenState, Nothing>(), UseVideoPieScreen {

    override fun createInitialState() = VideoPieScreenState()

    override fun refresh() {
        val counter = AtomicInteger()
        currentState.toDownload.forEachIndexed { index, url ->
            viewModelScope.launch(Dispatchers.IO) {
                val fileName = "Video$index.mp4"
                fileExplorer.downloadFile(url = url,
                    fileName = fileName,
                    progress = { progress ->
                        if (progress == 100F) {
                            if (counter.incrementAndGet() == currentState.toDownload.size) {
                                val mediaItems = List(currentState.toDownload.size) { mapIndex ->
                                    val tempFileName = "Video$mapIndex.mp4"
                                    val file = fileExplorer.file(tempFileName)
                                    val uri = fileExplorer.uriForFile(file)
                                    log("""
                                        created MediaItem with:
                                        fileName: $tempFileName
                                        file path: ${file.absoluteFile}
                                        uri: $uri
                                        """.trimIndent()
                                    )
                                    MediaItem.fromUri(uri)
                                }
                                emitState {
                                    it.copy(
                                        screen = it.screen.copy(state = ScreenState.SUCCESS),
                                        current = mediaItems.first(),
                                        next = (mediaItems - mediaItems.first()).random(),
                                        downloaded = mediaItems
                                    )
                                }
                                log("initial refreshed state $currentState")
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
        log("next")
    }

    private fun next() {
        log("old state: $currentState")
        emitState {
            it.copy(
                current = it.next,
                next = (it.downloaded - it.next).random()
            )
        }
        log("new state: $currentState")
    }

    override fun handle(event: VideoPieScreenEvent) = Unit

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}