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
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.model.MultisizedImage
import com.progressterra.ipbandroidview.theme.AppTheme

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
                .clip(AppTheme.shapes.small)
                .niceClickable { onPhotoSelect(picture) },
            url = picture.thumbnail,
            backgroundColor = AppTheme.colors.surfaces
        )
    }

    if (pictures.isEmpty()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(TextFieldDefaults.MinHeight)
                .clip(AppTheme.shapes.small)
                .background(AppTheme.colors.background)
                .niceClickable(enabled) { onCamera() }
                .padding(horizontal = AppTheme.dimensions.medium, vertical = verticalPadding),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.add_photo),
                style = AppTheme.typography.text,
                color = if (enabled) AppTheme.colors.gray1 else AppTheme.colors.gray2
            )
            Box(modifier = Modifier.size(picSize), contentAlignment = Alignment.Center) {
                CameraIcon(enabled = enabled)
            }
        }
    } else {
        LazyRow(modifier, horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
            if (enabled) {
                item {
                    Box(
                        modifier = Modifier
                            .size(picSize)
                            .clip(AppTheme.shapes.small)
                            .background(AppTheme.colors.background)
                            .niceClickable { onCamera() },
                        contentAlignment = Alignment.Center
                    ) {
                        CameraIcon(enabled = true)
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
    AppTheme {
        AttachedPhotos(pictures =
        listOf(
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                thumbnail = "",
                fullSize = ""
            ),
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                thumbnail = "",
                fullSize = ""
            ),
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                thumbnail = "",
                fullSize = ""
            )
        ), onPhotoSelect = {}, onCamera = {}, enabled = true
        )
    }
}

@Preview
@Composable
private fun AttachedPhotosPreviewDisabled() {
    AppTheme {
        AttachedPhotos(pictures = listOf(
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                thumbnail = "",
                fullSize = ""
            ),
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                thumbnail = "",
                fullSize = ""
            ),
            MultisizedImage(
                id = "",
                local = false,
                toRemove = false,
                thumbnail = "",
                fullSize = ""
            )
        ), onPhotoSelect = {}, onCamera = {}, enabled = false
        )
    }
}