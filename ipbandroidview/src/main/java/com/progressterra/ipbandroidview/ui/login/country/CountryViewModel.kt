package com.progressterra.ipbandroidview.ui.login.country

import android.util.Log
import androidx.lifecycle.*
import com.progressterra.ipbandroidview.models.ui.CountryUi
import com.progressterra.ipbandroidview.ui.login.country.enums.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

internal class CountryViewModel(private var selectedCountry: Country) : ViewModel() {

    private val allCountry = Country.values().asList()
    private val locale = Locale.getDefault()
    private var filter: String = ""

    private val _country = MutableLiveData(allCountry)
    val countryUi: LiveData<List<CountryUi>> = _country.map {
        it.map { country ->
            CountryUi(
                name = country.name,
                selected = country == selectedCountry,
                title = when (locale.language) {
                    "ru" -> country.titleRu
                    else -> country.titleEn
                }
            )
        }
    }

    init {
    }

    fun onItemClick(selectedCountry: String) {
        Log.d("myTag", "${javaClass.simpleName}: ${Country.valueOf(selectedCountry)}")
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