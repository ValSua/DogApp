package com.example.dogapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.activity.viewModels
import com.example.dogapp.databinding.ActivityCreateBinding
import com.example.dogapp.model.Cita
import com.example.dogapp.viewmodel.CitaViewModel

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    private val citaViewModel: CitaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        verificarCasillas()
        onClickBotonGuardar()
        onClickBotonVolver()
        observerRazas()
//        progressbar()
    }

    private fun verificarCasillas() {
        val listaInput = listOf(binding.ietMascota, binding.ietRaza, binding.ietPropietario, binding.ietTelefono)
        listaInput.forEach {
            it.addTextChangedListener {
                binding.btnGuardar.isEnabled = listaInput.all { it.text.toString().isNotEmpty() }
            }
        }
    }

    private fun onClickBotonGuardar() {
        binding.btnGuardar.setOnClickListener {
            if (binding.spinnerSintomas.selectedItem.toString() == "Síntomas") {
                Toast.makeText(this, "Selecciona un síntoma", Toast.LENGTH_SHORT).show()
            } else {
                val mascota = binding.ietMascota.text.toString()
                val raza = binding.ietRaza.text.toString()
                val telefono = binding.ietTelefono.text.toString()
                val propietario = binding.ietPropietario.text.toString()
                val sintoma = binding.spinnerSintomas.selectedItem.toString()
                val cita = Cita(mascota, raza, propietario, telefono, sintoma, "img")
                citaViewModel.agregarCita(cita)
                finish() // Close the activity and go back
            }
        }
    }

    private fun observerRazas() {
        citaViewModel.getListaRazas()
        citaViewModel.listaRazas.observe(this) { lista ->
            ArrayAdapter(
                this,
                android.R.layout.simple_dropdown_item_1line,
                lista
            ).also { adapter ->
                binding.ietRaza.setAdapter(adapter)
            }
        }
    }

//    private fun progressbar() {
//        citaViewModel.progresState.observe(this) {
//            binding.progress.isVisible = it
//        }
//    }

    private fun onClickBotonVolver() {
        binding.toolbar.btnVolver.setOnClickListener {
            finish() // Close the activity and go back
        }
    }
}
