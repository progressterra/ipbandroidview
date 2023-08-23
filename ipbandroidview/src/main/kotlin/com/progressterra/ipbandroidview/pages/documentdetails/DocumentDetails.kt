package com.progressterra.ipbandroidview.pages.documentdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.toCanBeEditted
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChat
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhoto
import com.progressterra.ipbandroidview.features.topbar.TopBar
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.ThemedLayout
import com.progressterra.ipbandroidview.shared.ui.button.Button
import com.progressterra.ipbandroidview.shared.ui.textfield.TextField

@Composable
fun DocumentDetails(
    modifier: Modifier = Modifier, state: DocumentDetailsState, useComponent: UseDocumentDetails
) {
    ThemedLayout(modifier = modifier, topBar = {
        TopBar(
            title = state.document.name,
            showBackButton = true,
            useComponent = useComponent
        )
    },
        bottomBar = {
            if (state.document.status.toCanBeEditted()) {
                Column(
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(IpbTheme.colors.surface.asBrush())
                        .padding(8.dp)
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        state = state.apply,
                        useComponent = useComponent,
                        title = stringResource(R.string.ready)
                    )
                }
            }
        }) { _, _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.document.entries) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        state = it,
                        useComponent = useComponent
                    )
                }
                if (!state.document.photo.isEmpty()) {
                    item {
                        DocumentPhoto(
                            state = state.document.photo,
                            useComponent = useComponent
                        )
                    }
                }
            }
            AttachableChat(state = state.chat, canBeClosed = false, useComponent = useComponent)
        }
    }
}

@Preview
@Composable
private fun DocumentDetails() {
    IpbTheme {
        DocumentDetails(
            state = DocumentDetailsState(),
            useComponent = UseDocumentDetails.Empty()
        )
    }
}
