package com.example.dogapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import com.example.dogapp.databinding.ActivityDetalleCitaBinding
import com.example.dogapp.viewmodel.CitaViewModel


class DetalleCitaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleCitaBinding
    private val citaViewModel: CitaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleCitaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar configuration
        val toolbar = findViewById<Toolbar>(R.id.toolbar_nombre)
        setSupportActionBar(toolbar)
        //obtener la cita actual
        val citaId = intent.getIntExtra("CITA_ID", -1)

        val btnEnviarDatos = findViewById<ImageButton>(R.id.btnedit)
        val textraza  = findViewById<TextView>(R.id.razaperro)
        val textsint  = findViewById<TextView>(R.id.sintoma)
        val textprop  = findViewById<TextView>(R.id.propietario)
        val texttel  = findViewById<TextView>(R.id.tel)
        val btnedit = findViewById<ImageButton>(R.id.btnedit)

        // Configuración del clic en el ícono de navegación
        toolbar.setNavigationOnClickListener {
            // Cierra esta actividad y regresa a la actividad anterior
            finish()
        }

        citaViewModel.getCitaById(citaId).observe(this) { cita ->
            cita?.let {
                textraza.setText(it.raza)
                textsint.setText(it.sintoma)
                textprop.setText(it.nombrePropietario)
                texttel.setText(it.tele)
            }
        }


        btnedit.setOnClickListener{
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("CITA_ID",citaId)
            startActivity(intent)
        }

    }




}
