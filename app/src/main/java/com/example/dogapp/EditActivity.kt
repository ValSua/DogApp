package com.example.dogapp

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.dogapp.databinding.ActivityEditBinding
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import java.security.Provider.Service


class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración de la barra de herramientas (Toolbar)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configuración del clic en el ícono de navegación
        toolbar.setNavigationOnClickListener {
            // Cierra esta actividad y regresa a la actividad anterior
            finish()
        }

        // Obtener referencias a las vistas
        val btnEnviarDatos = findViewById<ImageButton>(R.id.btnenviarDatos)
        val textNameDog  = findViewById<TextInputEditText>(R.id.nombrePerro)
        val textRazaDog = binding.raza
        val textNameDueno = findViewById<TextInputEditText>(R.id.nombreDueno)
        val numberPhone = findViewById<TextInputEditText>(R.id.telefono)
        val textbtn = findViewById<TextView>(R.id.enviarDatosText)

        // Definir el TextWatcher para verificar los campos llenos
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Verificar si todos los EditText contienen información
                val isAllFilled = !textNameDog.text.isNullOrBlank() &&
                        !textRazaDog.text.isNullOrBlank() &&
                        !textNameDueno.text.isNullOrBlank() &&
                        !numberPhone.text.isNullOrBlank()

                // Si todos los EditText contienen información, habilitar el ImageButton
                btnEnviarDatos.isEnabled = isAllFilled

                // Cambiar el color del texto según si todos los campos están llenos o no
                textbtn.setTextColor(if (isAllFilled) Color.WHITE else Color.BLACK)
            }

            override fun afterTextChanged(s: Editable?) {}
        }



        // Aplicar el TextWatcher a cada EditText
        textRazaDog.addTextChangedListener(textWatcher)
        textNameDog.addTextChangedListener(textWatcher)
        textNameDueno.addTextChangedListener(textWatcher)
        numberPhone.addTextChangedListener(textWatcher)

        // Configuración del clic en el botón de envío de datos
        btnEnviarDatos.setOnClickListener {
            // Verificar si todos los campos están llenos
            if (btnEnviarDatos.isEnabled) {
                // Realizar la acción deseada si todos los campos están llenos
                Toast.makeText(this, "ImageButton clicked", Toast.LENGTH_SHORT).show()
            } else {
                // Realizar alguna otra acción si no todos los campos están llenos
                // Por ejemplo, no hacer nada
            }
        }

        // Deshabilitar el botón de envío de datos inicialmente
        btnEnviarDatos.isEnabled = false

        setupAutoCompleteTextView()
    }

    private fun setupAutoCompleteTextView() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(DogService::class.java)

        service.getBreeds().enqueue(object : Callback<BreedsResponse> {
            override fun onResponse(call: Call<BreedsResponse>, response: Response<BreedsResponse>) {
            if (response.isSuccessful) {
                val breeds = response.body()?.message?.keys?.toList() ?: emptyList()
                val adapter = ArrayAdapter(this@EditActivity, android.R.layout.simple_dropdown_item_1line, breeds)
                binding.raza.setAdapter(adapter)
            }
        }

            override fun onFailure(call: Call<BreedsResponse>, t: Throwable) {
            Toast.makeText(this@EditActivity, "Error al obtener las razas de perros", Toast.LENGTH_SHORT).show()
        }
        })
    }
    interface DogService {
        @GET("api/breeds/list/all")
        fun getBreeds(): Call<BreedsResponse>
    }

    data class BreedsResponse(
        val message: Map<String, List<String>>,
    val status: String
    )

}
