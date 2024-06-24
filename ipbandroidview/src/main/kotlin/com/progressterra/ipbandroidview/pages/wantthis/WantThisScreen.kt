package com.progressterra.ipbandroidview.pages.wantthis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhoto
import com.progressterra.ipbandroidview.features.profilebutton.ProfileButton
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.Layout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumn
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close




import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import qrscanner.QrScanner


@Composable
fun QrScannerCompose(onQrCodeScanned: (String) -> Unit) {
    var qrCodeURL by remember { mutableStateOf("") }
    var startBarCodeScan by remember { mutableStateOf(true) }
    var flashlightOn by remember { mutableStateOf(false) }
    var launchGallery by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (qrCodeURL.isEmpty() && startBarCodeScan) {
                Column(
                    modifier = Modifier
                        .background(color = Color.Black)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(250.dp)
                            .clip(shape = RoundedCornerShape(size = 14.dp))
                            .clipToBounds()
                            .border(2.dp, Color.Gray, RoundedCornerShape(size = 14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        QrScanner(
                            modifier = Modifier
                                .clipToBounds()
                                .clip(shape = RoundedCornerShape(size = 14.dp)),
                            flashlightOn = flashlightOn,
                            launchGallery = launchGallery,
                            onCompletion = {
                                qrCodeURL = it
                                startBarCodeScan = false
                                onQrCodeScanned(it)
                            },
                            onGalleryCallBackHandler = {
                                launchGallery = it
                            },
                            onFailure = {
                                coroutineScope.launch {
                                    if (it.isEmpty()) {
                                        snackBarHostState.showSnackbar("Invalid qr code")
                                    } else {
                                        snackBarHostState.showSnackbar(it)
                                    }
                                }
                            }
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 30.dp)
                            .background(
                                color = Color(0xFFF9F9F9),
                                shape = RoundedCornerShape(25.dp)
                            )
                            .height(35.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 18.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(11.dp)
                        ) {
                            Icon(imageVector = if (flashlightOn) Icons.Filled.Close else Icons.Filled.Close,
                                "flash",
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        flashlightOn = !flashlightOn
                                    })
                        }
                    }
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            startBarCodeScan = true
                            qrCodeURL = ""
                        },
                    ) {
                        Text(
                            text = "Scan Qr",
                            modifier = Modifier.background(Color.Transparent)
                                .padding(horizontal = 12.dp, vertical = 12.dp),
                            fontSize = 16.sp
                        )
                    }

                    Text(
                        text = qrCodeURL,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
        }
        if (startBarCodeScan) {
            Icon(
                imageVector = Icons.Filled.Close,
                "Close",
                modifier = Modifier
                    .padding(top = 12.dp, end = 12.dp)
                    .size(24.dp)
                    .clickable {
                        startBarCodeScan = false
                    }.align(Alignment.TopEnd),
                tint = Color.White
            )
        }
    }
}

@Composable
fun WantThisScreen(
    modifier: Modifier = Modifier,
    state: WantThisScreenState,
    useComponent: UseWantThisScreen
) {


    val updateFirstEntry: (String) -> Unit = { scannedValue ->
        val updatedEntries = state.document.entries.mapIndexed { index, textFieldState ->
            if (index == 0) {
                textFieldState.copy(text = scannedValue)
            } else {
                textFieldState
            }
        }

    }

    Layout(
        modifier = modifier,
        topBar = { TopBar(title = stringResource(R.string.want_this), useComponent = useComponent) }
    ) { _, _ ->
        StateColumn(
            state = state.screen,
            scrollable = true,
            useComponent = useComponent,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                QrScannerCompose(onQrCodeScanned = updateFirstEntry)
            }
            Column(
                modifier = Modifier.padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                state.document.entries.forEach {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = it,
                        useComponent = useComponent
                    )
                }
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    state = state.send,
                    useComponent = useComponent,
                    title = stringResource(R.string.send_request)
                )
            }
        }
    }
}

@Composable
@Preview
private fun WantThisScreenPreview() {
    IpbTheme {
        WantThisScreen(
            state = WantThisScreenState(screen = StateColumnState(state = ScreenState.SUCCESS)),
            useComponent = UseWantThisScreen.Empty()
        )
    }
}