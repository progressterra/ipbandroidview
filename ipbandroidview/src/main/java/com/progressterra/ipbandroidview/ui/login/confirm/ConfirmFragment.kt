package com.progressterra.ipbandroidview.ui.login.confirm

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.databinding.FragmentConfirmBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.bonuses_details.tabs.ColorsPalette
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.hideKeyboard
import com.progressterra.ipbandroidview.utils.extensions.showKeyboard


class ConfirmFragment : BaseFragment() {

    private val args: ConfirmFragmentArgs by navArgs()

    private val viewModel: ConfirmViewModel by viewModels {
        ConfirmViewModelFactory(
            args.phoneNumber
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
        showKeyboard(requireContext(), binding.editText)

        binding.vm = viewModel
        binding.lifecycleOwner = this
        setupViewModel()
        setupCodeBlockParameters()
    }

    private fun setupViewModel() {
        viewModel.apply {
            action.observe(viewLifecycleOwner, this@ConfirmFragment::onAction)
            toastBundle.observe(viewLifecycleOwner, this@ConfirmFragment::showToast)
        }
//        viewModel.fragment.observe(viewLifecycleOwner, this::onFragment)
//        viewModel.clearConfirmCode.observe(viewLifecycleOwner) {
//            cleanBlockCode()
//        }
    }

    private fun cleanBlockCode() {
        binding.editText.text.clear()
    }

    private fun setupCodeBlockParameters() {
        binding.editText.afterTextChanged {
            setDigitItemParameters(it.getOrNull(0), binding.digit1)
            setDigitItemParameters(it.getOrNull(1), binding.digit2)
            setDigitItemParameters(it.getOrNull(2), binding.digit3)
            setDigitItemParameters(it.getOrNull(3), binding.digit4)
        }
    }

    private fun setDigitItemParameters(inputString: Char?, textView: TextView) {
        if (inputString == null) {
            textView.text = ""
            ColorsPalette.secondaryColor?.let {
                textView.backgroundTintList = ColorStateList.valueOf(it)
            }

        } else {
            textView.text = inputString.toString()
            ColorsPalette.mainColor?.let {
                textView.backgroundTintList = ColorStateList.valueOf(it)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard(requireContext(), binding.editText)
    }
}


