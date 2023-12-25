package com.progressterra.ipbandroidview.features.attachedphotos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
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
fun AttachedPhotos(
    modifier: Modifier = Modifier,
    pictures: List<MultisizedImage>,
    onPhotoSelect: (MultisizedImage) -> Unit,
    onDelete: (MultisizedImage) -> Unit,
    onCamera: () -> Unit,
    enabled: Boolean
) {

    @Composable
    fun Item(picture: MultisizedImage) {
        Box(
            modifier = Modifier
                .size(width = 48.dp, height = if (enabled) 64.dp else 48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(IpbTheme.colors.onSurface.asBrush())
        ) {
            SimpleImage(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .niceClickable { onPhotoSelect(picture) },
                image = picture.url,
                backgroundColor = IpbTheme.colors.surface.asColor()
            )
            if (enabled) {
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(width = 48.dp, height = 16.dp), onClick = { onDelete(picture) }
                ) {
                    BrushedIcon(
                        modifier = Modifier.size(16.dp),
                        resId = R.drawable.ic_trash,
                        tint = IpbTheme.colors.error.asBrush()
                    )
                }
            }
        }
    }

    if (pictures.isEmpty()) {
        Row(modifier = modifier
            .fillMaxWidth()
            .height(TextFieldDefaults.MinHeight)
            .clip(RoundedCornerShape(8.dp))
            .background(IpbTheme.colors.onSurface.asBrush())
            .niceClickable(enabled) { onCamera() }
            .padding(horizontal = 12.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            BrushedText(
                text = stringResource(id = R.string.add_photo),
                style = IpbTheme.typography.body,
                tint = if (enabled) IpbTheme.colors.textPrimary.asBrush() else IpbTheme.colors.textTertiary.asBrush()
            )
            BrushedIcon(
                modifier = Modifier,
                resId = R.drawable.ic_camera,
                tint = if (enabled) IpbTheme.colors.primary.asBrush() else IpbTheme.colors.iconTertiary.asBrush()
            )
        }
    } else {
        LazyRow(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (enabled) {
                item {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(IpbTheme.colors.onSurface.asBrush())
                            .niceClickable { onCamera() }, contentAlignment = Alignment.Center
                    ) {
                        BrushedIcon(
                            modifier = Modifier,
                            resId = R.drawable.ic_camera,
                            tint = IpbTheme.colors.primary.asBrush()
                        )
                    }
                }
            }
            items(pictures) { item ->
                Item(picture = item)
            }
        }
    }
}

@Preview
@Composable
private fun AttachedPhotosPreviewEnabled() {
    IpbTheme {
        AttachedPhotos(pictures = listOf(
            MultisizedImage(
                id = "", local = false, toRemove = false, url = ""
            ), MultisizedImage(
                id = "", local = false, toRemove = false, url = ""
            ), MultisizedImage(
                id = "", local = false, toRemove = false, url = ""
            )
        ), onPhotoSelect = {}, onCamera = {}, onDelete = {}, enabled = true
        )
    }
}

@Preview
@Composable
private fun AttachedPhotosPreviewDisabled() {
    IpbTheme {
        AttachedPhotos(pictures = listOf(
            MultisizedImage(
                id = "", local = false, toRemove = false, url = ""
            ), MultisizedImage(
                id = "", local = false, toRemove = false, url = ""
            ), MultisizedImage(
                id = "", local = false, toRemove = false, url = ""
            )
        ), onPhotoSelect = {}, onCamera = {}, onDelete = {}, enabled = false
        )
    }
}

@Preview
@Composable
private fun AttachedPhotosPreviewEmpty() {
    IpbTheme {
        AttachedPhotos(
            pictures = listOf(),
            onPhotoSelect = {},
            onCamera = {},
            onDelete = {},
            enabled = true
        )
    }
}