package com.progressterra.ipbandroidview.utils.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun showKeyboard(context: Context, view: View) {
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}

fun hideKeyboard(context: Context, view: View) {
    val imm =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.HIDE_IMPLICIT_ONLY)
}