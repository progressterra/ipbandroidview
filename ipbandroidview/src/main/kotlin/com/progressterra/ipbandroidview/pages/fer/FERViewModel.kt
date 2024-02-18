package com.progressterra.ipbandroidview.pages.fer

import android.util.Log
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbshared.FERModule
import com.progressterra.ipbshared.FaceLandmarks

class FERViewModel : AbstractNonInputViewModel<FERState, Nothing>(), UseFERScreen, FaceLandmarkerHelper.LandmarkerListener {
    override fun createInitialState() = FERState()
    private val ferModule = FERModule()

    override fun onResults(
        resultBundle: FaceLandmarkerHelper.ResultBundle
    ) {

            val blandShapes = resultBundle.result.faceBlendshapes().get()[0]
            val smileLeft = blandShapes.get(44)
            val smileRight = blandShapes.get(45)
            val frownLeft = blandShapes.get(30)
            val frownRight = blandShapes.get(31)
            val browDownLeft = blandShapes.get(1)
            val browDownRight = blandShapes.get(2)
            val browInnerUp = blandShapes.get(3)
            val jawOpen = blandShapes.get(25)
            val eyeBlinkLeft = blandShapes.get(9)
            val eyeBlinkRight = blandShapes.get(10)
            val mouthClose = blandShapes.get(27)
            val cheekSquintLeft = blandShapes.get(7)
            val cheekSquintRight = blandShapes.get(8)
            val mouthDimpleLeft = blandShapes.get(28)
            val mouthDimpleRight = blandShapes.get(29)
            val browOuterLeft = blandShapes.get(4)
            val browOuterRight = blandShapes.get(5)
            val eyeWideLeft = blandShapes.get(21)
            val eyeWideRight = blandShapes.get(22)
            val eyeSquintLeft = blandShapes.get(19)
            val eyeSquintRight = blandShapes.get(20)
            val mouthStretchLeft = blandShapes.get(46)
            val mouthStretchRight = blandShapes.get(47)
            val noseSneerLeft = blandShapes.get(50)
            val noseSneerRight = blandShapes.get(51)
            val mouthFrownLeft = blandShapes.get(30)
            val mouthFrownRight = blandShapes.get(31)
            val mouthLowerDownLeft = blandShapes.get(34)
            val mouthLowerDownRight = blandShapes.get(35)
            val mouthPressLeft = blandShapes.get(36)
            val mouthPressRight = blandShapes.get(37)
            val landmarks = FaceLandmarks(
                smileLeft = smileLeft.score(),
                smileRight = smileRight.score(),
                frownLeft = frownLeft.score(),
                frownRight = frownRight.score(),
                browDownLeft = browDownLeft.score(),
                browDownRight = browDownRight.score(),
                browInnerUp = browInnerUp.score(),
                jawOpen = jawOpen.score(),
                eyeBlinkLeft = eyeBlinkLeft.score(),
                eyeBlinkRight = eyeBlinkRight.score(),
                mouthClose = mouthClose.score(),
                cheekSquintLeft = cheekSquintLeft.score(),
                cheekSquintRight = cheekSquintRight.score(),
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
                noseSneerLeft = noseSneerLeft.score(),
                noseSneerRight = noseSneerRight.score(),
                mouthFrownLeft = mouthFrownLeft.score(),
                mouthFrownRight = mouthFrownRight.score(),
                mouthLowerDownLeft = mouthLowerDownLeft.score(),
                mouthLowerDownRight = mouthLowerDownRight.score(),
                mouthPressLeft = mouthPressLeft.score(),
                mouthPressRight = mouthPressRight.score()
            )
            val emotion = ferModule.recognizeEmotion(landmarks)
            Log.d("FER", "emotions: ${ferModule.printEmotions(landmarks)}")
    }

    override fun onEmpty() {
    }

    override fun onError(error: String, errorCode: Int) {
    }
}