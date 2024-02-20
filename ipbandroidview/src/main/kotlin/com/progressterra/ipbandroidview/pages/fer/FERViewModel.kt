package com.progressterra.ipbandroidview.pages.fer

import android.graphics.BitmapFactory
import androidx.camera.core.ImageProxy
import com.google.mediapipe.tasks.components.containers.Category
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbshared.Expression
import com.progressterra.ipbshared.FACSMultipliers
import com.progressterra.ipbshared.FERModule
import com.progressterra.ipbshared.FaceLandmarks
import java.io.File

fun List<Category>.toFaceLandmarks(): FaceLandmarks {
    val smileLeft = this[44]
    val smileRight = this[45]
    val browDownLeft = this[1]
    val browDownRight = this[2]
    val browInnerUp = this[3]
    val jawOpen = this[25]
    val eyeBlinkLeft = this[9]
    val eyeBlinkRight = this[10]
    val mouthClose = this[27]
    val mouthDimpleLeft = this[28]
    val mouthDimpleRight = this[29]
    val browOuterLeft = this[4]
    val browOuterRight = this[5]
    val eyeWideLeft = this[21]
    val eyeWideRight = this[22]
    val eyeSquintLeft = this[19]
    val eyeSquintRight = this[20]
    val mouthStretchLeft = this[46]
    val mouthStretchRight = this[47]
    val mouthFrownLeft = this[30]
    val mouthFrownRight = this[31]
    val mouthLowerDownLeft = this[34]
    val mouthLowerDownRight = this[35]
    val mouthPressLeft = this[36]
    val mouthPressRight = this[37]
    return FaceLandmarks(
        smileLeft = smileLeft.score(),
        smileRight = smileRight.score(),
        browDownLeft = browDownLeft.score(),
        browDownRight = browDownRight.score(),
        browInnerUp = browInnerUp.score(),
        jawOpen = jawOpen.score(),
        eyeBlinkLeft = eyeBlinkLeft.score(),
        eyeBlinkRight = eyeBlinkRight.score(),
        mouthClose = mouthClose.score(),
        mouthDimpleLeft = mouthDimpleLeft.score(),
        mouthDimpleRight = mouthDimpleRight.score(),
        browOuterLeft = browOuterLeft.score(),
        browOuterRight = browOuterRight.score(),
        eyeWideLeft = eyeWideLeft.score(),
        eyeWideRight = eyeWideRight.score(),
        eyeSquintLeft = eyeSquintLeft.score(),
        eyeSquintRight = eyeSquintRight.score(),
        mouthStretchLeft = mouthStretchLeft.score(),
        mouthStretchRight = mouthStretchRight.score(),
        mouthFrownLeft = mouthFrownLeft.score(),
        mouthFrownRight = mouthFrownRight.score(),
        mouthLowerDownLeft = mouthLowerDownLeft.score(),
        mouthLowerDownRight = mouthLowerDownRight.score(),
        mouthPressLeft = mouthPressLeft.score(),
        mouthPressRight = mouthPressRight.score()
    )
}

class FERViewModel(
    private val ferModule: FERModule,
    private val liveFLHelper: FaceLandmarkerHelper,
    private val imageFLHelper: FaceLandmarkerHelper
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
                val images = File(currentState.datasetDirPath).listFiles()?.map {
                    BitmapFactory.decodeFile(it.path)
                } ?: emptyList()
                val total = images.size
                val correct = images.sumOf {
                    val faceLandmarks =
                        imageFLHelper.detectImage(it)?.result?.faceBlendshapes()?.get()
                            ?.get(0)?.toFaceLandmarks()
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