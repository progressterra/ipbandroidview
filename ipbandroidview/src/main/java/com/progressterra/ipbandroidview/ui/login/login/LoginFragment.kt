package com.progressterra.ipbandroidview.ui.login.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.databinding.FragmentLoginBinding
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.utils.extensions.argument

class LoginFragment : Fragment() {

    private var container: Int? = null

    private var selectedCountry by argument<String>()

    private val vm: LoginViewModel by viewModels {
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
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner
        vm.nextFragment.observe(viewLifecycleOwner, this::nextFragment)
    }

    private fun nextFragment(fragment: Fragment) {
        activity?.supportFragmentManager?.commit {
            addToBackStack(javaClass.simpleName)
            replace(((view as ViewGroup).parent as View).id, fragment)
        }
    }

    companion object {

        fun newInstance(
            selectedCountry: String? = null
        ): LoginFragment {
            return LoginFragment().apply {
                this.selectedCountry = selectedCountry ?: Country.RUSSIA.name
            }
        }

    }
}