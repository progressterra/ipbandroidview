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
import com.progressterra.ipbandroidview.ui.login.LoginSettings
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.ui.login.country.CountryFragment
import com.progressterra.ipbandroidview.utils.DefaultArgsValues
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged


class LoginFragment : BaseFragment() {

    private var onLoginFlowFinishListener: OnLoginFlowFinishListener? = null

    private val args: LoginFragmentArgs by navArgs()
    private val loginSettings: LoginSettings by lazy {
        if (args.loginSettings != null)
            args.loginSettings!!
        else {
            LoginSettings(
                args.enableAgreement,
                args.enableFooter,
                args.footerImageResId
            )
        }
    }

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            selectedCountry = args.selectedCountry,
            onLoginFlowFinishListener = onLoginFlowFinishListener,
            loginSettings = loginSettings
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
            nextFragment.observe(viewLifecycleOwner, this@LoginFragment::onFragment)
            toastBundle.observe(viewLifecycleOwner, this@LoginFragment::showToast)

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

        viewModel.action.observe(viewLifecycleOwner, this::onAction)
    }

    // Старый метод, если аттрибуты будут подтверждены - будет выпилен
    override fun onFragment(event: Event<Fragment>) {
        val fragment = event.contentIfNotHandled
        activity?.supportFragmentManager?.commit {
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            addToBackStack(javaClass.simpleName)
            // костыль, пока не придумал как победить backStack
            if (fragment != null) {
                if (fragment is CountryFragment)
                    add(((view as ViewGroup).parent as View).id, fragment)
                else
                    replace(((view as ViewGroup).parent as View).id, fragment)
            }
        }
    }
}
