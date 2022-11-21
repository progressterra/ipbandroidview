package com.progressterra.ipbandroidview.ui.checklist

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.CheckPicture
import org.koin.androidx.compose.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class ChecklistNode(
    buildContext: BuildContext,
    private val auditDocument: AuditDocument,
    private val onBack: () -> Unit,
    private val openImage: (CheckPicture, Boolean) -> Unit
) : Node(buildContext) {

    @Composable
    override fun View(modifier: Modifier) {
        val viewModel: ChecklistViewModel = getViewModel()
        val context = LocalContext.current
        viewModel.collectSideEffect {
            when (it) {
                is ChecklistEffect.Back -> onBack()
                is ChecklistEffect.OpenImage -> openImage(it.picture, it.enabled)
                is ChecklistEffect.Toast -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        var alreadyLaunched by rememberSaveable(auditDocument) {
            mutableStateOf(false)
        }
        if (!alreadyLaunched) {
            alreadyLaunched = true
            viewModel.setDocument(auditDocument)
        }
        val state = viewModel.collectAsState()
        ChecklistScreen(
            state = state::value,
            back = viewModel::back,
            refreshCheck = viewModel::refreshCheck,
            refreshChecklist = viewModel::refreshChecklist,
            openCheck = viewModel::openCheck,
            applyCheck = viewModel::applyCheck,
            startStopAudit = viewModel::startStopAudit,
            yesNo = viewModel::yesNo,
            editCheckCommentary = viewModel::editCheckCommentary,
            startPausePlay = viewModel::startPausePlay,
            startStopRecording = viewModel::startStopRecording,
            remove = viewModel::remove,
            openImage = viewModel::openImage,
            onCamera = viewModel::onCamera,
        )
    }
}