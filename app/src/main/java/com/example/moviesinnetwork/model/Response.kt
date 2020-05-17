package com.example.moviesinnetwork.model

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("country_name")
    val countryName: String,

    @field:SerializedName("colors")
    val colors: String
)
