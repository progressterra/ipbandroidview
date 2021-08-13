package com.progressterra.ipbandroidview.ui.addresses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.progressterra.ipbandroidapi.remoteData.scrm.models.address.dadata.Suggestion
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentAddAddressBinding
import com.progressterra.ipbandroidview.databinding.ItemSuggestionsBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.utils.ui.adapters.RecyclerViewAdapter

class AddNewAddressFragment : BaseFragment() {
    private lateinit var binding: FragmentAddAddressBinding
    private val viewModel: AddNewAddressViewModel by viewModels()


    private val adapter =
        RecyclerViewAdapter<Suggestion>(
            R.layout.item_suggestions,
            onNormalBind = { suggestionBinding, suggestion ->
                (suggestionBinding as ItemSuggestionsBinding).apply {
                    lifecycleOwner = viewLifecycleOwner
                    item = suggestion
                    suggestionBinding.root.setOnClickListener {
                        viewModel.setAddressFromSuggestion(suggestion)
                        binding.etMainAddress.setText(suggestion.previewOfSuggestion ?: "")
                    }
                }
            })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddAddressBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = this@AddNewAddressFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupView()
        setupListeners()
    }

    private fun setupListeners() {
        binding.etMainAddress.addTextChangedListener {
            viewModel.getSuggestions(it.toString())
        }

        binding.etEntranceNumber.addTextChangedListener {
            viewModel.setEntranceNumber(it.toString())
        }

        binding.etFlatNumber.addTextChangedListener {
            viewModel.setFlatNumber(it.toString())
        }

        binding.etFloorNumber.addTextChangedListener {
            viewModel.setFloor(it.toString())
        }

        binding.btnAddNewAddress.setOnClickListener {
            viewModel.addNewAddress()
        }
    }

    private fun setupViewModel() {
        viewModel.apply {

            suggestions.observe(viewLifecycleOwner) {
                adapter.setItems(it)
            }

            popBackStack.observe(viewLifecycleOwner) {
                findNavController().popBackStack()
            }
        }
    }

    private fun setupView() {
        binding.rvFoundAddresses.adapter = adapter
    }


}