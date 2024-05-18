package com.example.dogapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController

class HelloWorldActivity : FragmentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello_world)


        val myButton: ImageButton = findViewById(R.id.btn_nueva_Cita)



        myButton.setOnClickListener {
            // Iniciar com.example.dogapp.EditActivity cuando se haga clic en el botón
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

        val botonEditarCita: Button = findViewById(R.id.btn_editar_Cita)


        botonEditarCita.setOnClickListener {
            // Iniciar com.example.dogapp.EditActivity cuando se haga clic en el botón
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("CITA_ID",1)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Intent to navigate to the home screen
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

}
