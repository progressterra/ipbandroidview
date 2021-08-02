package com.progressterra.ipbandroidview.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ToastBundle

open class BaseFragment : Fragment() {

    internal open val vm by viewModels<BaseViewModel>()

    protected open fun showToast(event: Event<ToastBundle>) {
        val toastBundle = event.contentIfNotHandled
        toastBundle?.let {
            val id = toastBundle.id
            val args = toastBundle.args
            if (id != null) {
                // Ветка если только id передали
                if (args.isEmpty()) {
                    Toast.makeText(context, getString(id), Toast.LENGTH_SHORT).show()
                } else {
                    // Аргументы в качестве vararg передаются
                    Toast.makeText(context, getString(id, *args.toTypedArray()), Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                // Если нет id, значит присутсвует неправильное использование данного класса
                // и единственный адекватный путь это отобразить подряд их
                if (args.isNotEmpty()) {
                    for (text in args) {
                        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    protected open fun onAction(event: Event<NavDirections>) {
        val action = event.contentIfNotHandled
        if (action != null) {
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.action.observe(viewLifecycleOwner, this::onAction)
        vm.toastBundle.observe(viewLifecycleOwner, this::showToast)
    }
}