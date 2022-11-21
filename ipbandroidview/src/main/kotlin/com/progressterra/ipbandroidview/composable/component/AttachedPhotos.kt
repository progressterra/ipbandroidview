package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.progressterra.ipbandroidview.composable.element.CameraIcon
import com.progressterra.ipbandroidview.composable.element.SimpleImage
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.CheckPicture
import com.progressterra.ipbandroidview.theme.AppTheme

private val picSize = 48.dp

@Composable
fun AttachedPhotos(
    modifier: Modifier = Modifier,
    pictures: () -> List<CheckPicture>,
    onPhotoSelect: (CheckPicture) -> Unit,
    onCamera: () -> Unit,
    enabled: () -> Boolean
) {

    @Composable
    fun Item(picture: CheckPicture) {
        SimpleImage(
            modifier = Modifier
                .size(picSize)
                .clip(AppTheme.shapes.small)
                .niceClickable(onClick = { onPhotoSelect(picture) }),
            url = { picture.thumbnail },
            backgroundColor = AppTheme.colors.surfaces
        )
    }

    if (pictures().isEmpty()) {
        Row(
            modifier = modifier
                .clip(AppTheme.shapes.small)
                .fillMaxWidth()
                .height(TextFieldDefaults.MinHeight)
                .background(AppTheme.colors.background)
                .niceClickable(
                    onClick = onCamera,
                    enabled = enabled
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.add_photo),
                style = AppTheme.typography.text,
                color = if (enabled()) AppTheme.colors.gray1 else AppTheme.colors.gray2
            )
            CameraIcon(enabled = enabled)
        }
    } else {
        LazyRow(modifier, horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.small)) {
            if (enabled()) {
                item {
                    Box(
                        modifier = Modifier
                            .size(picSize)
                            .clip(AppTheme.shapes.small)
                            .background(AppTheme.colors.background)
                            .niceClickable(onClick = onCamera),
                        contentAlignment = Alignment.Center
                    ) {
                        CameraIcon(enabled = { true })
                    }
                }
            }
            items(pictures()) { item ->
                Item(picture = item)
            }
        }
    }
}

@Preview
@Composable
private fun AttachedPhotosPreviewEnabled() {
    AppTheme {
        AttachedPhotos(pictures = {
            listOf(
                CheckPicture(
                    id = "",
                    local = false,
                    toRemove = false,
                    thumbnail = "",
                    fullSize = ""
                ),
                CheckPicture(
                    id = "",
                    local = false,
                    toRemove = false,
                    thumbnail = "",
                    fullSize = ""
                ),
                CheckPicture(
                    id = "",
                    local = false,
                    toRemove = false,
                    thumbnail = "",
                    fullSize = ""
                )
            )
        }, onPhotoSelect = {}, onCamera = {}, enabled = { true }

        )
    }
}

@Preview
@Composable
private fun AttachedPhotosPreviewDisabled() {
    AppTheme {
        AttachedPhotos(pictures = {
            listOf(
                CheckPicture(
                    id = "",
                    local = false,
                    toRemove = false,
                    thumbnail = "",
                    fullSize = ""
                ),
                CheckPicture(
                    id = "",
                    local = false,
                    toRemove = false,
                    thumbnail = "",
                    fullSize = ""
                ),
                CheckPicture(
                    id = "",
                    local = false,
                    toRemove = false,
                    thumbnail = "",
                    fullSize = ""
                )
            )
        }, onPhotoSelect = {}, onCamera = {}, enabled = { false }
        )
    }
}