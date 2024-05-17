package com.example.dogapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController


class HelloWorldActivity : FragmentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_world)

        val botonEditarCita: Button = findViewById(R.id.btn_editar_Cita)
        val btnCrearCita: Button = findViewById(R.id.btn_nueva_Cita)

        botonEditarCita.setOnClickListener {
            // Iniciar com.example.dogapp.EditActivity cuando se haga clic en el botón
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("CITA_ID",1)
            startActivity(intent)
        }

        btnCrearCita.setOnClickListener {
            // Iniciar com.example.dogapp.EditActivity cuando se haga clic en el botón
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
    }

}
