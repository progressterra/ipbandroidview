package com.progressterra.ipbandroidview.utils.ui.binding

import android.widget.EditText
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.progressterra.ipbandroidview.R

@BindingAdapter("isValid")
fun editTextIsValid(editText: EditText, isValid: Boolean) {
    editText.background =
        if (isValid)
            AppCompatResources.getDrawable(editText.context, R.drawable.background_edittext)
        else
            AppCompatResources.getDrawable(editText.context, R.drawable.background_edittext_invalid)
}