package com.progressterra.ipbandroidview.pages.fer

import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.darkrockstudios.libraries.mpfilepicker.DirectoryPicker
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.OnLifecycleEvent
import com.progressterra.ipbandroidview.shared.ui.Tabs
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.button.TextButton
import com.progressterra.ipbandroidview.shared.ui.button.UseButton
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField
import com.progressterra.ipbshared.Expression
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
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraProvider: ProcessCameraProvider =
        remember { ProcessCameraProvider.getInstance(context).get() }
    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                backgroundExecutor.execute {
                    viewModel.onResume()
                }
            }

            Lifecycle.Event.ON_PAUSE -> {
                backgroundExecutor.execute {
                    viewModel.onPause()
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
                "BS",
                "FACS FT"
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AndroidView(
                            modifier = Modifier.size(150.dp, 200.dp),
                            factory = { context ->
                                PreviewView(context).apply {
                                    setBackgroundColor(Color.White.toArgb())
                                    scaleType = PreviewView.ScaleType.FILL_START
                                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                                }.also { previewView ->
                                    previewView.post {
                                        val cameraSelector =
                                            CameraSelector.Builder()
                                                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                                                .build()
                                        val preview = Preview.Builder()
                                            .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                                            .setTargetRotation(previewView.display.rotation)
                                            .build()
                                        val imageAnalyzer =
                                            ImageAnalysis.Builder()
                                                .setTargetAspectRatio(AspectRatio.RATIO_4_3)
                                                .setTargetRotation(previewView.display.rotation)
                                                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                                                .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
                                                .build()
                                                .also {
                                                    it.setAnalyzer(backgroundExecutor) { image ->
                                                        viewModel.analyzeLiveStream(image)
                                                    }
                                                }
                                        cameraProvider.unbindAll()
                                        cameraProvider.bindToLifecycle(
                                            lifecycleOwner, cameraSelector, preview, imageAnalyzer
                                        )
                                        preview.setSurfaceProvider(previewView.surfaceProvider)
                                    }
                                }
                            },
                            onRelease = {
                                cameraProvider.unbindAll()
                            }
                        )
                        LazyVerticalGrid(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            columns = GridCells.Fixed(2)
                        ) {
                            items(state.blendshapes) {
                                BrushedText(
                                    text = it.toString(),
                                    style = IpbTheme.typography.caption2,
                                    tint = IpbTheme.colors.textPrimary.asBrush()
                                )
                            }
                        }
                    }
                } else {
                    cameraPermissionState.launchPermissionRequest()
                }
            } else if (page == 1) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    var showDirPicker by remember { mutableStateOf(false) }
                    DirectoryPicker(showDirPicker) { path ->
                        showDirPicker = false
                        path?.let { viewModel.handle(FEREvent.ChooseDataset(path)) }
                    }
                    BrushedText(
                        text = "Dataset for tuning: ${state.datasetDirPath}",
                        style = IpbTheme.typography.body,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        state = ButtonState(),
                        title = "Choose dataset",
                        useComponent = object : UseButton {
                            override fun handle(event: ButtonEvent) {
                                showDirPicker = true
                            }
                        })
                    BrushedText(
                        text = "Expression for tuning: ${state.tuningExpression.emoji}",
                        style = IpbTheme.typography.body,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        TextButton(
                            state = state.tuneJoyButton,
                            title = Expression.JOY.emoji,
                            useComponent = viewModel,
                            isTiny = true
                        )
                        TextButton(
                            state = state.tuneFearButton,
                            title = Expression.FEAR.emoji,
                            useComponent = viewModel,
                            isTiny = true
                        )
                        TextButton(
                            state = state.tuneAngryButton,
                            title = Expression.ANGRY.emoji,
                            useComponent = viewModel,
                            isTiny = true
                        )
                        TextButton(
                            state = state.tuneSadnessButton,
                            title = Expression.SADNESS.emoji,
                            useComponent = viewModel,
                            isTiny = true
                        )
                        TextButton(
                            state = state.tuneDisgustButton,
                            title = Expression.DISGUST.emoji,
                            useComponent = viewModel,
                            isTiny = true
                        )
                        TextButton(
                            state = state.tuneSurpriseButton,
                            title = Expression.SURPRISE.emoji,
                            useComponent = viewModel,
                            isTiny = true
                        )
                    }
                    BrushedText(
                        text = "Tuning results: ${state.tuningResult}",
                        style = IpbTheme.typography.body,
                        tint = IpbTheme.colors.textPrimary.asBrush()
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.tuningMultiplier,
                        useComponent = viewModel
                    )
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.tuneButton,
                        title = "Tune",
                        useComponent = viewModel
                    )

                }
            }
        }
    }
}

