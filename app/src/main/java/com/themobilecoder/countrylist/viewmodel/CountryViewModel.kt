package com.themobilecoder.countrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.themobilecoder.datamodel.CountryDetail
import com.themobilecoder.datasource.SlowNetwork
import kotlinx.coroutines.*

class CountryViewModel : ViewModel() {
    val countryDetailsLiveData = MutableLiveData<List<CountryDetail>>()
    private val slowNetwork = SlowNetwork()

    fun getCountryName(countryCode: String) {
        viewModelScope.launch {
            getCountriesFromFile(countryCode)
        }
    }

    private suspend fun getCountriesFromFile(countryCode: String) {
        withContext(Dispatchers.IO) {
            val countryDetails =
                Gson().fromJson(slowNetwork.getCountries(), Array<CountryDetail>::class.java)
                    .toList() as ArrayList<CountryDetail>
            countryDetailsLiveData.postValue(countryDetails)

        }
    }
}