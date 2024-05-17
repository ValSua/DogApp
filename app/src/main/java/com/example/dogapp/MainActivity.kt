package com.example.dogapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.dogapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener una referencia al ImageView
        val imageView: ImageView = findViewById(R.id.imageView)

        // Cargar la imagen en el ImageView
        imageView.setImageResource(R.drawable.head_48478_960_720)

        val animationView = binding.animationView
        animationView.setOnClickListener {
            showBiometricDialog()
        }

    }


    private fun showBiometricDialog() {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    // Autenticación exitosa, dirigir al usuario a la nueva actividad
                    val intent = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(intent)
                }

            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Autenticación con Biometría")
            .setSubtitle("Ingrese su huella digital")
            .setNegativeButtonText("Cancelar")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

}
