package com.progressterra.ipbandroidview.ui.documents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.checklist.AuditDocument
import com.progressterra.ipbandroidview.model.checklist.ChecklistStatus
import com.progressterra.ipbandroidview.model.checklist.Document
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Suppress("unused")
class DocumentsNode(
    buildContext: BuildContext,
    private val onChecklist: (AuditDocument, ChecklistStatus) -> Unit,
    private val onArchive: (List<Document>) -> Unit,
    private val updateDocumentsCounter: (Int) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: DocumentsViewModel = getViewModel()
        viewModel.collectSideEffect {
            when (it) {
                is DocumentsEffect.OpenChecklist -> onChecklist(it.auditDocument, it.initialStatus)
                is DocumentsEffect.UpdateCounter -> updateDocumentsCounter(it.counter)
                is DocumentsEffect.Archive -> onArchive(it.archived)
            }
        }
        LaunchedEffect(Unit) {
            viewModel.refresh()
        }
        val state = viewModel.collectAsState()
        DocumentsScreen(
            state = state::value,
            refresh = viewModel::refresh,
            openArchive = viewModel::openArchive,
            openDocument = viewModel::openDocument,
        )
    }
}