package com.progressterra.ipbandroidview.ui.login.login

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentLoginLibBinding
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.ui.login.settings.LoginKeys
import com.progressterra.ipbandroidview.ui.login.settings.PhoneNumberSettings
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.applyIfNotDefault
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginFragment : Fragment() {

    private val args: LoginFragmentArgs by navArgs()

    private val phoneNumberSettings: PhoneNumberSettings by lazy {
        val loginFlowSettings: LoginFlowSettings = args.loginFlowSettings
        loginFlowSettings.phoneNumberSettings
    }

    private val viewModel: LoginViewModel by viewModel(parameters = {
        parametersOf(
            phoneNumberSettings.defaultCountry,
            args.loginFlowSettings
        )
    })

    private var _binding: FragmentLoginLibBinding? = null
    private val binding: FragmentLoginLibBinding get() = _binding!!

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

        _binding = FragmentLoginLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginPhone.afterTextChanged(viewModel::checkPhone)
        binding.loginNext.setOnClickListener { viewModel.next(binding.loginPhone.text.toString()) }

        phoneNumberSettings.headerImageId.applyIfNotDefault(binding.ivHeader)

        phoneNumberSettings.footerImageId.applyIfNotDefault(binding.ivFooter)

        binding.btnSkip.apply {
            isVisible = phoneNumberSettings.showSkipBtn
            setOnClickListener {
                findNavController().popBackStack()
            }
        }


        applyAgreements()
    }


    private fun applyAgreements() {
        val policy = phoneNumberSettings.privacyPolicy
        val terms = phoneNumberSettings.termsOfUse

        when {
            policy != null && terms != null -> setTermsAndPolicy(policy, terms)
            policy != null -> setPolicy(policy)
            terms != null -> setTerms(terms)
        }
    }

    private fun setPolicy(policyUrl: String) {
        val ss =
            SpannableString(resources.getString(R.string.login_policy))

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                findNavController().navigate(
                    LoginFragmentDirections.toWebViewDialog(policyUrl)
                )
            }
        }

        ss.setSpan(clickableSpan, 39, 67, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.theme_color, null)),
            39,
            67,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.textViewAgreement.apply {
            movementMethod = LinkMovementMethod.getInstance()
            text = ss
            visibility = View.VISIBLE
        }
    }

    private fun setTerms(termsUrl: String) {
        val ss =
            SpannableString(resources.getString(R.string.login_terms))

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                findNavController().navigate(
                    LoginFragmentDirections.toWebViewDialog(termsUrl)
                )
            }
        }

        ss.setSpan(clickableSpan, 40, 68, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.theme_color, null)),
            40,
            68,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.textViewAgreement.apply {
            movementMethod = LinkMovementMethod.getInstance()
            text = ss
            visibility = View.VISIBLE
        }
    }

    private fun setTermsAndPolicy(policyUrl: String, termsUrl: String) {
        val ss =
            SpannableString(resources.getString(R.string.login_terms_and_policy))

        val termsSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                findNavController().navigate(
                    LoginFragmentDirections.toWebViewDialog(termsUrl)
                )
            }
        }

        val policySpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                findNavController().navigate(
                    LoginFragmentDirections.toWebViewDialog(policyUrl)
                )
            }
        }

        ss.setSpan(termsSpan, 60, 88, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(policySpan, 106, 134, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        ss.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.theme_color, null)),
            60,
            88,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        ss.setSpan(
            ForegroundColorSpan(resources.getColor(R.color.theme_color, null)),
            106,
            134,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        binding.textViewAgreement.apply {
            movementMethod = LinkMovementMethod.getInstance()
            text = ss
            visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}