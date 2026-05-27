package com.example.proyecto

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombre = "Miguel Zeferino Perez Vazquez"
        if (nombre.isNotEmpty()) {
            binding.textViewIntent.text = nombre
            Toast.makeText(this, "Mensaje mostrado", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Está vacío", Toast.LENGTH_LONG).show()
        }

        binding.btnsiguiente2.setOnClickListener {
            startActivity(this, ThirdActivity::class.java)
        }
    }

    private fun startActivity(actividadActual: Activity, actividadNext: Class<*>) {
        val intentMio = Intent(actividadActual, actividadNext)
        actividadActual.startActivity(intentMio)
    }
}
