package com.progressterra.ipbandroidview.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ToastBundle

open class BaseFragment : Fragment() {

    internal fun showToast(event: Event<ToastBundle>) {
        val toastBundle = event.contentIfNotHandled
        toastBundle?.let {
            val id = toastBundle.id
            val args = toastBundle.args
            if (id != null) {
                if (args.isEmpty()) {
                    Toast.makeText(context, getString(id), Toast.LENGTH_SHORT).show()
                } else {
                    for (text in args) {
                        Toast.makeText(context, getString(id, text), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                if (args.isNotEmpty()) {
                    for (text in args) {
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    internal fun onAction(event: Event<NavDirections>) {
        val action = event.contentIfNotHandled
        if (action != null) {
            findNavController().navigate(action)
        }
    }
}