package com.progressterra.ipbandroidview.ui.login.confirm

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.databinding.FragmentConfirmBinding
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.argument


internal class ConfirmFragment : Fragment() {

    private var selectedCountry by argument<String>()
    private var phoneNumber by argument<String>()
    private var onLoginFlowFinishListener: OnLoginFlowFinishListener? = null


    private val viewModel: ConfirmViewModel by viewModels {
        ConfirmViewModelFactory(
            selectedCountry,
            phoneNumber,
            onLoginFlowFinishListener
        )
    }


    private lateinit var binding: FragmentConfirmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.afterTextChanged(viewModel::checkIt)
        binding.editText.requestFocus()

        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.editText, InputMethodManager.SHOW_IMPLICIT)

        binding.editText.afterTextChanged {
            setupDigitItemParameters(it.getOrNull(0), binding.digit1)
            setupDigitItemParameters(it.getOrNull(1), binding.digit2)
            setupDigitItemParameters(it.getOrNull(2), binding.digit3)
            setupDigitItemParameters(it.getOrNull(3), binding.digit4)
        }

        binding.vm = viewModel
        binding.lifecycleOwner = this
        viewModel.fragment.observe(viewLifecycleOwner, this::onFragment)
    }


    private fun setupDigitItemParameters(inputString: Char?, textView: TextView) {
        if (inputString == null) {
            textView.text = ""
            textView.backgroundTintList = ColorStateList.valueOf(Color.RED)
        } else {
            textView.text = inputString.toString()
            textView.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
        }
    }

    private fun onFragment(event: Event<Fragment>) {
        val fragment = event.contentIfNotHandled
        activity?.supportFragmentManager?.commit {
            if (fragment != null) {
                replace(((view as ViewGroup).parent as View).id, fragment)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.editText, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    companion object {
        fun newInstance(
            selectedCountry: String,
            phoneNumber: String,
            onLoginFlowFinishListener: OnLoginFlowFinishListener?
        ): ConfirmFragment =
            ConfirmFragment().apply {
                this.selectedCountry = selectedCountry
                this.phoneNumber = phoneNumber
                this.onLoginFlowFinishListener = onLoginFlowFinishListener
            }
    }
}


