package com.themobilecoder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.themobilecoder.datamodel.Capitals
import com.themobilecoder.datamodel.Countries
import com.themobilecoder.datasource.SlowNetwork
import kotlinx.coroutines.*

class CountryViewModel : ViewModel() {
    val countryDetailsLiveData = MutableLiveData<String>()
    private val slowNetwork = SlowNetwork()

    fun getCountryCapital(countryName: String) {
        viewModelScope.launch {
            getDetailsFromFile(countryName)
        }
    }

    private suspend fun getDetailsFromFile(countryName: String) {
        var countryCapital = "Unknown code"
        withContext(Dispatchers.IO) {
            //Converting the COUNTRIES JSON to Countries data class
            val countriesList = getCountryNameAndCode()
            //Filtering/Extracting the required country from the Countries List using Country Code
            var countryDetail = countriesList.filter {
                it.countryName == countryName
            }
            //Fetching the required Country name from the filtered list
            var countryCode = countryDetail.getOrNull(0)?.countryCode

            countryCode?.let {
                countryCapital = getCountryCapitalFromFile(countryCode)
            }
            countryDetailsLiveData.postValue(countryCapital)
        }
    }

    private fun getCountryNameAndCode() =
        Gson().fromJson(slowNetwork.getCountries(), Array<Countries>::class.java)
            .toList() as ArrayList<Countries>

    private fun getCountryCapitalFromFile(countryCode: String): String {
        //getting the Capital from the SlowNetwork class
        var countryNameAndCapital = slowNetwork.getCapitolFor(countryCode)
        //Parse the single JSON item
        val countryCapitals = Gson().fromJson(countryNameAndCapital, Capitals::class.java)
        return countryCapitals.capital
    }
}
