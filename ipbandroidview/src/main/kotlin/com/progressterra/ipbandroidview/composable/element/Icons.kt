package com.progressterra.ipbandroidview.composable.element

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun FavoriteUncheckedIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_favorite),
        contentDescription = null,
        tint = AppTheme.colors.gray2
    )
}

@Preview
@Composable
private fun FavoriteUncheckedIconPreview() {
    AppTheme {
        FavoriteUncheckedIcon()
    }
}

@Composable
fun FavoriteCheckedIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_favorite_selected),
        contentDescription = null,
        tint = AppTheme.colors.primary
    )
}

@Preview
@Composable
private fun FavoriteCheckedIconPreview() {
    AppTheme {
        FavoriteCheckedIcon()
    }
}

@Composable
fun MicIcon(modifier: Modifier = Modifier, enabled: () -> Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_mic),
        contentDescription = null,
        tint = if (enabled()) AppTheme.colors.primary else AppTheme.colors.gray2
    )
}

@Preview
@Composable
private fun MicIconPreview() {
    AppTheme {
        Column {
            MicIcon(enabled = { true })
            MicIcon(enabled = { false })
        }
    }
}

@Composable
fun CameraIcon(modifier: Modifier = Modifier, enabled: () -> Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_camera),
        contentDescription = null,
        tint = if (enabled()) AppTheme.colors.primary else AppTheme.colors.gray2
    )
}

@Preview
@Composable
private fun CameraIconPreview() {
    AppTheme {
        Column {
            CameraIcon(enabled = { true })
            CameraIcon(enabled = { false })
        }
    }
}

@Composable
fun TrashIcon(modifier: Modifier = Modifier, enabled: () -> Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_trash),
        contentDescription = null,
        tint = if (enabled()) AppTheme.colors.error else AppTheme.colors.gray2
    )
}

@Preview
@Composable
private fun TrashIconPreview() {
    AppTheme {
        Column {
            TrashIcon(enabled = { true })
            TrashIcon(enabled = { false })
        }
    }
}

@Composable
fun PlayPauseIcon(modifier: Modifier = Modifier, ongoing: () -> Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = if (ongoing()) R.drawable.ic_pause else R.drawable.ic_play),
        contentDescription = null,
        tint = AppTheme.colors.primary
    )
}

@Preview
@Composable
private fun PlayPauseIconPreview() {
    AppTheme {
        Column {
            PlayPauseIcon(ongoing = { true })
            PlayPauseIcon(ongoing = { false })
        }
    }
}

@Composable
fun ForwardIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_forward),
        contentDescription = null,
        tint = AppTheme.colors.gray2
    )
}

@Preview
@Composable
private fun ForwardIconPreview() {
    AppTheme {
        ForwardIcon()
    }
}

@Composable
fun ForwardTinyIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_forward_tiny),
        contentDescription = null,
        tint = AppTheme.colors.gray2
    )
}

@Preview
@Composable
private fun ForwardTinyIconPreview() {
    AppTheme {
        ForwardTinyIcon()
    }
}

@Composable
fun SettingsIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_settings),
        contentDescription = null,
        tint = AppTheme.colors.gray1
    )
}

@Preview
@Composable
private fun SettingsIconPreview() {
    AppTheme {
        SettingsIcon()

    }
}

@Composable
fun BackIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = null,
        tint = AppTheme.colors.gray1
    )
}

@Preview
@Composable
private fun BackIconPreview() {
    AppTheme {
        BackIcon()

    }
}

@Composable
fun RulerIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_ruler),
        contentDescription = null,
        tint = AppTheme.colors.primary
    )
}

@Preview
@Composable
private fun RulerIconPreview() {
    AppTheme {
        RulerIcon()

    }
}

@Composable
fun MapIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_map),
        contentDescription = null,
        tint = AppTheme.colors.primary
    )
}

@Preview
@Composable
private fun MapIconPreview() {
    AppTheme {
        MapIcon()

    }
}

@Composable
fun LocationIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = AppTheme.colors.surfaces,
        painter = painterResource(id = R.drawable.ic_location),
        contentDescription = null
    )
}


@Preview
@Composable
private fun LocationIconPreview() {
    AppTheme {
        LocationIcon()

    }
}

@Composable
fun RefreshIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = AppTheme.colors.surfaces,
        painter = painterResource(id = R.drawable.ic_refresh),
        contentDescription = null
    )
}

@Preview
@Composable
private fun RefreshIconPreview() {
    AppTheme {
        RefreshIcon()
    }
}

@Composable
fun AddItemIcon(modifier: Modifier = Modifier, available: () -> Boolean) {
    Icon(
        modifier = modifier,
        tint = if (available()) AppTheme.colors.black else AppTheme.colors.gray2,
        painter = painterResource(id = R.drawable.ic_add_item),
        contentDescription = null
    )
}

@Preview
@Composable
private fun AddItemIconPreview() {
    AppTheme {
        Column {
            AddItemIcon(available = { true })
            AddItemIcon(available = { false })
        }
    }
}

@Composable
fun RemoveItemIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = AppTheme.colors.black,
        painter = painterResource(id = R.drawable.ic_remove_item),
        contentDescription = null
    )
}

@Preview
@Composable
private fun RemoveItemIconPreview() {
    AppTheme {
        RemoveItemIcon()
    }
}

@Composable
fun MarkIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = AppTheme.colors.gray1,
        painter = painterResource(id = R.drawable.ic_mark),
        contentDescription = null
    )
}

@Preview
@Composable
private fun MarkIconPreview() {
    AppTheme {
        MarkIcon()
    }
}

@Composable
fun BonusesSmallIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.size(20.dp),
        tint = AppTheme.colors.primary,
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = null
    )
}

@Preview
@Composable
private fun BonusesSmallIconPreview() {
    AppTheme {
        BonusesSmallIcon()
    }
}

@Composable
fun BonusesLargeIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.size(40.dp),
        tint = AppTheme.colors.primary,
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = null
    )
}

@Preview
@Composable
private fun BonusesLargeIconPreview() {
    AppTheme {
        BonusesLargeIcon()
    }
}

@Composable
fun BonusesTinyIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.size(16.dp),
        tint = AppTheme.colors.surfaces,
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = null
    )
}

@Preview
@Composable
private fun BonusesTinyIconPreview() {
    AppTheme {
        BonusesTinyIcon()
    }
}

@Composable
fun SearchIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = AppTheme.colors.gray1,
        painter = painterResource(R.drawable.ic_search),
        contentDescription = null
    )
}

@Preview
@Composable
private fun SearchIconPreview() {
    AppTheme {
        SearchIcon()
    }
}

@Composable
fun Mark2Icon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_mark_2),
        contentDescription = null,
        tint = Color.Unspecified
    )
}

@Preview
@Composable
private fun Mark2IconPreview() {
    AppTheme {
        Mark2Icon()
    }
}

@Composable
fun DownIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = AppTheme.colors.gray2,
        painter = painterResource(R.drawable.ic_down),
        contentDescription = null
    )
}

@Preview
@Composable
private fun DownIconPreview() {
    AppTheme {
        DownIcon()
    }
}

@Composable
fun UpIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = AppTheme.colors.gray2,
        painter = painterResource(R.drawable.ic_up),
        contentDescription = null
    )
}

@Preview
@Composable
private fun UpIconPreview() {
    AppTheme {
        UpIcon()
    }
}