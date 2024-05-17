package com.example.dogapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val myButton: ImageButton = findViewById(R.id.floatingActionButton)
        myButton.setOnClickListener {
            val intent = Intent(this, NuevaCitaActivity::class.java)
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