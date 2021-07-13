package com.progressterra.ipbandroidview.ui.login.personal

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.progressterra.ipbandroidapi.localdata.shared_pref.models.SexType
import com.progressterra.ipbandroidapi.remoteData.scrm.models.responses.CitiesListResponse
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.databinding.FragmentPersonalBinding
import com.progressterra.ipbandroidview.ui.base.BaseFragment
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.ui.login.settings.LoginKeys
import com.progressterra.ipbandroidview.ui.login.settings.PersonalSettings
import com.progressterra.ipbandroidview.utils.extensions.afterTextChanged
import com.progressterra.ipbandroidview.utils.ui.adapters.NoPaddingArrayAdapter
import java.util.*

class PersonalFragment : BaseFragment() {

    private val DEFAULT_YEAR = 1995
    private val DEFAULT_MONTH = 1
    private val DEFAULT_DAY = 14

    private val args: PersonalFragmentArgs by navArgs()

    private val personalSettings: PersonalSettings by lazy {
        val loginFlowSettings: LoginFlowSettings = args.loginFlowSettings
        loginFlowSettings.personalSettings
    }

    private val viewModel: PersonalViewModel by viewModels {
        PersonalViewModelFactory(
            personalSettings,
            args.loginFlowSettings.newLoginFlow
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
        initListeners()
        initEditTextValidation()
        applySettings()

        // TODO: 10.06.2021 если приживется, то нужно окультурить
        if (args.loginFlowSettings.newLoginFlow)
            viewModel.popBackStack.observe(viewLifecycleOwner) { event ->
                event.contentIfNotHandled?.let {
                    if (it) {
                        findNavController().popBackStack(R.id.fragmentLogin, true)
                    }
                }
            }
    }

    private fun initListeners() {
        binding.personalData.apply {
            editTextName.afterTextChanged { viewModel.updateFirstName(it) }
            editTextSecondName.afterTextChanged { viewModel.updateLastName(it) }
            editTextEmail.afterTextChanged { viewModel.updateEmail(it) }
            radioButtonMale.setOnClickListener { viewModel.updateSex(SexType.MALE) }
            radioButtonFemale.setOnClickListener { viewModel.updateSex(SexType.FEMALE) }
        }
    }

    private fun applySettings() {
        if (personalSettings.setLastNameAttentionColor) {
            val typedValue = TypedValue()
            val theme = context?.theme
            theme?.resolveAttribute(R.attr.app_textFootnoteAttentionColor, typedValue, true)
            binding.personalData.textViewSecondNameLabel.setTextColor(typedValue.data)
        }
    }

    private fun initEditTextValidation() {
        viewModel.personalInfo.observe(viewLifecycleOwner, {
            binding.personalData.apply {
                setEditTextValidState(editTextName, it.nameIsValid)
                setEditTextValidState(editTextSecondName, it.lastNameIsValid)
                setEditTextValidState(editTextEmail, it.emailIsValid)
                setEditTextValidState(textViewBirthDay, it.birthDateIsValid)
            }
        })
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
                binding.personalData.textViewBirthDay.text =
                    getString(R.string.birthday_date, dayOfMonth, month + 1, year)
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        )
        dialog.updateDate(DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY)
        dialog.datePicker.maxDate = System.currentTimeMillis()

        binding.personalData.textViewBirthDay.setOnClickListener {
            dialog.show()
        }
    }

    private fun setupCitySpinner(citiesList: List<CitiesListResponse.City>) {
        val spinnerAdapter =
            NoPaddingArrayAdapter(
                requireContext(),
                R.layout.item_city,
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
}