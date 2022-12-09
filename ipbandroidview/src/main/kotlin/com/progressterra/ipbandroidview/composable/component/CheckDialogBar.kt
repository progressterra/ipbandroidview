package com.progressterra.ipbandroidview.composable.component

import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.composable.element.BasicTopAppBar
import com.progressterra.ipbandroidview.composable.element.MarkIcon
import com.progressterra.ipbandroidview.theme.AppTheme
import com.progressterra.ipbandroidview.model.checklist.Check

@Composable
fun CheckDialogBar(
    modifier: Modifier = Modifier,
    currentCheck: () -> Check?,
    onMark: () -> Unit
) {
    BasicTopAppBar(modifier = modifier,
        rightActions = {
            IconButton(
                onClick = onMark
            ) {
                MarkIcon()
            }
        },
        title = {
            val check = currentCheck()
            Text(
                text = check?.printTitle() ?: stringResource(R.string.loading),
                color = AppTheme.colors.black,
                style = AppTheme.typography.title,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    )
}

@Preview
@Composable
private fun CheckDialogBarWithBackNavPreview0() {
    AppTheme {
        CheckDialogBar(currentCheck = { null }, onMark = {})
    }
}
