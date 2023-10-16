package com.progressterra.ipbandroidview.pages.datingmain

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node

class DatingMainScreenNode(
    buildContext: BuildContext
) : Node(buildContext = buildContext) {

    override fun updateLifecycleState(state: Lifecycle.State) {
        super.updateLifecycleState(state)
        Log.d("DATING", "updateLifecycleState: ${state.name}")
    }

    @Composable
    override fun View(modifier: Modifier) {
    }
}