package com.themobilecoder.countrylist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.themobilecoder.countrylist.databinding.ActivityMainBinding

import com.themobilecoder.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var countryName:EditText
    private lateinit var submit:Button
    lateinit var viewModel: CountryViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)

        countryName = binding.countryName
        submit = binding.submitButton

        submit.setOnClickListener {
            viewModel.getCountryName(countryName.text.toString())
        }

        viewModel.countryDetailsLiveData.observe(this, {
            countryName.setText(it)
        })
    }

}