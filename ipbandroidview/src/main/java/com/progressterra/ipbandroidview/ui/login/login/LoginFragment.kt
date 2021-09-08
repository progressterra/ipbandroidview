package com.progressterra.ipbandroidview.ui.login.login

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentLoginLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.ui.login.settings.LoginKeys
import com.progressterra.ipbandroidview.ui.login.settings.PhoneNumberSettings
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.applyIfNotDefault


class LoginFragment : BaseFragment() {


    private val args: LoginFragmentArgs by navArgs()
    private val phoneNumberSettings: PhoneNumberSettings by lazy {
        val loginFlowSettings: LoginFlowSettings = args.loginFlowSettings
        loginFlowSettings.phoneNumberSettings
    }

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            selectedCountry = phoneNumberSettings.defaultCountry,
            loginFlowSettings = args.loginFlowSettings
        )
    }

    private lateinit var binding: FragmentLoginLibBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setFragmentResultListener(LoginKeys.AUTH_COUNTRY) { _, bundle ->
            viewModel.updateCountry(
                bundle.getString(
                    LoginKeys.AUTH_NEW_COUNTRY,
                    phoneNumberSettings.defaultCountry
                )
            )
        }

        binding = FragmentLoginLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.apply {
            toastBundle.observe(viewLifecycleOwner, this@LoginFragment::showToast)
            action.observe(viewLifecycleOwner, this@LoginFragment::onAction)

            screenState.observe(viewLifecycleOwner) {
                binding.loginNext.text =
                    if (it == ScreenState.LOADING) "" else getString(R.string.next)
            }
        }
        binding.apply {
            vm = viewModel
            binding.lifecycleOwner = this@LoginFragment
            lifecycleOwner = viewLifecycleOwner
            loginPhone.afterTextChanged(viewModel::checkPhone)
            loginNext.setOnClickListener { viewModel.next(loginPhone.text.toString()) }
            if (phoneNumberSettings.agreementEnabled) {
                textViewAgreement.apply {
                    text = Html.fromHtml(getString(R.string.login_agreement_html))
                    movementMethod = LinkMovementMethod.getInstance()
                    visibility = View.VISIBLE
                }
            }

            phoneNumberSettings.headerImageId.applyIfNotDefault(ivHeader)

            phoneNumberSettings.footerImageId.applyIfNotDefault(ivFooter)

            binding.btnSkip.apply {
                isVisible = phoneNumberSettings.showSkipBtn
                setOnClickListener {
                    findNavController().popBackStack(R.id.fragmentLogin, true)
                }
            }

        }
    }
}
