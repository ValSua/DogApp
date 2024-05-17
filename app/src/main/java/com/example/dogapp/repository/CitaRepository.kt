package com.example.dogapp.repository

import android.content.Context
import com.example.dogapp.data.DBCita
import com.example.dogapp.data.DaoCita
import com.example.dogapp.data.RetrofitService
import com.example.dogapp.data.RetrofitServiceFactory
import com.example.dogapp.model.Cita
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CitaRepository(val context: Context) {
    private var daoCita = DBCita.getDatabase(context).citasDao()
    private var service = RetrofitServiceFactory.makeRetrofitService()

    suspend fun agregarCita(cita: Cita){
        withContext(Dispatchers.IO){
            daoCita.guardarCita(cita)
        }
    }

    suspend fun getListaRazas(): MutableList<String>{
        return withContext(Dispatchers.IO){
            try {
                val response = service.getRazas()
                mapToList(response.razas)
            } catch (e: Exception){
                ArrayList()
            }
        }
    }

    suspend fun getImgUrl(raza: String): String {
        return withContext(Dispatchers.IO){
            try {
                service.getImgUrl(raza).message
            } catch (e: Exception) {
                ""
            }
        }
    }

    private fun mapToList(razas: Map<String, List<String>>): MutableList<String> {
        val result = mutableListOf<String>()

        razas.forEach {
            if (it.value.isEmpty()) {result.add(it.key)}
            else {it.value.forEach{valor -> result.add("${it.key}-$valor")}}
        }
        return result
    }
}