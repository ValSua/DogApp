package com.example.dogapp.model

import com.google.gson.annotations.SerializedName

data class ImagenUrl(
    @SerializedName("status")
    val status: String,
    @SerializedName("message")
    val message: String
)
