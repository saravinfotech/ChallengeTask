package com.themobilecoder.datamodel

import com.google.gson.annotations.SerializedName

data class Countries(
    @SerializedName("country_name")
    val countryName:String,
    @SerializedName("country_code")
    val countryCode:String
)
