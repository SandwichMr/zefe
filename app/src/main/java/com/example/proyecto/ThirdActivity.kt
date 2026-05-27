package com.example.proyecto

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyecto.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Atrás"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.imageButtonPhone.setOnClickListener {
            marcarNumeroIngresado()
        }

        binding.imageButtonWeb.setOnClickListener {
            val url = binding.editTextWeb.text.toString().trim()
            if (url.isEmpty()) {
                Toast.makeText(this, "Ingresa una página web", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val webUrl = if (url.startsWith("http://") || url.startsWith("https://")) {
                url
            } else {
                "https://$url"
            }
            val intentWeb = Intent()
            intentWeb.action = Intent.ACTION_VIEW
            intentWeb.data = Uri.parse(webUrl)
            startActivity(intentWeb)
        }

        binding.buttonEmailMe.setOnClickListener {
            val mailto = "mailto:${getString(R.string.email_to)}" +
                "?cc=${getString(R.string.email_cc)}" +
                "&subject=" + Uri.encode("Asunto del correo") +
                "&body=" + Uri.encode(getString(R.string.default_message))
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse(mailto))
            try {
                startActivity(Intent.createChooser(emailIntent, "Elige el cliente de correo..."))
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "No hay ningún cliente de correo electrónico instalado.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        binding.buttonContactPhone.setOnClickListener {
            val intentCall = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${getString(R.string.contact_phone)}"))
            startActivity(intentCall)
        }

        binding.imageButtonCamera.setOnClickListener {
            val intentCamera = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intentCamera)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun marcarNumeroIngresado() {
        val phoneNumber = binding.editTextPhone.text.toString().trim()
        if (phoneNumber.isNotEmpty()) {
            val intentCall = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
            startActivity(intentCall)
        } else {
            Toast.makeText(
                this,
                "Debes marcar un número, intenta nuevamente",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.menuContactos -> {
                val intentContactos = Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"))
                startActivity(intentContactos)
            }

            R.id.menuSMS -> {
                val intentSMS = Intent()
                intentSMS.action = Intent.ACTION_SENDTO
                intentSMS.data = Uri.parse("smsto:")
                intentSMS.putExtra("address", getString(R.string.contact_phone))
                intentSMS.putExtra("sms_body", getString(R.string.default_message))
                startActivity(intentSMS)
            }

            R.id.menuVideo -> {
                val intentVideo = Intent("android.media.action.VIDEO_CAPTURE")
                startActivity(intentVideo)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
