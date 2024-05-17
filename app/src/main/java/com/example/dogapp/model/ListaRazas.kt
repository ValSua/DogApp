package com.example.dogapp.model

import com.google.gson.annotations.SerializedName

data class ListaRazas(
    @SerializedName("message")
    val razas: Map<String, List<String>>,
    @SerializedName("status")
    val status: String
)
