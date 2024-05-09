package com.example.dogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener una referencia al ImageView
        val imageView: ImageView = findViewById(R.id.imageView)

        // Cargar la imagen en el ImageView
        imageView.setImageResource(R.drawable.head_48478_960_720)
    }
}
