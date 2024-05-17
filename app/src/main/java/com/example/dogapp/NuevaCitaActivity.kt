package com.example.dogapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dogapp.view.fragment.NuevaCitaFragment

class NuevaCitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_cita)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NuevaCitaFragment())
                .commit()
        }
    }
}
