package com.progressterra.ipbandroidview.ui.addresses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentListOfAddressBinding
import com.progressterra.ipbandroidview.databinding.ItemAddressBinding
import com.progressterra.ipbandroidview.ui.addresses.models.AddressUI
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.utils.ui.adapters.RecyclerViewAdapter


class ListOfAddressesFragment : BaseFragment() {
    private lateinit var binding: FragmentListOfAddressBinding
    override val vm: ListOfAddressesViewModel by viewModels()

    private val adapter =
        RecyclerViewAdapter<AddressUI>(
            R.layout.item_address,
            onNormalBind = { addressBinding, address ->
                (addressBinding as ItemAddressBinding).apply {
                    lifecycleOwner = viewLifecycleOwner
                    item = address
                    addressBinding.root.setOnClickListener {
                        vm.setCurrentAddressAsDefault(address)
                    }
                }
            })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfAddressBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ListOfAddressesFragment
            vm = this@ListOfAddressesFragment.vm
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvListOfAddress.adapter = adapter
        binding.rvListOfAddress.itemAnimator = null

        binding.tvAddAddress.setOnClickListener {
            findNavController().navigate(R.id.action_listOfAddressesFragment_to_addNewAddressFragment)
        }

        vm.listOfAddress.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getListOfAddresses()
    }

}