package com.themobilecoder.countrylist.viewmodel

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
//    val countryNameLiveData = MutableLiveData<String>()
    private val slowNetwork = SlowNetwork()


    fun getCountryName(countryCode: String) {
        viewModelScope.launch {
            getDetailsFromFile(countryCode)
        }
    }

    private suspend fun getDetailsFromFile(countryCode: String) {
        withContext(Dispatchers.IO) {
            //Converting the COUNTRIES JSON to Countries data class
            val countriesList = getCountryNameAndCode()
             //Filtering/Extracting the required country from the Countries List using Country Code
           var countryDetail =  countriesList.filter { countryName ->
               countryName.countryCode == countryCode
           }
            //Fetching the required Country name from the filtered list
            var countryName = countryDetail[0].countryName

            countryDetailsLiveData.postValue(countryName)
        }
    }

    private fun getCountryNameAndCode() =
        Gson().fromJson(slowNetwork.getCountries(), Array<Countries>::class.java)
            .toList() as ArrayList<Countries>

}