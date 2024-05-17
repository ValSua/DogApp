import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.example.dogapp.R
import com.example.dogapp.database.Mascota
import com.example.dogapp.database.MascotaDatabase
import com.example.dogapp.databinding.ActivityDetalleCitaBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalleCitaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleCitaBinding
    private lateinit var mascotaDatabase: MascotaDatabase
    private var mascotaId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleCitaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar configuration
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mascotaDatabase = MascotaDatabase.getDatabase(this)

        mascotaId = intent.getIntExtra("mascotaId", 0)

        lifecycleScope.launch {
            val mascota = getMascota(mascotaId)
            if (mascota != null) {
                withContext(Dispatchers.Main) {
                    mostrarDetalles(mascota)
                }
            } else {
                Toast.makeText(this@DetalleCitaActivity, "Mascota no encontrada", Toast.LENGTH_SHORT).show()
            }
        }

        binding.deleteButton.setOnClickListener {
            eliminarMascota()
        }

        binding.editButton.setOnClickListener {
            // Navegar a la actividad de edición (HU 5.0)
            // ...
        }
    }

    private suspend fun getMascota(mascotaId: Int): Mascota? = withContext(Dispatchers.IO) {
        mascotaDatabase.mascotaDao().getById(mascotaId)
    }

    private fun mostrarDetalles(mascota: Mascota) {
        supportActionBar?.title = mascota.nombreMascota

        binding.razaTextView.text = mascota.raza
        binding.sintomasTextView.text = mascota.sintomas
        binding.propietarioTextView.text = mascota.nombrePropietario
        binding.telefonoTextView.text = mascota.telefono
        // ... (cargar imagen de la mascota usando Glide o Picasso)
    }

    private fun eliminarMascota() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                mascotaDatabase.mascotaDao().delete(mascotaId)
            }
            finish() // Regresar a la actividad anterior (HU 2.0)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
