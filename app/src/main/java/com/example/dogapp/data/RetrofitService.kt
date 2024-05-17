package com.example.dogapp.data

import com.example.dogapp.model.ImagenUrl
import com.example.dogapp.model.ListaRazas
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitService {
    @GET("breed/{raza}/images/random")
    suspend fun getImgUrl(
        @Path("raza") raza: String
    ): ImagenUrl

    @GET("breeds/list/all")
    suspend fun getRazas(): ListaRazas
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}