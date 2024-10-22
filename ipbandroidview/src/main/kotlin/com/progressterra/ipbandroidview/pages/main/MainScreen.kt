package com.progressterra.ipbandroidview.pages.main

import android.app.Activity
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.features.bonuses.Bonuses
import com.progressterra.ipbandroidview.shared.ui.Layout
import com.progressterra.ipbandroidview.widgets.galleries.Galleries

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun MainScreen(modifier: Modifier = Modifier, state: MainScreenState, useComponent: UseMainScreen) {

    val view = LocalView.current
    val window = (view.context as Activity).window
    val useDarkIcons = MaterialTheme.colors.isLight

    LaunchedEffect(Unit) {
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = useDarkIcons
    }


    Layout(
        modifier = modifier,
    ) { _, _ ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),
            //contentPadding = PaddingValues(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            item {
                Bonuses(
                    modifier = Modifier.fillMaxSize().padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()),

//                        .systemBarsPadding(),//padding(horizontal = 20.dp),
                    state = state.bonuses,
                    useComponent = useComponent
                )
            }
            items(state.recommended) { Galleries(state = it, useComponent = useComponent) }
        }
    }
}
