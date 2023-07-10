package com.progressterra.ipbandroidview.pages.support

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.chatinput.ChatInput
import com.progressterra.ipbandroidview.features.chatinput.ChatInputState
import com.progressterra.ipbandroidview.features.chatinput.UseChatInput
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBox
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldState
import com.progressterra.ipbandroidview.widgets.messages.Messages
import com.progressterra.ipbandroidview.widgets.messages.MessagesState
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import org.orbitmvi.orbit.viewmodel.container

@Composable
fun SupportScreen(
    modifier: Modifier = Modifier,
    state: SupportScreenState,
    useComponent: UseSupportScreen
) {
    ThemedLayout(
        modifier = modifier,
        topBar = {
            TopBar(
                title = stringResource(R.string.support),
                showBackButton = true,
                useComponent = useComponent
            )
        }
    ) { _, _ ->
        StateBox(
            state = state.screenState,
            useComponent = useComponent
        ) {
            Messages(
                state = state.messages,
            )
            ChatInput(
                state = state.input,
                useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun SupportScreenPreview() {
    IpbTheme {
        SupportScreen(
            state = SupportScreenState(
                input = ChatInputState(
                    input = TextFieldState(text = "Some input")
                ),
                messages = MessagesState(
                    items = listOf(
                        MessagesState.Item(
                            user = true,
                            content = "Hello world!",
                            date = "12.12.2012"
                        ),
                        MessagesState.Item(
                            user = false,
                            content = "Hello, user!",
                            date = "12.12.2012"
                        ),
                        MessagesState.Item(
                            user = true,
                            content = "Mucho gusto!",
                            date = "12.12.2012"
                        )
                    )
                ),
                screenState = ScreenState.SUCCESS
            ),
            useComponent = UseSupportScreen.Empty()
        )
    }
}

interface UseSupportScreen : UseTopBar, UseStateBox, UseChatInput {

    class Empty : UseSupportScreen {

        override fun handle(event: TopBarEvent) = Unit

        override fun handle(event: StateBoxEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}

object SupportScreenEvent

@Immutable
data class SupportScreenState(
    val input: ChatInputState = ChatInputState(),
    val messages: MessagesState = MessagesState(),
    val screenState: ScreenState = ScreenState.LOADING
)

@Suppress("unused")
class SupportScreenNode(
    buildContext: BuildContext,
    private val onBack: () -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel = getViewModel<SupportScreenViewModel>()
        viewModel.collectSideEffect { onBack() }
        val state = viewModel.collectAsState().value
        SupportScreen(
            modifier = modifier,
            state = state,
            useComponent = viewModel
        )
    }
}

class SupportScreenViewModel : ContainerHost<SupportScreenState, SupportScreenEvent>,
    ViewModel(), UseSupportScreen {

    override val container = container<SupportScreenState, SupportScreenEvent>(SupportScreenState())

    override fun handle(event: TopBarEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: StateBoxEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: TextFieldEvent) {
        TODO("Not yet implemented")
    }
}
