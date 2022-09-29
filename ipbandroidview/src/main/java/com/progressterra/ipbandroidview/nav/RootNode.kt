package com.progressterra.ipbandroidview.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.composable.Children
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.node.ParentNode
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.operation.pop
import com.bumble.appyx.navmodel.backstack.operation.push
import com.bumble.appyx.navmodel.backstack.operation.replace
import com.bumble.appyx.navmodel.backstack.transitionhandler.rememberBackstackSlider
import com.progressterra.ipbandroidview.ui.city.CityNode
import com.progressterra.ipbandroidview.ui.confirmationcode.ConfirmationCodeNode
import com.progressterra.ipbandroidview.ui.main.MainNode
import com.progressterra.ipbandroidview.ui.signin.SignInNode
import com.progressterra.ipbandroidview.ui.signup.SignUpNode
import com.progressterra.ipbandroidview.ui.splash.SplashNode

class RootNode(
    buildContext: BuildContext, private val backStack: BackStack<Screen> = BackStack(
        Screen.Splash, buildContext.savedStateMap
    )
) : ParentNode<Screen>(
    backStack, buildContext
) {

    override fun resolve(navTarget: Screen, buildContext: BuildContext): Node = when (navTarget) {
        is Screen.City -> CityNode(buildContext = buildContext,
            onBack = { backStack.pop() },
            onNext = { backStack.push(Screen.Main) },
            onSkip = { backStack.push(Screen.Main) })
        is Screen.ConfirmationCode -> ConfirmationCodeNode(buildContext = buildContext,
            onNext = { backStack.push(Screen.SignUp) },
            onBack = { backStack.pop() })
        is Screen.SignIn -> SignInNode(buildContext = buildContext,
            onNext = { backStack.push(Screen.ConfirmationCode) },
            onSkip = { backStack.push(Screen.Main) },
            onBack = { backStack.pop() })
        is Screen.SignUp -> SignUpNode(buildContext = buildContext,
            onNext = { backStack.push(Screen.City) },
            onSkip = { backStack.push(Screen.Main) },
            onBack = { backStack.pop() })
        is Screen.Splash -> SplashNode(buildContext = buildContext,
            onAuth = { backStack.replace(Screen.SignIn) },
            onNonAuth = { backStack.replace(Screen.Main) })
        is Screen.Main -> MainNode(buildContext)
    }

    @Composable
    override fun View(modifier: Modifier) {
        Children(
            navModel = backStack, transitionHandler = rememberBackstackSlider()
        )
    }
}