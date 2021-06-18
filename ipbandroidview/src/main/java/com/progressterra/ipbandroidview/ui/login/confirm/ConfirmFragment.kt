package com.progressterra.ipbandroidview.ui.login.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentConfirmBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.login.settings.ConfirmCodeSettings
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.ui.login.settings.LoginKeys
import com.progressterra.ipbandroidview.utils.extensions.*


class ConfirmFragment : BaseFragment() {

    private val args: ConfirmFragmentArgs by navArgs()

    private val confirmSettings: ConfirmCodeSettings by lazy {
        val loginFlowSettings: LoginFlowSettings = args.loginFlowSettings
        loginFlowSettings.confirmCodeSettings
    }

    private val viewModel: ConfirmViewModel by viewModels {
        ConfirmViewModelFactory(
            args.phoneNumber,
            args.loginFlowSettings,
            args.loginFlowSettings.newLoginFlow
        )
    }

    private lateinit var binding: FragmentConfirmBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        applySettings()

        // TODO: 10.06.2021 если приживется идея, то нужно окультурить
        if (args.loginFlowSettings.newLoginFlow) {
            viewModel.popBackStack.observe(viewLifecycleOwner) { event ->
                event.contentIfNotHandled?.let {
                    if (it) {
                        findNavController().popBackStack(R.id.fragmentLogin, true)
                    }
                }
            }
        }
    }

    private fun applySettings() {
        binding.apply {
            if (confirmSettings.enableLogo && confirmSettings.logoImageResId.notDefaultArg()) {
                confirmLogoIv.setImageResource(confirmSettings.logoImageResId)
                confirmLogoIv.isVisible = confirmSettings.enableLogo
            }
            if (confirmSettings.enableFooter && confirmSettings.footerImageResId.notDefaultArg()) {
                confirmBannerIv.setImageResource(confirmSettings.footerImageResId)
                groupFooter.isVisible = confirmSettings.enableFooter
            }
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            action.observe(viewLifecycleOwner, this@ConfirmFragment::onAction)
            toastBundle.observe(viewLifecycleOwner, this@ConfirmFragment::showToast)
            clearConfirmCode.observe(viewLifecycleOwner) {
                cleanBlockCode()
            }
            setFragmentResult.observe(viewLifecycleOwner) {
                val bundle = it.contentIfNotHandled
                bundle?.let {
                    setFragmentResult(LoginKeys.AUTH_BUNDLE, bundle)
                }
            }
        }
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
            textView.isEnabled = false

        } else {
            textView.text = inputString.toString()
            textView.isEnabled = true
        }
    }

    override fun onStop() {
        super.onStop()
        hideKeyboard(requireContext(), binding.editText)
    }
}


