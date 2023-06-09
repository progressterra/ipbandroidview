package com.progressterra.ipbandroidview.features.dialogpicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Citizenship
import com.progressterra.ipbandroidview.shared.theme.IpbTheme
import com.progressterra.ipbandroidview.shared.ui.BrushedText
import com.progressterra.ipbandroidview.shared.ui.ThemedRadioButton
import com.progressterra.ipbandroidview.shared.ui.button.Button

@Composable
fun DialogPicker(
    modifier: Modifier = Modifier,
    state: DialogPickerState,
    useComponent: UseDialogPicker
) {
    if (state.variants.isNotEmpty()) {

        @Composable
        fun RadioListItem(item: Citizenship, selected: Boolean) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ThemedRadioButton(
                    checked = selected,
                    onClick = { useComponent.handle(DialogPickerEvent.Select(item)) }
                )
                BrushedText(
                    text = item.name,
                    style = IpbTheme.typography.body,
                    tint = IpbTheme.colors.primary.asBrush()
                )
            }
        }
        if (state.open) {
            Dialog(
                onDismissRequest = { useComponent.handle(DialogPickerEvent.Close) })
            {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(IpbTheme.colors.surface.asBrush())
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    state.variants.forEach { item ->
                        RadioListItem(
                            item = item,
                            selected = item == state.selected
                        )
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        title = stringResource(R.string.choose),
                        state = state.apply,
                        useComponent = useComponent
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xAA1122)
@Composable
private fun DialogPickerPreview() {
    DialogPicker(
        modifier = Modifier,
        state = DialogPickerState(
            listOf()
        ),
        useComponent = UseDialogPicker.Empty()
    )
}
