package com.progressterra.ipbandroidview.pages.documents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenship
import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenshipState
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumn
import com.progressterra.ipbandroidview.widgets.documents.Documents
import com.progressterra.ipbandroidview.widgets.documents.DocumentsState

@Composable
fun DocumentsScreen(
    modifier: Modifier = Modifier, state: DocumentsScreenState, useComponent: UseDocumentsScreen
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = stringResource(R.string.documents),
            showBackButton = true,
            useComponent = useComponent
        )
    }) { _, _ ->
        StateColumn(
            state = state.screen, useComponent = useComponent,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            CurrentCitizenship(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.citizenship, useComponent = useComponent
            )
            Documents(
                modifier = Modifier.padding(horizontal = 20.dp),
                state = state.documents, useComponent = useComponent
            )
        }
    }
}

@Preview
@Composable
private fun DocumentsScreenPreview() {
    val citizenshipState = CurrentCitizenshipState(
        citizenship = Citizenship(name = "United States", id = "")
    )

    val documentsScreenState = DocumentsScreenState(
        documents = DocumentsState(
            listOf(
                Document(
                    docName = "Passport",
                    status = TypeStatusDoc.CONFIRMED,
                    id = "",
                    entries = emptyList(),
                    photo = null
                ), Document(
                    docName = "Passport",
                    status = TypeStatusDoc.NOT_FILL,
                    id = "",
                    entries = emptyList(),
                    photo = null
                )
            )
        ),
        citizenship = citizenshipState,
        screen = ScreenState.SUCCESS
    )

    IpbTheme {
        DocumentsScreen(state = documentsScreenState, useComponent = UseDocumentsScreen.Empty())
    }
}



