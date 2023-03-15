package com.progressterra.ipbandroidview.composable

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
import com.progressterra.ipbandroidview.theme.IpbTheme

@Composable
fun FavoriteUncheckedIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(R.drawable.ic_favorite),
        contentDescription = null,
        tint = IpbTheme.colors.gray2
    )
}

@Preview
@Composable
private fun FavoriteUncheckedIconPreview() {
    IpbTheme {
        FavoriteUncheckedIcon()
    }
}

@Composable
fun FavoriteCheckedIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_favorite_selected),
        contentDescription = null,
        tint = IpbTheme.colors.primary
    )
}

@Preview
@Composable
private fun FavoriteCheckedIconPreview() {
    IpbTheme {
        FavoriteCheckedIcon()
    }
}

@Composable
fun MicIcon(modifier: Modifier = Modifier, enabled: Boolean) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_mic),
        contentDescription = null,
        tint = if (enabled) IpbTheme.colors.primary else IpbTheme.colors.gray2
    )
}

@Preview
@Composable
private fun MicIconPreview() {
    IpbTheme {
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
        contentDescription = null,
        tint = if (enabled) IpbTheme.colors.primary else IpbTheme.colors.gray2
    )
}

@Preview
@Composable
private fun CameraIconPreview() {
    IpbTheme {
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
        contentDescription = null,
        tint = if (enabled) IpbTheme.colors.error else IpbTheme.colors.gray2
    )
}

@Preview
@Composable
private fun TrashIconPreview() {
    IpbTheme {
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
        contentDescription = null,
        tint = IpbTheme.colors.primary
    )
}

@Preview
@Composable
private fun PlayPauseIconPreview() {
    IpbTheme {
        Column {
            PlayPauseIcon(ongoing = true)
            PlayPauseIcon(ongoing = false)
        }
    }
}

@Composable
fun ForwardIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_forward),
        contentDescription = null,
        tint = IpbTheme.colors.gray2
    )
}

@Preview
@Composable
private fun ForwardIconPreview() {
    IpbTheme {
        ForwardIcon()
    }
}

@Composable
fun ForwardTinyIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_forward_tiny),
        contentDescription = null,
        tint = IpbTheme.colors.gray2
    )
}

@Preview
@Composable
private fun ForwardTinyIconPreview() {
    IpbTheme {
        ForwardTinyIcon()
    }
}

@Composable
fun SettingsIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_settings),
        contentDescription = null,
        tint = IpbTheme.colors.gray2
    )
}

@Preview
@Composable
private fun SettingsIconPreview() {
    IpbTheme {
        SettingsIcon()

    }
}

@Composable
fun BackIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = null,
        tint = IpbTheme.colors.gray2
    )
}

@Preview
@Composable
private fun BackIconPreview() {
    IpbTheme {
        BackIcon()

    }
}

@Composable
fun RulerIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_ruler),
        contentDescription = null,
        tint = IpbTheme.colors.primary
    )
}

@Preview
@Composable
private fun RulerIconPreview() {
    IpbTheme {
        RulerIcon()

    }
}

@Composable
fun MapIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_map),
        contentDescription = null,
        tint = IpbTheme.colors.primary
    )
}

@Preview
@Composable
private fun MapIconPreview() {
    IpbTheme {
        MapIcon()

    }
}

@Composable
fun LocationIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = IpbTheme.colors.surfaces,
        painter = painterResource(id = R.drawable.ic_location),
        contentDescription = null
    )
}


@Preview
@Composable
private fun LocationIconPreview() {
    IpbTheme {
        LocationIcon()

    }
}

@Composable
fun RefreshIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = IpbTheme.colors.surfaces,
        painter = painterResource(id = R.drawable.ic_refresh),
        contentDescription = null
    )
}

@Preview
@Composable
private fun RefreshIconPreview() {
    IpbTheme {
        RefreshIcon()
    }
}

@Composable
fun AddItemIcon(modifier: Modifier = Modifier, available: Boolean) {
    Icon(
        modifier = modifier,
        tint = if (available) IpbTheme.colors.black else IpbTheme.colors.gray2,
        painter = painterResource(id = R.drawable.ic_add_item),
        contentDescription = null
    )
}

@Preview
@Composable
private fun AddItemIconPreview() {
    IpbTheme {
        Column {
            AddItemIcon(available = true)
            AddItemIcon(available = false)
        }
    }
}

@Composable
fun RemoveItemIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = IpbTheme.colors.black,
        painter = painterResource(id = R.drawable.ic_remove_item),
        contentDescription = null
    )
}

@Preview
@Composable
private fun RemoveItemIconPreview() {
    IpbTheme {
        RemoveItemIcon()
    }
}

@Composable
fun MarkIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = IpbTheme.colors.gray2,
        painter = painterResource(id = R.drawable.ic_mark),
        contentDescription = null
    )
}

@Preview
@Composable
private fun MarkIconPreview() {
    IpbTheme {
        MarkIcon()
    }
}

@Composable
fun BonusesSmallIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.size(20.dp),
        tint = IpbTheme.colors.primary,
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = null
    )
}

@Preview
@Composable
private fun BonusesSmallIconPreview() {
    IpbTheme {
        BonusesSmallIcon()
    }
}

@Composable
fun BonusesLargeIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.size(40.dp),
        tint = IpbTheme.colors.primary,
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = null
    )
}

@Preview
@Composable
private fun BonusesLargeIconPreview() {
    IpbTheme {
        BonusesLargeIcon()
    }
}

@Composable
fun BonusesTinyIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier.size(16.dp),
        tint = IpbTheme.colors.surfaces,
        painter = painterResource(R.drawable.ic_logo),
        contentDescription = null
    )
}

@Preview
@Composable
private fun BonusesTinyIconPreview() {
    IpbTheme {
        BonusesTinyIcon()
    }
}

@Composable
fun SearchIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = IpbTheme.colors.gray2,
        painter = painterResource(R.drawable.ic_search),
        contentDescription = null
    )
}

@Preview
@Composable
private fun SearchIconPreview() {
    IpbTheme {
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
    IpbTheme {
        Mark2Icon()
    }
}

@Composable
fun DownIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = IpbTheme.colors.gray2,
        painter = painterResource(R.drawable.ic_down),
        contentDescription = null
    )
}

@Preview
@Composable
private fun DownIconPreview() {
    IpbTheme {
        DownIcon()
    }
}

@Composable
fun UpIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        tint = IpbTheme.colors.gray2,
        painter = painterResource(R.drawable.ic_up),
        contentDescription = null
    )
}

@Preview
@Composable
private fun UpIconPreview() {
    IpbTheme {
        UpIcon()
    }
}

@Composable
fun EditIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_edit),
        contentDescription = null,
        tint = IpbTheme.colors.gray2
    )
}

@Preview
@Composable
private fun EditIconPreview() {
    IpbTheme {
        EditIcon()
    }
}

@Composable
fun SumIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_sum),
        tint = IpbTheme.colors.gray2,
        contentDescription = null
    )
}

@Composable
@Preview
private fun SumIconPreview() {
    IpbTheme {
        SumIcon()
    }
}

@Composable
fun SuccessIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_plus),
        tint = IpbTheme.colors.primary,
        contentDescription = null
    )
}

@Composable
@Preview
private fun SuccessIconPreview() {
    IpbTheme {
        SuccessIcon()
    }
}

@Composable
fun FailedIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_minus),
        tint = IpbTheme.colors.error,
        contentDescription = null
    )
}

@Composable
@Preview
private fun FailedIconPreview() {
    IpbTheme {
        FailedIcon()
    }
}

@Composable
fun RemainingIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_remaining),
        tint = IpbTheme.colors.gray2,
        contentDescription = null
    )
}

@Composable
@Preview
private fun RemainingIconPreview() {
    IpbTheme {
        RemainingIcon()
    }
}

@Composable
fun AuditsIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_audits),
        tint = IpbTheme.colors.gray2,
        contentDescription = null
    )
}

@Composable
@Preview
private fun AuditsIconPreview() {
    IpbTheme {
        AuditsIcon()
    }
}

@Composable
fun DocumentsIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_docs),
        tint = IpbTheme.colors.gray2,
        contentDescription = null
    )
}

@Composable
@Preview
private fun DocumentsIconPreview() {
    IpbTheme {
        DocumentsIcon()
    }
}

@Composable
fun SendIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_send),
        tint = IpbTheme.colors.black,
        contentDescription = null
    )
}

@Composable
@Preview
private fun SendIconPreview() {
    IpbTheme {
        SendIcon()
    }
}

@Composable
fun ShareIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_share),
        tint = IpbTheme.colors.gray2,
        contentDescription = null
    )
}

@Composable
@Preview
private fun ShareIconPreview() {
    IpbTheme {
        ShareIcon()
    }
}

@Composable
fun CopyIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_copy),
        tint = IpbTheme.colors.gray2,
        contentDescription = null
    )
}

@Composable
@Preview
private fun CopyIconPreview() {
    IpbTheme {
        CopyIcon()
    }
}

@Composable
fun PlusPeopleIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_pluspeople),
        tint = IpbTheme.colors.black,
        contentDescription = null
    )
}

@Composable
@Preview
private fun PlusPeopleIconPreview() {
    IpbTheme {
        PlusPeopleIcon()
    }
}

@Composable
fun ShoppingCartIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_shop_cart),
        tint = IpbTheme.colors.black,
        contentDescription = null
    )
}

@Composable
@Preview
private fun ShoppingCartIconPreview() {
    IpbTheme {
        ShoppingCartIcon()
    }
}

@Composable
fun ArrowRightIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_arrow_right),
        tint = Color(0xFFD9D9D9),
        contentDescription = null
    )
}

@Composable
@Preview
private fun ArrowRightIconPreview() {
    IpbTheme {
        ArrowRightIcon()
    }
}