package com.progressterra.ipbandroidview.utils.ui.binding

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("setValidationStatus")
fun setValidationStatus(editText: EditText, isValid: Boolean) {
    if (!isValid) editText.backgroundTintList = ColorStateList.valueOf(Color.RED)
    else {
        editText.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
    }
}