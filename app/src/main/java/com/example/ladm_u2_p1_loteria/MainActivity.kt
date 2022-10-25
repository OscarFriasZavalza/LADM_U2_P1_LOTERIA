package com.example.ladm_u2_p1_loteria

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.example.ladm_u2_p1_loteria.databinding.ActivityMainBinding

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var cartasLoteria = arrayListOf<Int>(
        R.drawable.carta1,
        R.drawable.carta2,
        R.drawable.carta3,
        R.drawable.carta4,
        R.drawable.carta5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mostrarCartasInicio()
        var hilo =Hilo(this)

        binding.btnEmpezar.setOnClickListener {
            hilo.start()
            binding.btnEmpezar.isEnabled = false
            binding.btnDetener.isEnabled = true
            binding.btnPausar.isEnabled = true
            binding.btnBarajear.isEnabled = true
        }

        binding.btnPausar.setOnClickListener {
            hilo.cambiarPausar()
        }

        binding.btnDetener.setOnClickListener {
            if(hilo.corriendo && !hilo.pausar){
                if(hilo.revisar<2)
                    hilo.revisar=hilo.revisar+1
                hilo.pararJuego()
            }
        }

        binding.btnBarajear.setOnClickListener {
            if (!hilo.pausar)
                hilo.cambiarPausar()
            AlertDialog.Builder(this)
                .setTitle("BARAJEAR CARTAS")
                .setMessage("¿Desea reiniciar el juego?")
                .setPositiveButton("OK") { d, i ->
                    hilo.reiniciar()
                    mostrarCartas_despues_reinicio()
                    d.dismiss()
                }
                .setNegativeButton("Cancelar") { d, i ->
                    hilo.cambiarPausar()
                    d.dismiss()
                }
                .show()
        }

    }


    fun mostrarCartasInicio() = GlobalScope.launch {
        var audio = MediaPlayer.create(baseContext, R.raw.musicafondo)
        audio.start()
        for (i in cartasLoteria) {
            runOnUiThread {
                Glide.with(baseContext)//se pasa el contexto
                    .load(i)
                    .into(binding.imgCarta)

            }
            binding.texto.text = "Preparando las Cartas..."
            delay(2000)
        }
        binding.texto.text="Listo...¡Comencemos!"

    }

    fun mostrarCartas_despues_reinicio() = GlobalScope.launch {
        for (i in cartasLoteria) {
            runOnUiThread {
                Glide.with(baseContext)
                    .load(i)
                    .into(binding.imgCarta)
            }
            delay(1500)
        }
    }
}