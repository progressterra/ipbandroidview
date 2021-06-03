package com.progressterra.ipbandroidview.ui.login.personal

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.SexType
import com.progressterra.ipbandroidapi.remoteData.scrm.models.responses.CitiesListResponse
import com.progressterra.ipbandroidview.databinding.FragmentPersonalBinding
import com.progressterra.ipbandroidview.ui.login.OnLoginFlowFinishListener
import com.progressterra.ipbandroidview.utils.Event
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import java.util.*

class PersonalFragment : Fragment() {

    private var onLoginFlowFinishListener: OnLoginFlowFinishListener? = null

    private val viewModel: PersonalViewModel by viewModels {
        PersonalViewModelFactory(
            onLoginFlowFinishListener
        )
    }

    private lateinit var binding: FragmentPersonalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPersonalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel


        viewModel.apply {
            citiesList.observe(viewLifecycleOwner) {
                setupCitySpinner(it)
            }
            toastTextRes.observe(viewLifecycleOwner, this@PersonalFragment::showToastFromRes)
        }



        setupDatePickerDialog()
        initListeners()
    }

    private fun initListeners() {
        binding.personalData.editTextName.afterTextChanged { viewModel.updateFirstName(it) }
        binding.personalData.editTextSecondName.afterTextChanged { viewModel.updateLastName(it) }
        binding.personalData.editTextEmail.afterTextChanged { viewModel.updateEmail(it) }
        binding.personalData.radioButtonMale.setOnClickListener { viewModel.updateSex(SexType.MALE) }
        binding.personalData.radioButtonFemale.setOnClickListener { viewModel.updateSex(SexType.FEMALE) }
    }

    private fun setupDatePickerDialog() {
        val calendar = Calendar.getInstance()

        val dialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                viewModel.updateBirthdate(dayOfMonth, month, year)
                binding.personalData.textViewBirthDay.text = "$dayOfMonth.$month.$year"
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )

        binding.personalData.textViewBirthDay.setOnClickListener {
            dialog.show()
        }
    }

    private fun showToastFromRes(event: Event<Int>) {
        event.contentIfNotHandled?.let {
            Toast.makeText(context, getString(it), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupCitySpinner(citiesList: List<CitiesListResponse.City>) {
        val spinnerAdapter =
            ArrayAdapter(
                requireContext(), android.R.layout.simple_spinner_dropdown_item,
                citiesList
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        binding.personalData.citySpinner.apply {
            adapter = spinnerAdapter
            onItemSelectedListener =
                (object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewModel.updateCity(citiesList[position])
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        viewModel.updateCity(citiesList[0])
                    }
                })
        }
    }

    companion object {
        fun newInstance(
            onLoginFlowFinishListener: OnLoginFlowFinishListener?
        ) = PersonalFragment().apply {
            this.onLoginFlowFinishListener = onLoginFlowFinishListener
        }
    }

}