package com.progressterra.ipbandroidview.ui.archive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus
import com.progressterra.ipbandroidview.entities.Document
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class ArchiveNode(
    buildContext: BuildContext,
    private val title: String,
    private val archived: List<Document>,
    private val onBack: () -> Unit,
    private val onChecklist: (AuditDocument, ChecklistStatus) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: ArchiveViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is ArchiveEffect.Back -> onBack()
                is ArchiveEffect.OpenChecklist -> onChecklist(it.auditDocument, it.initialStatus)
            }
        }
        var alreadyLaunched by rememberSaveable(title, archived) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setDocuments(title, archived)
        }
        val state = viewModel.collectAsState()
        ArchiveScreen(
            state = state.value,
            interactor = viewModel
        )
    }
}