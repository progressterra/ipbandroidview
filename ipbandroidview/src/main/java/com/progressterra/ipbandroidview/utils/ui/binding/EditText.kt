package com.progressterra.ipbandroidview.utils.ui.binding

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.progressterra.ipbandroidview.R

@BindingAdapter("isValid")
fun editTextIsValid(editText: EditText, isValid: Boolean) {
    editText.backgroundTintList = ColorStateList.valueOf(
        if (isValid) {
            Color.parseColor("#DEDEDE")
        } else {
            Color.parseColor("#B3241D")
        }
    )
}