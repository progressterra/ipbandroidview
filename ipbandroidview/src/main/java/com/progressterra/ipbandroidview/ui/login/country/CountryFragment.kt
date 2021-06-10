package com.progressterra.ipbandroidview.ui.login.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentCountryBinding
import com.progressterra.ipbandroidview.databinding.ItemCountryBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.login.country.models.CountryUi
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.ui.adapters.RecyclerViewAdapter

class CountryFragment : BaseFragment() {

    private val args: CountryFragmentArgs by navArgs()

    private lateinit var binding: FragmentCountryBinding

    private val vm: CountryViewModel by viewModels {
        CountryViewModelFactory(
            loginFlowSettings = args.loginFlowSettings
        )
    }

    private val adapter =
        RecyclerViewAdapter<CountryUi>(R.layout.item_country, onNormalBind = { binding, country ->
            (binding as ItemCountryBinding).apply {
                lifecycleOwner = viewLifecycleOwner
                item = country
                vm = this@CountryFragment.vm
            }
        })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.countryUi.observe(viewLifecycleOwner, {
            adapter.setItems(it)
        })
        vm.action.observe(viewLifecycleOwner, this::onAction)

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