package com.example.dogapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dogapp.model.Cita
import com.example.dogapp.repository.CitaRepository
import kotlinx.coroutines.launch
import kotlin.math.log

class CitaViewModel(application: Application): AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val citaRepository = CitaRepository(context)

    private val _listaCitas = MutableLiveData<MutableList<Cita>>()
    val listaCitas get() = _listaCitas

    private val _progresState = MutableLiveData<Boolean>(false)
    val progresState get() = _progresState

    private val _listaRazas = MutableLiveData<MutableList<String>>()
    val listaRazas get() = _listaRazas

    fun agregarCita(cita: Cita){
        viewModelScope.launch{
            _progresState.value = true
            cita.img = citaRepository.getImgUrl(cita.raza)
            try {
                citaRepository.agregarCita(cita)
                _progresState.value = false
            }
            catch (e: Exception) {
                _progresState.value = false
            }
        }
    }

    fun getListaRazas() {
        viewModelScope.launch {
            _listaRazas.value = citaRepository.getListaRazas()
        }
    }

}