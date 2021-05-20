package com.progressterra.ipbandroidview.ui.login.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidview.databinding.FragmentLoginBinding
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import kotlin.properties.Delegates

class LoginFragment : Fragment() {

    private var container by Delegates.notNull<Int>()

    private val vm: LoginViewModel by viewModels {
        LoginViewModelFactory(
            selectedCountry = Country.RUSSIA
        )
    }

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.container = container?.id ?: throw Exception("Container is null")
//        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner
        vm.nextFragment.observe(viewLifecycleOwner, this::nextFragment)
    }

    private fun nextFragment(fragment: Fragment) {
        fragment?.let {
            activity?.supportFragmentManager?.commit {
                replace(container, fragment)
            }
        }
    }

    companion object {

        fun newInstance(
        ): LoginFragment {
            return LoginFragment().apply {
            }
        }

        internal fun newInstance(
            selectedCountry: Country
        ): LoginFragment {
            return LoginFragment().apply {
                vm.selectedCountry = selectedCountry
            }
        }

    }
}