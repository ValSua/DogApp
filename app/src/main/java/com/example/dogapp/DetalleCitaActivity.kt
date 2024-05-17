
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.dogapp.databinding.ActivityDetalleCitaBinding

class DetalleCitaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleCitaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleCitaBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}