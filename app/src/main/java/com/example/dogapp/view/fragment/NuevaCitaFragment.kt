package com.example.dogapp.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.dogapp.R
import com.example.dogapp.databinding.FragmentNuevaCitaBinding
import com.example.dogapp.model.Cita
import com.example.dogapp.viewmodel.CitaViewModel

class NuevaCitaFragment : Fragment() {

    private lateinit var binding: FragmentNuevaCitaBinding
    private val citaViewModel: CitaViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNuevaCitaBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        verificarCasillas()
        onClickBotonGuardar()
        onClickBotonVolver()
        observerRazas()
        progressbar()
    }

    private fun verificarCasillas(){
        val listaInput = listOf(binding.ietMascota, binding.ietRaza, binding.ietPropietario, binding.ietTelefono)
        listaInput.forEach {
            it.addTextChangedListener{
                binding.btnGuardar.isEnabled = listaInput.all { it.text.toString().isNotEmpty() }
            }
        }
    }

    private fun onClickBotonGuardar(){
        binding.btnGuardar.setOnClickListener{
            if(binding.spinnerSintomas.selectedItem.toString() == "Síntomas"){
                Toast.makeText(context, "Selecciona un síntoma", Toast.LENGTH_SHORT).show()
            } else {
                val mascota = binding.ietMascota.text.toString()
                val raza = binding.ietRaza.text.toString()
                val telefono = binding.ietTelefono.text.toString()
                val propietario = binding.ietPropietario.text.toString()
                val sintoma = binding.spinnerSintomas.selectedItem.toString()
                val cita = Cita(mascota, raza, propietario, telefono, sintoma, "")
                citaViewModel.agregarCita(cita)
                findNavController().popBackStack()
            }
        }
    }

    private fun observerRazas(){
        citaViewModel.getListaRazas()
        citaViewModel.listaRazas.observe(viewLifecycleOwner){ lista ->
            ArrayAdapter(
                this.requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                lista).also { adapter ->
                binding.ietRaza.setAdapter(adapter)
            }
        }
    }

    private fun progressbar(){
        citaViewModel.progresState.observe(viewLifecycleOwner){
            binding.progress.isVisible = it
        }
    }

    private fun onClickBotonVolver(){
        binding.toolbar.btnVolver.setOnClickListener{
            findNavController().popBackStack()
        }
    }

}