package com.progressterra.ipbandroidview


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBarWithBackNav(title: String, onBack: () -> Unit) {
    TopAppBar(
        backgroundColor = MaterialTheme.appColors.surfaces,
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBack() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(id = R.string.navigate_back),
                        tint = MaterialTheme.appColors.gray1
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = title,
                    color = MaterialTheme.appColors.black,
                    style = MaterialTheme.appTypography.title,
                    maxLines = 1,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun TopAppBarWithBackNavPreview() {
    AppTheme {
        TopAppBarWithBackNav(title = "Some mock title", onBack = {})
    }
}