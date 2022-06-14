package com.progressterra.ipbandroidview.ui.login.confirm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentConfirmLibBinding
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
            phoneNumber = args.phoneNumber,
            loginFlowSettings = args.loginFlowSettings,
            newLoginFlow = args.loginFlowSettings.newLoginFlow
        )
    }

    private lateinit var binding: FragmentConfirmLibBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfirmLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editText.afterTextChanged(viewModel::checkIt)
        binding.editText.requestFocus()
        showKeyboard(requireActivity(), binding.editText)
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
            confirmSettings.logoImageResId.applyIfNotDefault(confirmLogoIv)
            confirmSettings.footerImageResId.applyIfNotDefault(confirmBannerIv)
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
        hideKeyboard(requireActivity(), binding.editText)
        super.onStop()
    }
}


