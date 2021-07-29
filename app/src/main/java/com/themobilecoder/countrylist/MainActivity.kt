package com.themobilecoder.countrylist

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import com.themobilecoder.countrylist.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var countryName:TextView
    lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)

        countryName = findViewById(R.id.countryName)
        viewModel.getCountryName("Denmark")
        viewModel.countryDetailsLiveData.observe(this, {
            countryName.text = it
        })
    }

}