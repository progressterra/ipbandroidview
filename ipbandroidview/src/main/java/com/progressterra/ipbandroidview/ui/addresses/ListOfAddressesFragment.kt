package com.progressterra.ipbandroidview.ui.addresses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentListOfAddressLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment


class ListOfAddressesFragment : BaseFragment() {
    private lateinit var binding: FragmentListOfAddressLibBinding
    override val vm: ListOfAddressesViewModel by viewModels()

    private val adapter = AddressesListAdapter {
        vm.setCurrentAddressAsDefault(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListOfAddressLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHeader(R.string.address_list_title)
        setupView()
        setupListeners()
        setupViewModel()
    }

    private fun setupViewModel() {
        vm.listOfAddress.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupListeners() {
        binding.tvAddAddress.setOnClickListener {
            findNavController().navigate(R.id.action_listOfAddressesFragment_to_addNewAddressFragment)
        }
    }

    private fun setupView() {
        binding.rvListOfAddress.adapter = adapter
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = this@ListOfAddressesFragment.vm
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getListOfAddresses()
    }

}