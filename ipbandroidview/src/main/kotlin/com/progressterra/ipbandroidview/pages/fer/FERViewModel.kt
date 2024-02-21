package com.progressterra.ipbandroidview.pages.fer

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.camera.core.ImageProxy
import com.progressterra.ipbandroidview.entities.Blendshape
import com.progressterra.ipbandroidview.entities.toFaceLandmarks
import com.progressterra.ipbandroidview.processes.FaceLandmarkerHelper
import com.progressterra.ipbandroidview.processes.media.FileExplorer
import com.progressterra.ipbandroidview.shared.log
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbshared.Expression
import com.progressterra.ipbshared.FACSMultipliers
import com.progressterra.ipbshared.FERModule

class FERViewModel(
    private val ferModule: FERModule,
    private val liveFLHelper: FaceLandmarkerHelper,
    private val imageFLHelper: FaceLandmarkerHelper,
    private val fileExplorer: FileExplorer
) : AbstractNonInputViewModel<FERState, Nothing>(), UseFERScreen,
    FaceLandmarkerHelper.LandmarkerListener {
    override fun createInitialState() = FERState()

    init {
        liveFLHelper.faceLandmarkerHelperListener = this
    }

    override fun onResults(
        resultBundle: FaceLandmarkerHelper.ResultBundle
    ) {
        val blandShapes = resultBundle.result.faceBlendshapes().get()[0]
        emitState { prevState ->
            prevState.copy(blendshapes = blandShapes.map {
                Blendshape(
                    number = it.index(), name = it.categoryName(), value = it.score()
                )
            }.sortedBy { it.number })
        }
    }

    override fun handle(event: FEREvent) {
        when (event) {
            is FEREvent.ChooseDataset -> emitState {
                it.copy(datasetDirPath = event.dirPath)
            }

            is FEREvent.ChooseTuneExpression -> emitState {
                it.copy(tuningExpression = event.expression)
            }
        }
    }

    @Suppress("USELESS_CAST")
    override fun handle(event: ButtonEvent) {
        when (event.id) {
            "tune" -> onBackground {
                val uri = Uri.parse(currentState.datasetDirPath)
                log("uri: $uri")
                val file = fileExplorer.fileForUri(uri, "TempDataset")
                log("dir exist: ${file.exists()} is dir: ${file.isDirectory} path: ${file.path}")
                val images = file.listFiles()?.map {
                    BitmapFactory.decodeFile(it.path)
                } ?: emptyList()
                val total = images.size
                val correct = images.sumOf {
                    val faceLandmarks =
                        imageFLHelper.detectImage(it)?.result?.faceBlendshapes()?.get()?.get(0)
                            ?.toFaceLandmarks()
                    val expression = ferModule.recognizeEmotion(
                        landmarks = faceLandmarks!!, multipliers = FACSMultipliers(
                            joy = if (currentState.tuningExpression == Expression.JOY) currentState.tuningMultiplier.text.toFloat() else 1f,
                            fear = if (currentState.tuningExpression == Expression.FEAR) currentState.tuningMultiplier.text.toFloat() else 1f,
                            disgust = if (currentState.tuningExpression == Expression.DISGUST) currentState.tuningMultiplier.text.toFloat() else 1f,
                            angry = if (currentState.tuningExpression == Expression.ANGRY) currentState.tuningMultiplier.text.toFloat() else 1f,
                            surprise = if (currentState.tuningExpression == Expression.SURPRISE) currentState.tuningMultiplier.text.toFloat() else 1f,
                            sadness = if (currentState.tuningExpression == Expression.SADNESS) currentState.tuningMultiplier.text.toFloat() else 1f
                        )
                    )
                    if (expression == currentState.tuningExpression) 1 else 0 as Int
                }
                emitState { it.copy(tuningResult = "correct $correct / total $total") }
            }

            "tuneJoy" -> emitState { it.copy(tuningExpression = Expression.JOY) }
            "tuneFear" -> emitState { it.copy(tuningExpression = Expression.FEAR) }
            "tuneDisgust" -> emitState { it.copy(tuningExpression = Expression.DISGUST) }
            "tuneAngry" -> emitState { it.copy(tuningExpression = Expression.ANGRY) }
            "tuneSurprise" -> emitState { it.copy(tuningExpression = Expression.SURPRISE) }
            "tuneSadness" -> emitState { it.copy(tuningExpression = Expression.SADNESS) }
        }
    }

    override fun handle(event: TextFieldEvent) {
        if (event is TextFieldEvent.TextChanged && event.id == "tuningMultiplier") {
            emitState {
                it.copy(
                    tuningMultiplier = it.tuningMultiplier.copy(text = event.text),
                    tuneButton = it.tuneButton.copy(enabled = event.text.isFloat())
                )
            }
        }
    }

    private fun String.isFloat() = try {
        toFloat()
        true
    } catch (e: Exception) {
        false
    }

    fun onResume() {
        if (liveFLHelper.isClose()) {
            liveFLHelper.setupFaceLandmarker()
        }
    }

    fun onPause() {
        liveFLHelper.clearFaceLandmarker()
    }

    fun analyzeLiveStream(image: ImageProxy) {
        liveFLHelper.detectLiveStream(
            imageProxy = image, isFrontCamera = true
        )
    }


    override fun onEmpty() {
    }

    override fun onError(error: String, errorCode: Int) {
    }
}