package com.progressterra.ipbandroidview.ui.login.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentCountryBinding
import com.progressterra.ipbandroidview.databinding.ItemCountryBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.login.LoginSettings
import com.progressterra.ipbandroidview.ui.login.country.models.CountryUi
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.argument
import com.progressterra.ipbandroidview.utils.ui.adapters.RecyclerViewAdapter

internal class CountryFragment : BaseFragment() {

    private var container: Int? = null

    private var selectedCountry by argument<String>()

//    private lateinit var loginSettings: LoginSettings

    private lateinit var binding: FragmentCountryBinding
    private val vm: CountryViewModel by viewModels {
        CountryViewModelFactory(
            selectedCountry = selectedCountry,
            loginSettings = LoginSettings(agreementEnabled = false, footerEnabled = false)
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
        if (this.container == null)
            this.container = container?.id ?: throw Exception("Container is null")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.countryUi.observe(viewLifecycleOwner, {
            adapter.setItems(it)
        })
        vm.nextFragment.observe(viewLifecycleOwner, this::onFragment)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            countryRecycle.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = this@CountryFragment.adapter
            }
            countryValue.afterTextChanged(vm::changedSearchValue)
        }
    }

//    companion object {
//        fun newInstance(
//            selectedCountry: String,
//            loginSettings: LoginSettings
//        ): CountryFragment {
//            return CountryFragment().apply {
//                this.selectedCountry = selectedCountry
//                this.loginSettings = loginSettings
//            }
//        }
//    }
}