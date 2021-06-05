package com.progressterra.ipbandroidview.ui.login.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentLoginBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.login.LoginSettings
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.ui.login.country.CountryFragment
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged


class LoginFragment : BaseFragment() {

    private var container: Int? = null

    private var onLoginFlowFinishListener: OnLoginFlowFinishListener? = null

//    private var selectedCountry by argument<String>()

    private val args: LoginFragmentArgs by navArgs()

//    private lateinit var loginSettings: LoginSettings

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            selectedCountry = args.selectedCountry,
            onLoginFlowFinishListener = onLoginFlowFinishListener,
            loginSettings = LoginSettings(agreementEnabled = false, footerEnabled = false)
        )
    }

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (this.container == null)
            this.container = container?.id ?: throw Exception("Container is null")
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
//            if (loginSettings.agreementEnabled) {
//                textViewAgreement.apply {
//                    text = Html.fromHtml(getString(R.string.login_agreement_html))
//                    movementMethod = LinkMovementMethod.getInstance()
//                    visibility = View.VISIBLE
//                }
//            }
//            if (loginSettings.footerEnabled) {
//                val resId = loginSettings.footerImageId
//                if (resId != null) {
//                    viewFooterDivider.visibility = View.VISIBLE
//                    ivFooter.visibility = View.VISIBLE
//                    ivFooter.setImageResource(resId)
//                }
//            }
        }

        viewModel.action.observe(viewLifecycleOwner, {
            val action = it.contentIfNotHandled
            if (action != null) {
                findNavController().navigate(action)
            }
        })
    }

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


//    companion object {
//
//        /**
//         *  @param selectedCountry - Country enum name
//         *  @param enableUserAgreement - Включает текст с пользовтелеским соглашением, по дефолту выключено
//         *  @param enableFooterImage - Включает картинку в футере, по дефолту выключено
//         *  @param footerImageRes - id ресурса с картинкой футера
//         */
//        fun newInstance(
//            selectedCountry: String? = null,
//            loginFinishListener: OnLoginFlowFinishListener? = null,
//            enableUserAgreement: Boolean? = null,
//            enableFooterImage: Boolean? = null,
//            footerImageRes: Int? = null
//        ): LoginFragment {
//            return LoginFragment().apply {
//                this.selectedCountry = selectedCountry ?: Country.RUSSIA.name
//                onLoginFlowFinishListener = loginFinishListener
//                loginSettings = LoginSettings(
//                    agreementEnabled = enableUserAgreement ?: false,
//                    footerEnabled = enableFooterImage ?: false,
//                    footerImageId = footerImageRes,
//                    loginFinishListener = loginFinishListener
//                )
//            }
//        }
//
//        internal fun newInstance(
//            selectedCountry: String,
//            loginSettings: LoginSettings
//        ): LoginFragment = LoginFragment().apply {
//            this.selectedCountry = selectedCountry
//            this.loginSettings = loginSettings
//        }
//    }
}