package com.progressterra.ipbandroidview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.theme.AppTheme

@Composable
fun FavoriteUncheckedIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_favorite),
        contentDescription = stringResource(
            id = R.string.favorite_button
        ),
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
        contentDescription = stringResource(
            id = R.string.favorite_button
        ),
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
fun MicIcon(modifier: Modifier = Modifier, enabled: Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_mic),
        contentDescription = stringResource(
            id = R.string.mic
        ),
        tint = if (enabled) AppTheme.colors.primary else AppTheme.colors.gray2
    )
}


@Preview
@Composable
private fun MicIconPreview() {
    AppTheme {
        Column {
            MicIcon(enabled = true)
            MicIcon(enabled = false)
        }
    }
}

@Composable
fun CameraIcon(modifier: Modifier = Modifier, enabled: Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_camera),
        contentDescription = stringResource(
            id = R.string.camera
        ),
        tint = if (enabled) AppTheme.colors.primary else AppTheme.colors.gray2
    )
}


@Preview
@Composable
private fun CameraIconPreview() {
    AppTheme {
        Column {
            CameraIcon(enabled = true)
            CameraIcon(enabled = false)
        }
    }
}

@Composable
fun TrashIcon(modifier: Modifier = Modifier, enabled: Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_trash),
        contentDescription = stringResource(
            id = R.string.trash
        ),
        tint = if (enabled) AppTheme.colors.error else AppTheme.colors.gray2
    )
}


@Preview
@Composable
private fun TrashIconPreview() {
    AppTheme {
        Column {
            TrashIcon(enabled = true)
            TrashIcon(enabled = false)
        }
    }
}

@Composable
fun PlayPauseIcon(modifier: Modifier = Modifier, ongoing: Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = if (ongoing) R.drawable.ic_pause else R.drawable.ic_play),
        contentDescription = stringResource(
            id = R.string.pause_stop
        ),
        tint = AppTheme.colors.primary
    )
}


@Preview
@Composable
private fun PlayPauseIconPreview() {
    AppTheme {
        Column {
            PlayPauseIcon(ongoing = true)
            PlayPauseIcon(ongoing = false)
        }
    }
}

@Composable
fun ForwardIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.size(24.dp),
        painter = painterResource(id = R.drawable.ic_forward),
        contentDescription = stringResource(
            id = R.string.forward
        ),
        tint = AppTheme.colors.gray2
    )
}


@Preview
@Composable
private fun ForwardIconPreview() {
    AppTheme {
        Column {
            ForwardIcon()
            ForwardIcon()
        }
    }
}