package com.themobilecoder.datamodel

import com.google.gson.annotations.SerializedName

data class Capitals(
    @SerializedName("country_name")
    val countryName:String,
    @SerializedName("capital")
    val capital:String
)
