package com.progressterra.ipbandroidview.ui.login.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentCountryLibBinding
import com.progressterra.ipbandroidview.databinding.ItemCountryLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.login.country.models.CountryUi
import com.progressterra.ipbandroidview.ui.login.settings.LoginKeys
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.ui.adapters.RecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryFragment : Fragment() {

    private lateinit var binding: FragmentCountryLibBinding

    private val vm: CountryViewModel by viewModel()

    private val adapter =
        RecyclerViewAdapter<CountryUi>(
            R.layout.item_country_lib,
            onNormalBind = { binding, country ->
                (binding as ItemCountryLibBinding).apply {
                    lifecycleOwner = viewLifecycleOwner
                    item = country
                    vm = this@CountryFragment.vm
                    itemCountry.setOnClickListener {
                        setFragmentResult(
                            LoginKeys.AUTH_COUNTRY,
                            bundleOf(LoginKeys.AUTH_NEW_COUNTRY to country.name)
                        )
                        findNavController().popBackStack()
                    }
                }
            })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country_lib, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.countryUi.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            countryRecycle.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = this@CountryFragment.adapter
            }
            countryValue.afterTextChanged(vm::changedSearchValue)
        }
    }
}