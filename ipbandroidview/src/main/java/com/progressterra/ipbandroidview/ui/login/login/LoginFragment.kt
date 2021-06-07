package com.progressterra.ipbandroidview.ui.login.login

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentLoginBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.ui.login.settings.PhoneNumberSettings
import com.progressterra.ipbandroidview.utils.DefaultArgsValues
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged


class LoginFragment : BaseFragment() {

    private var onLoginFlowFinishListener: OnLoginFlowFinishListener? = null

    private val args: LoginFragmentArgs by navArgs()
    private val loginSettings: PhoneNumberSettings by lazy {
        val loginFlowSettings: LoginFlowSettings = args.loginFlowSettings
        loginFlowSettings.phoneNumberSettings
    }

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            selectedCountry = loginSettings.defaultCountry,
            onLoginFlowFinishListener = onLoginFlowFinishListener,
            loginFlowSettings = args.loginFlowSettings
        )
    }

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
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
            if (loginSettings.agreementEnabled) {
                textViewAgreement.apply {
                    text = Html.fromHtml(getString(R.string.login_agreement_html))
                    movementMethod = LinkMovementMethod.getInstance()
                    visibility = View.VISIBLE
                }
            }
            if (loginSettings.footerEnabled) {
                val resId = loginSettings.footerImageId
                if (resId != DefaultArgsValues.DEFAULT_RES) {
                    viewFooterDivider.visibility = View.VISIBLE
                    ivFooter.visibility = View.VISIBLE
                    ivFooter.setImageResource(resId)
                }
            }
        }
    }
}
