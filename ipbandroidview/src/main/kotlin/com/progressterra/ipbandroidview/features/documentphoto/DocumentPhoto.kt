package com.progressterra.ipbandroidview.features.documentphoto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.MultisizedImage
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedIcon
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.SimpleImage
import com.progressterra.ipbandroidview.shared.ui.modifier.niceClickable

@Composable
fun DocumentPhoto(
    modifier: Modifier = Modifier,
    state: DocumentPhotoState,
    name: String,
    useComponent: UseDocumentPhoto
) {

    @Composable
    fun Item(picture: MultisizedImage) {
        SimpleImage(
            modifier = Modifier
                .size(63.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(IpbTheme.colors.surface.asBrush())
                .niceClickable { useComponent.handle(DocumentPhotoEvent.Select(picture)) },
            image = picture.url
        )
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BrushedText(
                text = name,
                style = IpbTheme.typography.body,
                tint = IpbTheme.colors.textPrimary.asBrush()
            )
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(IpbTheme.colors.surface.asBrush())
                    .niceClickable(enabled = state.enabled) { useComponent.handle(DocumentPhotoEvent.MakePhoto) }
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                BrushedIcon(
                    resId = R.drawable.ic_camera,
                    tint = if (state.enabled) IpbTheme.colors.iconPrimary.asBrush() else IpbTheme.colors.textDisabled.asBrush()
                )
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.items) { item ->
                Item(picture = item)
            }
        }
    }
}

@Preview
@Composable
private fun AttachedPhotosPreviewEnabled() {
    IpbTheme {
        DocumentPhoto(
            state = DocumentPhotoState(
                enabled = true,
                items = listOf(
                    MultisizedImage(
                        id = "", local = false, toRemove = false, url = ""
                    ), MultisizedImage(
                        id = "", local = false, toRemove = false, url = ""
                    ), MultisizedImage(
                        id = "", local = false, toRemove = false, url = ""
                    )
                )
            ), useComponent = UseDocumentPhoto.Empty(),
            name = "Foto foto"
        )
    }
}
