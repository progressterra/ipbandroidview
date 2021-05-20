package com.progressterra.ipbandroidview.ui.login.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentCountryBinding
import com.progressterra.ipbandroidview.databinding.ItemCountryBinding
import com.progressterra.ipbandroidview.models.ui.CountryUi
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.argument
import com.progressterra.ipbandroidview.utils.ui.adapters.RecyclerViewAdapter

internal class CountryFragment : Fragment() {

    private var selectedCountry by argument<Country>()

    private lateinit var binding: FragmentCountryBinding
    private val vm: CountryViewModel by viewModels {
        CountryViewModelFactory(
            selectedCountry = selectedCountry
        )
    }

    private val adapter =
        RecyclerViewAdapter<CountryUi>(R.layout.item_country, onNormalBind = { binding, country ->
            (binding as ItemCountryBinding).apply {
                lifecycleOwner = viewLifecycleOwner
                textViewCountryName.text = country.title
                groupSelected.visibility = if (country.selected) View.VISIBLE else View.GONE
                itemCountry.setOnClickListener { vm.onItemClick(country.name) }
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
            adapter.notifyDataSetChanged()
        })
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            countryRecycle.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = this@CountryFragment.adapter
            }
            countryValue.afterTextChanged { vm.changedSearchValue(it) }
        }
    }

    companion object {
        fun newInstance(
            selectedCountry: Country
        ): CountryFragment {
            return CountryFragment().apply {
                this.selectedCountry = selectedCountry
            }
        }
    }
}