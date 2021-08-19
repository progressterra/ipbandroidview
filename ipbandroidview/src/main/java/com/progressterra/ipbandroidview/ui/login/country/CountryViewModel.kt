package com.progressterra.ipbandroidview.ui.login.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.progressterra.ipbandroidview.ui.base.BaseViewModel
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.ui.login.country.models.CountryUi
import com.progressterra.ipbandroidview.ui.login.settings.LoginFlowSettings
import com.progressterra.ipbandroidview.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class CountryViewModel(
    private val loginFlowSettings: LoginFlowSettings
) : BaseViewModel() {

    private val allCountry = Country.values().asList()
    private val locale = Locale.getDefault()
    private var filter: String = ""

    private val _country = MutableLiveData(allCountry)
    internal val countryUi: LiveData<List<CountryUi>> = _country.map {
        it.map { country ->
            CountryUi(
                name = country.name,
                selected = country.name == loginFlowSettings.phoneNumberSettings.defaultCountry,
                title = when (locale.language) {
                    "ru" -> country.titleRu
                    else -> country.titleEn
                }
            )
        }
    }

    fun onItemClick(selectedCountry: String) {
        _action.value =
            Event(
                CountryFragmentDirections.actionFragmentCountryToFragmentLogin(
                    loginFlowSettings = loginFlowSettings.copy(
                        phoneNumberSettings = loginFlowSettings.phoneNumberSettings.copy(
                            defaultCountry = selectedCountry
                        )
                    )
                )
            )
    }

    fun changedSearchValue(value: String) {
        // Сохранение фильтра
        filter = value

        // Обновляем список
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _country.postValue(
                    if (filter.isEmpty())
                        allCountry
                    else
                        allCountry.filter { item ->
                            item.titleRu.lowercase().startsWith(value.lowercase())
                                    || item.titleEn.lowercase().startsWith(value.lowercase())
                        }
                )
            }
        }
    }
}