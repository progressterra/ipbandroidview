package com.progressterra.ipbandroidview.utils.ui.binding

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.progressterra.ipbandroidview.R

@BindingAdapter("app:setBonusTransactionType")
internal fun setBonusTransactionType(textView: TextView, type: Int) {
    when (type) {
        0 -> textView.text = textView.context.getText(R.string.type_operation_charging)
        1 -> textView.text = textView.context.getText(R.string.type_operation_spending)
        2 -> textView.text = textView.context.getText(R.string.type_operation_charging)
    }
}

@BindingAdapter("app:setColorAndTextFormattingByQuantity")
internal fun setColorAndTextFormattingByQuantity(textView: TextView, quantity: Int) {
    if (quantity <= 0) {
        textView.setTextColor(Color.RED)
        textView.text = String.format(
            textView.context.getString(R.string.negative_sum), kotlin.math.abs(quantity)
        )
    } else {
        textView.setTextColor(Color.GREEN)
        textView.text = String.format(
            textView.context.getString(R.string.positive_sum), kotlin.math.abs(quantity)
        )
    }
}