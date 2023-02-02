package com.progressterra.ipbandroidview.composable

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
import com.progressterra.ipbandroidview.composable.utils.niceClickable
import com.progressterra.ipbandroidview.model.store.CategoryWithSubcategories
import com.progressterra.ipbandroidview.theme.AppTheme


@Composable
fun SubCategory(
    modifier: Modifier = Modifier,
    state: CategoryWithSubcategories,
    openCategory: (CategoryWithSubcategories) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(AppTheme.shapes.medium)
            .background(AppTheme.colors.surfaces)
            .niceClickable(onClick = { openCategory(state) })
            .padding(AppTheme.dimensions.large),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = state.name,
            color = AppTheme.colors.black,
            style = AppTheme.typography.text
        )
        ForwardIcon()
    }
}

@Preview
@Composable
private fun SubCategoryPreview() {
    AppTheme {
        SubCategory(
            modifier = Modifier.width(300.dp),
            state = CategoryWithSubcategories(
                    id = "", name = "Some cool category", subCategories = listOf(), hasNext = false
            ), openCategory = {}
        )
    }
}