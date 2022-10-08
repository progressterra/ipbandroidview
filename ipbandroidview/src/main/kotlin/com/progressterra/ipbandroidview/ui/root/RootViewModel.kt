package com.progressterra.ipbandroidview.ui.root

import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class RootViewModel : ViewModel(), ContainerHost<RootState, RootEffect> {

    override val container: Container<RootState, RootEffect> = container(RootState())

    fun configure(
        onBack: (() -> Unit)? = null,
        title: String? = null,
        actions: (@Composable RowScope.() -> Unit)? = null,
        bottomBarVisibility: Boolean = false
    ) = intent {
        reduce {
            RootState(onBack, title, actions, bottomBarVisibility)
        }
    }
}
