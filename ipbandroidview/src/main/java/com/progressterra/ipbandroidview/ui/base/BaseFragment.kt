package com.progressterra.ipbandroidview.ui.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.ToastBundle

@Deprecated(replaceWith = ReplaceWith("BaseBindingFragment"), message = "")
open class BaseFragment : Fragment() {

    protected open val vm by viewModels<BaseViewModel>()

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

    protected fun SResult.Toast.handleToastResult() {
        when (message) {
            is String -> message as String
            is Int -> getString(message as Int)
            else -> null
        }?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    open fun setupHeader(
        title: Int? = null,
        backVisible: Boolean = true
    ) {
        try {
            val back = requireActivity().findViewById<ImageView>(R.id.iv_back_header)

            if (backVisible) {
                back.visibility = View.VISIBLE
                back.setOnClickListener {
                    findNavController().popBackStack()
                }
            } else {
                back.visibility = View.GONE
            }

            title?.let {
                requireActivity().findViewById<TextView>(R.id.tv_title_header).text =
                    resources.getString(title)
            }
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "$e")
        }
    }

    open fun setupHeader(
        title: String? = null,
        backVisible: Boolean = true
    ) {
        try {
            val back = requireActivity().findViewById<ImageView>(R.id.iv_back_header)

            if (backVisible) {
                back.visibility = View.VISIBLE
                back.setOnClickListener {
                    findNavController().popBackStack()
                }
            } else {
                back.visibility = View.GONE
            }

            title?.let {
                requireActivity().findViewById<TextView>(R.id.tv_title_header).text = title
            }
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "$e")
        }
    }
}