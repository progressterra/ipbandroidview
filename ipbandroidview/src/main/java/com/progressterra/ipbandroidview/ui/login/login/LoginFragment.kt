package com.progressterra.ipbandroidview.ui.login.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.databinding.FragmentLoginBinding
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.argument

class LoginFragment : Fragment() {

    private var container: Int? = null

    private var selectedCountry by argument<String>()

    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(
            selectedCountry = selectedCountry
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
            nextFragment.observe(viewLifecycleOwner, this@LoginFragment::nextFragment)
            toastStringInt.observe(viewLifecycleOwner, this@LoginFragment::showToastByRes)
            toastText.observe(viewLifecycleOwner, this@LoginFragment::showToast)
        }

        binding.apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            loginPhone.afterTextChanged(viewModel::checkPhone)
            loginNext.setOnClickListener { viewModel.next(loginPhone.text.toString()) }
        }
    }

    private fun nextFragment(event: Event<Fragment>) {
        val fragment = event.contentIfNotHandled
        activity?.supportFragmentManager?.commit {
            addToBackStack(javaClass.simpleName)
            if (fragment != null) {
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

    companion object {

        /**
         *  @param selectedCountry - Country enum name
         */
        fun newInstance(
            selectedCountry: String? = null
        ): LoginFragment {
            return LoginFragment().apply {
                this.selectedCountry = selectedCountry ?: Country.RUSSIA.name
            }
        }

    }
}