package com.progressterra.ipbandroidview.composable.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.progressterra.ipbandroidview.composable.ForwardIcon
import com.progressterra.ipbandroidview.shared.ui.niceClickable
import com.progressterra.ipbandroidview.entities.CategoryWithSubcategories
import com.progressterra.ipbandroidview.shared.theme.IpbTheme

@Composable
fun SubCategoryComponent(
    modifier: Modifier = Modifier,
    state: CategoryWithSubcategories,
    openCategory: (CategoryWithSubcategories) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(IpbTheme.shapes.medium)
            .background(IpbTheme.colors.surfaces)
            .niceClickable { openCategory(state) }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.name,
            color = IpbTheme.colors.black,
            style = IpbTheme.typography.primary
        )
        ForwardIcon()
    }
}

@Preview
@Composable
private fun SubCategoryPreview() {
    IpbTheme {
        SubCategoryComponent(
            modifier = Modifier.width(300.dp),
            state = CategoryWithSubcategories(
                id = "",
                name = "Some cool category",
                subCategories = listOf(),
                hasNext = false
            ), openCategory = {}
        )
    }
}