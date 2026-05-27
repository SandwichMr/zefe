package com.example.proyecto

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val saludo = "¡Hola desde el Activity Main / Primer pantalla!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_smiley_app)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btncalcular.setOnClickListener {
            val anioNac = binding.edtxtanionac.text.toString().toIntOrNull()
            if (anioNac == null) {
                Toast.makeText(this, "Ingresa un año válido", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val anioActual = Calendar.getInstance().get(Calendar.YEAR)
            val miEdad = anioActual - anioNac
            binding.txtedad.text = "Tu edad es $miEdad años"
        }

        binding.btnsiguiente.setOnClickListener {
            startActivity(this, SecondActivity::class.java)
        }
    }

    private fun startActivity(actividadActual: Activity, actividadNext: Class<*>) {
        val intentMio = Intent(actividadActual, actividadNext)
        intentMio.putExtra("saludo", saludo)
        actividadActual.startActivity(intentMio)
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "Mensaje o notificación onStop", Toast.LENGTH_LONG).show()
    }
}
