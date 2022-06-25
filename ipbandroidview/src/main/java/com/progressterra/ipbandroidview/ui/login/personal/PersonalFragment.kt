package com.progressterra.ipbandroidview.ui.login.personal

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.SexType
import com.progressterra.ipbandroidapi.remoteData.scrm.models.responses.CitiesListResponse
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentPersonalLibBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.ui.login.settings.LoginKeys
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.extensions.applyIfNotDefault
import com.progressterra.ipbandroidview.utils.ui.adapters.NoPaddingArrayAdapter
import java.util.*

private const val DEFAULT_YEAR = 1995
private const val DEFAULT_MONTH = 1
private const val DEFAULT_DAY = 14

class PersonalFragment : BaseFragment() {

    private val args: PersonalFragmentArgs by navArgs()

    private val viewModel: PersonalViewModel by viewModels {
        PersonalViewModelFactory(args.loginFlowSettings)
    }

    private var _binding: FragmentPersonalLibBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalLibBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel
        viewModel.apply {
            citiesList.observe(viewLifecycleOwner) {
                if (it != null)
                    setupCitySpinner(it)
            }
            toastBundle.observe(viewLifecycleOwner, this@PersonalFragment::showToast)
            action.observe(viewLifecycleOwner, this@PersonalFragment::onAction)
            setFragmentResult.observe(viewLifecycleOwner) {
                val bundle = it.contentIfNotHandled
                bundle?.let {
                    setFragmentResult(LoginKeys.AUTH_BUNDLE, bundle)
                }
            }
        }
        setupDatePickerDialog()
        initInputs(args.loginFlowSettings)
        initEditTextValidation()
        applyHeaderLogo()
        if (args.loginFlowSettings.newLoginFlow) {
            viewModel.popBackStack.observe(viewLifecycleOwner) { event ->
                event.contentIfNotHandled?.let {
                    if (it) {
                        findNavController().popBackStack(R.id.fragmentLogin, true)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initInputs(settings: LoginFlowSettings) {
        binding.personalData.apply {
            if (settings.enableName) {
                etName.afterTextChanged { viewModel.updateFirstName(it) }
            } else {
                etName.isVisible = false
                tvNameLabel.isVisible = false
            }
            if (settings.enableSurname) {
                etSurname.afterTextChanged { viewModel.updateLastName(it) }
            } else {
                etSurname.isVisible = false
                tvSurnameLabel.isVisible = false
            }
            if (settings.enableEmail) {
                etEmail.afterTextChanged { viewModel.updateEmail(it) }
            } else {
                etEmail.isVisible = false
                tvEmailLabel.isVisible = false
            }
            if (!settings.enableBirthDate) {
                etBirthDay.isVisible = false
                tvBirthDayLabel.isVisible = false
            }
            buttonSkip.apply {
                setOnClickListener { viewModel.skipRegistration() }
                isVisible =
                    settings.personalSettings.enableSkipRegistrationButton
            }
            buttonNext.apply {
                viewModel.screenState.observe(viewLifecycleOwner) { setIsLoading(it.isLoading()) }
                viewModel.personalDataIsValid.observe(viewLifecycleOwner) {
                    isEnabled = it
                }
                setOnClickListener { viewModel.addPersonalInfo() }
            }
            if (settings.enableSex) {
                findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Bundle>("buy bundle")
                    ?.observe(
                        viewLifecycleOwner
                    ) { bundle ->
                        when (bundle.getInt("sex")) {
                            0 -> {
                                viewModel.updateSex(SexType.FEMALE)
                                tvSex.text = "Женский"
                            }
                            1 -> {
                                viewModel.updateSex(SexType.MALE)
                                tvSex.text = "Женский"
                            }
                        }
                    }
            } else {
                vSex.isVisible = false
                tvSex.isVisible = false
                tvSexShort.isVisible = false
            }
        }
    }

    private fun applyHeaderLogo() {
        args.loginFlowSettings.personalSettings.logoId.applyIfNotDefault(binding.ivHeader)
    }

    private fun initEditTextValidation() {
        viewModel.personalInfo.observe(viewLifecycleOwner) {
            binding.personalData.apply {
                if (args.loginFlowSettings.enableName) {
                    setEditTextValidState(etName, it.nameIsValid)
                }
                if (args.loginFlowSettings.enableSurname) {
                    setEditTextValidState(etSurname, it.lastNameIsValid)
                }
                if (args.loginFlowSettings.enableEmail) {
                    setEditTextValidState(etEmail, it.emailIsValid)
                }
                if (args.loginFlowSettings.enableBirthDate) {
                    setEditTextValidState(etBirthDay, it.birthDateIsValid)
                }
            }
        }
    }

    private fun setEditTextValidState(view: View, isValid: Boolean) {
        view.background = if (isValid) AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.background_edittext
        ) else AppCompatResources.getDrawable(
            requireContext(),
            R.drawable.background_edittext_invalid
        )
    }

    private fun setupDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val dialog = DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                viewModel.updateBirthdate(dayOfMonth, month + 1, year)
                binding.personalData.etBirthDay.setText(getString(R.string.birthday_date, dayOfMonth, month + 1, year))
                Log.d("BIRTH", "Dialog set bd $dayOfMonth, ${month + 1}, $year")
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )
        dialog.updateDate(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY)
        dialog.datePicker.maxDate = System.currentTimeMillis()

        binding.personalData.etBirthDay.setOnClickListener {
            Log.d("BIRTH", "Dialog show")
            dialog.show()
        }
    }

    private fun setupCitySpinner(citiesList: List<CitiesListResponse.City>) {
        val spinnerAdapter =
            NoPaddingArrayAdapter(
                requireContext(),
                R.layout.item_city_lib,
                citiesList
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }

        binding.personalData.citySpinner.apply {
            adapter = spinnerAdapter
            onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
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
}