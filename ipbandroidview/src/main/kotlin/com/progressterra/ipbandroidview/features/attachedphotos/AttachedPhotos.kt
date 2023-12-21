package com.progressterra.ipbandroidview.composable

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

private val picSize = 48.dp

private val verticalPadding = 14.dp

@Composable
fun AttachedPhotos(
    modifier: Modifier = Modifier,
    pictures: List<MultisizedImage>,
    onPhotoSelect: (MultisizedImage) -> Unit,
    onCamera: () -> Unit,
    enabled: Boolean
) {

    @Composable
    fun Item(picture: MultisizedImage) {
        SimpleImage(
            modifier = Modifier
                .size(picSize)
                .clip(RoundedCornerShape(8.dp))
                .niceClickable { onPhotoSelect(picture) },
            image = picture.url,
            backgroundColor = IpbTheme.colors.surface.asColor()
        )
    }

    if (pictures.isEmpty()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(TextFieldDefaults.MinHeight)
                .clip(RoundedCornerShape(8.dp))
                .background(IpbTheme.colors.background.asBrush())
                .niceClickable(enabled) { onCamera() }
                .padding(horizontal = 12.dp, vertical = verticalPadding),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BrushedText(
                text = stringResource(id = R.string.add_photo),
                style = IpbTheme.typography.body,
                tint = if (enabled) IpbTheme.colors.textPrimary.asBrush() else IpbTheme.colors.textTertiary.asBrush()
            )
            BrushedIcon(
                modifier = modifier,
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
                            .size(picSize)
                            .clip(RoundedCornerShape(8.dp))
                            .background(IpbTheme.colors.background.asBrush())
                            .niceClickable { onCamera() },
                        contentAlignment = Alignment.Center
                    ) {
                        BrushedIcon(
                            modifier = modifier,
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
        AttachedPhotos(pictures =
        listOf(
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                url = ""
            ),
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                url = ""
            ),
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                url = ""
            )
        ), onPhotoSelect = {}, onCamera = {}, enabled = true
        )
    }
}

@Preview
@Composable
private fun AttachedPhotosPreviewDisabled() {
    IpbTheme {
        AttachedPhotos(pictures = listOf(
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                url = ""
            ),
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                url = ""
            ),
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                url = ""
            )
        ), onPhotoSelect = {}, onCamera = {}, enabled = false
        )
    }
}