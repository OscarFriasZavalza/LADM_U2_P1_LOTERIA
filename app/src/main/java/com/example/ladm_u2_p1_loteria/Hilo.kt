package com.example.ladm_u2_p1_loteria


import com.bumptech.glide.Glide
import android.media.MediaPlayer
import kotlin.random.Random

class Hilo(var activity: MainActivity) : Thread() {

    var cartas = arrayListOf<Carta>()
    var cartasSobrantes = arrayListOf<Carta>()

    var corriendo = true
    var parar = false
    var barajear = true
    var pausar = false
    var revisar = 0
    var i = 0

    var frases = arrayOf("", "del pozole", "con la vieja", "Se va y se corre")
    var a = 3
    var audio : MediaPlayer

    init {
        iniciarCartas()
        cartas.shuffle()
        cartasSobrantes = cartas
        audio = MediaPlayer.create(activity, R.raw.musicafondo)
    }

    override fun run() {
        super.run()

        while (true) {
            if (corriendo) {
                if (barajear && a != 0) {
                    activity.runOnUiThread {
                        activity.binding.texto.text= frases[a--]
                    }
                } else {
                    if (pausar && !parar)
                        activity.runOnUiThread {
                            activity.binding.texto.text = "JUEGO EN PAUSA"
                            activity.binding.etPausar.text = "REANUDAR"
                        }
                    else {
                        if (parar) {
                            activity.runOnUiThread {
                                activity.binding.etLoteria.text= "VERIFICAR"
                                Glide.with(activity)
                                    .load(R.drawable.buenas)
                                    .into(activity.binding.imgCarta)
                            }
                            if (revisar == 2) {
                                if (i < cartas.size) {
                                    activity.runOnUiThread {
                                        activity.binding.texto.text= "${i+1} VERIFICANDO CARTAS "
                                        Glide.with(activity)
                                            .load(cartas[i].img)
                                            .into(activity.binding.imgCarta)
                                        audio = MediaPlayer.create(activity, cartas[i].audio)
                                        audio.start()
                                        i++
                                    }
                                }
                            }
                        } else {
                            activity.runOnUiThread {
                                activity.binding.etPausar.text = "PAUSAR"
                                if (i < cartas.size) {
                                    Glide.with(activity)
                                        .load(cartas[i].img)
                                        .into(activity.binding.imgCarta)
                                    activity.binding.texto.text = "${i+1} ${cartas[i].nombre} "
                                    audio = MediaPlayer.create(activity, cartas[i].audio)
                                    audio.start()
                                    i++
                                } else {

                                    // Pasaron todas las cartas
                                    activity.binding.texto.text = "JUEGO TERMINADO"
                                    Glide.with(activity)
                                        .load(R.drawable.juegotermino)
                                        .into(activity.binding.imgCarta)
                                }
                            }
                        }
                    }
                }
            }

            audio.release()
            sleep(2500)
        }
    }

    fun pararJuego() {
        parar = true
    }

    fun cambiarPausar() {
        pausar = !pausar
    }

    fun reiniciar() {
        activity.runOnUiThread {
            activity.binding.etLoteria.text = "LOTERIA"

        }
        cartas.shuffle()
        a = 3
        barajear = true
        pausar = false

        i = 0
        revisar = 0
        parar = false
    }

    private fun iniciarCartas() {
        cartas.add(Carta(1,"El gallo",R.drawable.gallo1,R.raw.aud1))
        cartas.add(Carta(2, "El diablo",R.drawable.diablo2, R.raw.aud2))
        cartas.add(Carta(3,"La dama",R.drawable.dama3,R.raw.aud3))
        cartas.add(Carta(4, "El catrín",R.drawable.catrin4, R.raw.aud4))
        cartas.add(Carta(5,"El paraguas", R.drawable.paraguas5, R.raw.aud5))
        cartas.add(Carta(6, "La sirena",R.drawable.sirena6, R.raw.aud6))
        cartas.add(Carta(7, "La escalera", R.drawable.escalera7, R.raw.aud7))
        cartas.add(Carta(8,"La botella", R.drawable.botella8, R.raw.aud8))
        cartas.add(Carta(9,"EL barril", R.drawable.barril9, R.raw.aud9))
        cartas.add(Carta(10,"El árbol", R.drawable.arbol10, R.raw.aud10))
        cartas.add(Carta(11,"El melón", R.drawable.melon11, R.raw.aud11))
        cartas.add(Carta(12, "El valiente", R.drawable.valiente12, R.raw.aud12))
        cartas.add(Carta(13, "El gorrito",R.drawable.gorrito13, R.raw.aud13))
        cartas.add(Carta(14, "La muerte",R.drawable.muerte14, R.raw.aud14))
        cartas.add(Carta(15, "La pera",R.drawable.pera15, R.raw.aud15))
        cartas.add(Carta(16, "La bandera",R.drawable.bandera16, R.raw.aud16))
        cartas.add(Carta(17, "El bandolón", R.drawable.bandolon17,R.raw.aud17))
        cartas.add(Carta(18, "El violoncello", R.drawable.violoncello18,R.raw.aud18))
        cartas.add(Carta(19, "La garza", R.drawable.garza19,R.raw.aud19))
        cartas.add(Carta(20, "El pájaro", R.drawable.pajaro20,R.raw.aud20))
        cartas.add(Carta(21, "La mano", R.drawable.mano21,R.raw.aud21))
        cartas.add(Carta(22, "La bota", R.drawable.bota22,R.raw.aud22))
        cartas.add(Carta(23, "La luna", R.drawable.luna23,R.raw.aud23))
        cartas.add(Carta(24, "El cotorro",R.drawable.cotorro24, R.raw.aud24))
        cartas.add(Carta(25, "El borracho", R.drawable.borracho25, R.raw.aud25))
        cartas.add(Carta(26, "El negrito", R.drawable.negrito26, R.raw.aud26))
        cartas.add(Carta(27, "El corazón", R.drawable.corazon27, R.raw.aud27))
        cartas.add(Carta(28, "La sandía",R.drawable.sandia28, R.raw.aud28))
        cartas.add(Carta(29, "El tambor",R.drawable.tambor29, R.raw.aud29))
        cartas.add(Carta(30, "El camarón",R.drawable.camaron30, R.raw.aud30))
        cartas.add(Carta(31, "Las jaras",R.drawable.jaras31, R.raw.aud31))
        cartas.add(Carta(32, "El músico", R.drawable.musico32,R.raw.aud32))
        cartas.add(Carta(33, "La araña", R.drawable.arana33, R.raw.aud33))
        cartas.add(Carta(34, "El soldado", R.drawable.soldado34, R.raw.aud34))
        cartas.add(Carta(35, "La estrella",R.drawable.estrella35, R.raw.aud35))
        cartas.add(Carta(36, "El cazo",R.drawable.cazo36,R.raw.aud36))
        cartas.add(Carta(37, "El mundo", R.drawable.mundo37, R.raw.aud37))
        cartas.add(Carta(38, "El apache",R.drawable.apache38, R.raw.aud38))
        cartas.add(Carta(39, "El nopal", R.drawable.nopal39, R.raw.aud39))
        cartas.add(Carta(40, "El alacrán",R.drawable.alacran40, R.raw.aud40))
        cartas.add(Carta(41, "La rosa",R.drawable.rosa41, R.raw.aud41))
        cartas.add(Carta(42, "La calavera",R.drawable.calavera42, R.raw.aud42))
        cartas.add(Carta(43, "La campana",R.drawable.campana43, R.raw.aud43))
        cartas.add(Carta(44, "El cantarito",R.drawable.cantarito44, R.raw.aud44))
        cartas.add(Carta(45, "El venado",R.drawable.venado45, R.raw.aud45))
        cartas.add(Carta(46, "El sol",R.drawable.sol46, R.raw.aud46))
        cartas.add(Carta(47, "La corona",R.drawable.corona47, R.raw.aud47))
        cartas.add(Carta(48, "La chalupa",R.drawable.chalupa48, R.raw.aud48))
        cartas.add(Carta(49, "El pino",R.drawable.pino49, R.raw.aud49))
        cartas.add(Carta(50, "El pescado",R.drawable.pescado50,R.raw.aud50))
        cartas.add(Carta(51, "La palma",R.drawable.palma51,R.raw.aud51))
        cartas.add(Carta(52, "La maceta",R.drawable.maceta52, R.raw.aud52))
        cartas.add(Carta(53, "El arpa",R.drawable.arpa53, R.raw.aud53))
        cartas.add(Carta(54, "La rana", R.drawable.rana54, R.raw.aud54))
    }
}