package com.progressterra.ipbandroidview.ui.base

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ToastBundle

open class BaseFragment : Fragment() {

    internal open fun showToast(event: Event<ToastBundle>) {
        val toastBundle = event.contentIfNotHandled
        if (toastBundle != null) {
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

    internal open fun onFragment(event: Event<Fragment>) {
        val fragment = event.contentIfNotHandled
        activity?.supportFragmentManager?.commit {
            if (fragment != null) {
                replace(((view as ViewGroup).parent as View).id, fragment)
            }
        }
    }
}