package com.progressterra.ipbandroidview.widgets.documents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.toColor
import com.progressterra.ipbandroidview.entities.toString
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.niceClickable

@Composable
fun Documents(
    modifier: Modifier = Modifier,
    state: DocumentsState,
    useComponent: UseDocuments
) {

    @Composable
    fun Item(
        itemState: Document
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(IpbTheme.colors.surface.asBrush())
                .niceClickable { useComponent.handle(DocumentsEvent(itemState)) }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                BrushedText(
                    text = itemState.name,
                    style = IpbTheme.typography.body,
                    tint = IpbTheme.colors.textPrimary.asBrush()
                )
                BrushedText(
                    text = itemState.status.toString { stringResource(id = it) },
                    style = IpbTheme.typography.footnoteRegular,
                    tint = itemState.status.toColor()
                )
            }
            BrushedIcon(
                resId = R.drawable.ic_forw,
                tint = IpbTheme.colors.iconPrimary.asBrush()
            )
        }
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.items) {
            Item(it)
        }
    }
}

@Composable
@Preview
private fun DocumentsPreview() {
    IpbTheme {
        Documents(
            state = DocumentsState(
                items = listOf(
                    Document(
                        id = "",
                        name = "Passport 1",
                        status = TypeStatusDoc.REJECTED
                    ),
                    Document(
                        id = "",
                        name = "Passport 2",
                        status = TypeStatusDoc.NOT_FILL
                    ),
                    Document(
                        id = "",
                        name = "Passport 3",
                        status = TypeStatusDoc.WAIT_REVIEW
                    ),
                    Document(
                        id = "",
                        name = "Passport 4",
                        status = TypeStatusDoc.WAIT_IMAGE
                    ),
                    Document(
                        id = "",
                        name = "Passport 5",
                        status = TypeStatusDoc.CONFIRMED
                    )
                )
            ),
            useComponent = UseDocuments.Empty()
        )
    }
}
