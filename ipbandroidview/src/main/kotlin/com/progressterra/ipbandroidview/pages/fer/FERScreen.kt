package com.progressterra.ipbandroidview.pages.fer

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.progressterra.ipbandroidview.shared.ui.OnLifecycleEvent
import com.progressterra.ipbandroidview.shared.ui.Tabs
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalFoundationApi::class, ExperimentalPermissionsApi::class)
@Composable
fun FERScreen(
    modifier: Modifier,
    viewModel: FERViewModel
) {
    val state = viewModel.state.collectAsState().value
    val pagerState = rememberPagerState { 2 }
    val scope = rememberCoroutineScope()
    val backgroundExecutor = remember { Executors.newSingleThreadExecutor() }
    var faceLandmarkerHelper: FaceLandmarkerHelper? = remember { null }
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController =
        remember { LifecycleCameraController(context) }
    LaunchedEffect(Unit) {
        backgroundExecutor.execute {
            faceLandmarkerHelper = FaceLandmarkerHelper(
                context = context,
                runningMode = RunningMode.LIVE_STREAM,
                faceLandmarkerHelperListener = viewModel
            )
        }
    }
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                backgroundExecutor.execute {
                    if (faceLandmarkerHelper?.isClose() == true) {
                        faceLandmarkerHelper?.setupFaceLandmarker()
                    }
                }
            }

            Lifecycle.Event.ON_PAUSE -> {
                if(faceLandmarkerHelper != null) {
                    backgroundExecutor.execute { faceLandmarkerHelper!!.clearFaceLandmarker() }
                }
            }

            Lifecycle.Event.ON_DESTROY -> {
                backgroundExecutor.shutdown()
                backgroundExecutor.awaitTermination(
                    Long.MAX_VALUE, TimeUnit.NANOSECONDS
                )
            }

            else -> Unit
        }
    }
    ThemedLayout(modifier = modifier, topBar = {
        Tabs(
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
            tabs = listOf(
                "Blendshapes",
                "Finetuning"
            ),
            currentIndex = pagerState.currentPage,
            onTabClicked = { scope.launch { pagerState.animateScrollToPage(it) } }
        )
    }) { _, _ ->
        HorizontalPager(
            state = pagerState,
            pageSpacing = 20.dp,
            verticalAlignment = Alignment.Top
        ) { page ->
            if (page == 0) {
                if (cameraPermissionState.status.isGranted) {
                    AndroidView(
                        modifier = Modifier
                            .fillMaxHeight().aspectRatio(1f),
                        factory = { context ->
                            PreviewView(context).apply {
                                setBackgroundColor(Color.White.toArgb())
                                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                                scaleType = PreviewView.ScaleType.FILL_START
                                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                            }.also { previewView ->
                                previewView.controller = cameraController
                                cameraController.bindToLifecycle(lifecycleOwner)
                            }
                        },
                        onRelease = {
                            cameraController.unbind()
                        }
                    )
                } else {
                    cameraPermissionState.launchPermissionRequest()
                }
            } else if (page == 1) {
                Text(text = "Second page")
            }
        }
    }
}

