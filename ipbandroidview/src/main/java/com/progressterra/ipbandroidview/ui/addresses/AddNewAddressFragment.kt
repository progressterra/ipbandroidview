package com.progressterra.ipbandroidview.ui.addresses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentAddAddressLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNewAddressFragment : BaseFragment() {
    private lateinit var binding: FragmentAddAddressLibBinding
    override val vm: AddNewAddressViewModel by viewModel()


    private lateinit var adapter: SuggestionsArrayAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAddressLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHeader(R.string.address_add_title)
        setupView()
        setupViewModel()
        setupListeners()
    }

    private fun setupListeners() {
        binding.etMainAddress.addTextChangedListener {
            vm.getSuggestions(it.toString())
        }

        binding.etEntranceNumber.addTextChangedListener {
            vm.setEntranceNumber(it.toString())
        }

        binding.etFlatNumber.addTextChangedListener {
            vm.setFlatNumber(it.toString())
        }

        binding.etFloorNumber.addTextChangedListener {
            vm.setFloor(it.toString())
        }

        binding.btnAddNewAddress.setOnClickListener {
            vm.addNewAddress()
        }
    }

    private fun setupViewModel() {
        vm.apply {
            suggestions.observe(viewLifecycleOwner) {
                adapter.clear()
                adapter.addAll(it.toMutableList())
                adapter.notifyDataSetChanged()
                binding.etMainAddress.showDropDown()
                binding.etMainAddress.setSelection(binding.etMainAddress.text.toString().length)
            }

            popBackStack.observe(viewLifecycleOwner) {
                findNavController().popBackStack(R.id.listOfAddressesFragment, true)
            }
        }
    }

    private fun setupView() {
        adapter = SuggestionsArrayAdapter(requireContext(), mutableListOf())

        binding.apply {
            vm = this@AddNewAddressFragment.vm
            lifecycleOwner = viewLifecycleOwner
            binding.etMainAddress.setAdapter(adapter)
            binding.etMainAddress.setOnClickListener {
                binding.etMainAddress.showDropDown()
            }

            etMainAddress.setOnItemClickListener { parent, view, position, id ->
                adapter.getItem(position)
                    ?.let { this@AddNewAddressFragment.vm.setAddressFromSuggestion(it) }
                binding.etMainAddress.setText(
                    adapter.getItem(position)?.previewOfSuggestion ?: "",
                    false
                )
            }
        }
    }


}