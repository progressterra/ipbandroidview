package com.progressterra.ipbandroidview.ui.login.country

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.progressterra.ipbandroidview.ui.login.LoginSettings
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import com.progressterra.ipbandroidview.ui.login.country.models.CountryUi
import com.progressterra.ipbandroidview.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

internal class CountryViewModel(
    private var selectedCountry: String,
    private val loginSettings: LoginSettings
) : ViewModel() {

    private val allCountry = Country.values().asList()
    private val locale = Locale.getDefault()
    private var filter: String = ""

    private val _country = MutableLiveData(allCountry)
    val countryUi: LiveData<List<CountryUi>> = _country.map {
        it.map { country ->
            CountryUi(
                name = country.name,
                selected = country.name == selectedCountry,
                title = when (locale.language) {
                    "ru" -> country.titleRu
                    else -> country.titleEn
                }
            )
        }
    }

    private val _action = MutableLiveData<Event<NavDirections>>()
    val action: LiveData<Event<NavDirections>> = _action

    fun onItemClick(selectedCountry: String) {
        _action.value =
            Event(CountryFragmentDirections.actionFragmentCountryToFragmentLogin(selectedCountry))
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