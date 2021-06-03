package com.progressterra.ipbandroidview.ui.login.login

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.*
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentLoginBinding
import com.progressterra.ipbandroidview.ui.bonuses_details.tabs.ColorsPalette
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.ui.login.country.CountryFragment
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.ScreenState
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.argument
import com.progressterra.ipbandroidview.utils.extensions.argumentNullable


class LoginFragment : Fragment() {

    private var container: Int? = null

    private var onLoginFlowFinishListener: OnLoginFlowFinishListener? = null

    private var selectedCountry by argument<String>()

    private var mainColor: String? by argumentNullable()
    private var secondaryColor: String? by argumentNullable()
    private var mainTextColor: String? by argumentNullable()
    private var secondaryTextColor: String? by argumentNullable()
    private var companyLogoRes: Int? by argumentNullable()
    private var loginImageRes: Int? by argumentNullable()
    private var confirmImageRes: Int? by argumentNullable()

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            selectedCountry = selectedCountry,
            onLoginFlowFinishListener = onLoginFlowFinishListener
        )
    }

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        getValuesFromArguments()
        if (this.container == null)
            this.container = container?.id ?: throw Exception("Container is null")
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.apply {
            nextFragment.observe(viewLifecycleOwner, this@LoginFragment::nextFragment)
            toastStringInt.observe(viewLifecycleOwner, this@LoginFragment::showToastByRes)
            toastText.observe(viewLifecycleOwner, this@LoginFragment::showToast)

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
            textViewAgreement.text =
                Html.fromHtml(getString(R.string.login_agreement_html))
            textViewAgreement.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun nextFragment(event: Event<Fragment>) {
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


    private fun showToastByRes(event: Event<Int>) {
        val stringRes = event.contentIfNotHandled
        if (stringRes != null)
            Toast.makeText(context, getString(stringRes, viewModel.phoneCode), Toast.LENGTH_SHORT)
                .show()

    }

    private fun showToast(event: Event<String>) {
        val text = event.contentIfNotHandled
        if (text != null)
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }


    private fun getValuesFromArguments() {
        mainColor?.let {
            ColorsPalette.mainColor = Color.parseColor(it)
        }

        secondaryColor?.let {
            ColorsPalette.secondaryColor = Color.parseColor(it)
        }

        secondaryTextColor?.let {
            ColorsPalette.secondaryColor = Color.parseColor(it)
        }

        mainTextColor?.let {
            ColorsPalette.mainColor = Color.parseColor(it)
        }

        companyLogoRes?.let {
            ColorsPalette.companyLogoImageRes = it
        }

        loginImageRes?.let {
            ColorsPalette.loginImageRes = it
        }

        confirmImageRes?.let {
            ColorsPalette.confirmImageRes = it
        }

    }

    companion object {

        /**
         *  @param selectedCountry - Country enum name
         */
        fun newInstance(
            selectedCountry: String? = null,
            loginFinishListener: OnLoginFlowFinishListener? = null,
            mainColor: String? = null,
            secondaryColor: String? = null,
            mainTextColor: String? = null,
            secondaryTextColor: String? = null,
            companyLogoRes: Int? = null,
            loginLogoRes: Int? = null,
            confirmLogoRes: Int? = null

        ): LoginFragment {
            return LoginFragment().apply {
                this.selectedCountry = selectedCountry ?: Country.RUSSIA.name
                this.onLoginFlowFinishListener = loginFinishListener

                mainColor?.let {
                    this.mainColor = it
                }

                mainTextColor?.let {
                    this.mainTextColor = it
                }

                secondaryTextColor?.let {
                    this.secondaryTextColor
                }

                secondaryColor?.let {
                    this.secondaryColor = it
                }

                companyLogoRes?.let {
                    this.companyLogoRes = it
                }

                loginLogoRes?.let {
                    this.loginImageRes = it
                }

                confirmLogoRes.let {
                    this.confirmImageRes = it
                }
            }
        }
    }
}