package com.progressterra.ipbandroidview.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackSlider

class RootNode(
    buildContext: BuildContext,
    private val backStack: BackStack<Screen> = BackStack(
        Screen.Splash,
        buildContext.savedStateMap
    )
) : ParentNode<Screen>(
    backStack,
    buildContext
) {

    override fun resolve(navTarget: Screen, buildContext: BuildContext): Node =
        when (navTarget) {
            Screen.City -> TODO()
            Screen.ConfirmationCode -> TODO()
            Screen.SignIn -> TODO()
            Screen.SignUp -> TODO()
            Screen.Splash -> TODO()
        }

    @Composable
    override fun View(modifier: Modifier) {
        Children(
            navModel = backStack,
            transitionHandler = rememberBackstackSlider()
        )
    }
}