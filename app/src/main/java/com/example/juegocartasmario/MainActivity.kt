package com.example.juegocartasmario

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.R.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    //<editor-fold desc="Todas las imagenes">
    private lateinit var img01:ImageView
    private lateinit var img02:ImageView
    private lateinit var img03:ImageView
    private lateinit var img04:ImageView

    private lateinit var img11:ImageView
    private lateinit var img12:ImageView
    private lateinit var img13:ImageView
    private lateinit var img14:ImageView

    private lateinit var img21:ImageView
    private lateinit var img22:ImageView
    private lateinit var img23:ImageView
    private lateinit var img24:ImageView

    private lateinit var img31:ImageView
    private lateinit var img32:ImageView
    private lateinit var img33:ImageView
    private lateinit var img34:ImageView
    //</editor-fold>

    //<editor-fold desc="Otros objetos">
    private lateinit var textJ1: TextView
    private lateinit var textJ2: TextView

    private lateinit var btnSonidoMain: ImageButton


    private lateinit var media :MediaPlayer
    private lateinit var mediaFondo :MediaPlayer

    private lateinit var imagen1 :ImageView
    private lateinit var imagen2 :ImageView
    //</editor-fold>

    //<editor-fold desc="Invocacion de variables">
    private var imgArray = arrayOf(11,12,13,14,15,16,17,18,21,22,23,24,25,26,27,28)
    private var finn =0
    private var jake =0
    private var bmo =0
    private var marceline =0
    private var bultos =0
    private var chicle =0
    private var llama =0
    private var reyhielo =0

    private var turno=1
    private var puntosJ1=0
    private var puntosJ2=0
    private var numeroImagen=1
    private var escuchar = true

    //</editor-fold>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        linkGUI()
    }

    private fun linkGUI() {
        //<editor-fold desc="FindViews">
        img01 = findViewById(R.id.imageView00)
        img02 = findViewById(R.id.imageView01)
        img03 = findViewById(R.id.imageView02)
        img04 = findViewById(R.id.imageView03)

        img11 = findViewById(R.id.imageView10)
        img12 = findViewById(R.id.imageView11)
        img13 = findViewById(R.id.imageView12)
        img14 = findViewById(R.id.imageView13)

        img21 = findViewById(R.id.imageView20)
        img22 = findViewById(R.id.imageView21)
        img23 = findViewById(R.id.imageView22)
        img24 = findViewById(R.id.imageView23)

        img31 = findViewById(R.id.imageView30)
        img32 = findViewById(R.id.imageView31)
        img33 = findViewById(R.id.imageView32)
        img34 = findViewById(R.id.imageView33)
        //</editor-fold>

        btnSonidoMain = findViewById(R.id.btnSonido)
        sonido("backgroundsound", true)
        //Color verde de base de la libreria Colors
        btnSonidoMain.setColorFilter(ContextCompat.getColor(this, R.color.green))

        //<editor-fold desc="Asignacion de Tags">
        img01.tag = "0"
        img02.tag = "1"
        img03.tag = "2"
        img04.tag = "3"
        img11.tag = "4"
        img12.tag = "5"
        img13.tag = "6"
        img14.tag = "7"
        img21.tag = "8"
        img22.tag = "9"
        img23.tag = "10"
        img24.tag = "11"
        img31.tag = "12"
        img32.tag = "13"
        img33.tag = "14"
        img34.tag = "15"
        //</editor-fold>

        imgArray.shuffle()

        finn = R.drawable.finn
        jake = R.drawable.jake
        bmo = R.drawable.bmo
        marceline = R.drawable.marceline
        reyhielo = R.drawable.reyhielo
        bultos = R.drawable.princesabultos
        chicle = R.drawable.princesachicle
        llama = R.drawable.princesallama


        textJ1 = findViewById(R.id.textJ1)
        textJ2 = findViewById(R.id.textJ2)

        textJ2.setTextColor(ContextCompat.getColor(this, R.color.gray))
        textJ1.setTextColor(ContextCompat.getColor(this, R.color.white))

    }

    @SuppressLint("DiscouragedApi")
    private fun sonido(sound: String, bucle: Boolean=false) {
        val recursoId = resources.getIdentifier(
            sound,"raw",packageName
        )
        if(recursoId!=0) {
            try {
            if (sound == "backgroundsound") {
                mediaFondo = MediaPlayer.create(this, recursoId)
                mediaFondo.isLooping = bucle
                if (!mediaFondo.isPlaying) {
                    mediaFondo.start()
                }
            } else {
                media = MediaPlayer.create(this, recursoId)
                media.setOnCompletionListener { mediaPlayer ->
                    mediaPlayer.stop()
                    mediaPlayer.release()
                }
                if (!media.isPlaying) {
                    media.start()
                }
            }
            } catch (e: Exception) {
                // Manejar errores al crear MediaPlayer
                Log.e("MediaPlayerError", "Error al crear MediaPlayer: $e")
            }
        } else {
            Log.e("SonidoError", "Recurso no encontrado para el sonido: $sound")
        }
    }

    fun musicaFondo(v: View){
        if (escuchar){
            mediaFondo.pause()
            btnSonidoMain.setImageResource(R.drawable.baseline_volume_off_24)
            btnSonidoMain.setColorFilter(ContextCompat.getColor(this, R.color.gray))
        } else{
            mediaFondo.start()
            btnSonidoMain.setImageResource(R.drawable.baseline_volume_up_24)
            btnSonidoMain.setColorFilter(ContextCompat.getColor(this, R.color.green))
        }
        escuchar= !escuchar
    }

    fun seleccionar(view: View){
        sonido("touch")
        verificar(view)
    }

    private fun verificar(view: View) {
        val imagen=(view as ImageView)
        val tag = view.tag.toString().toInt()

        when (imgArray[tag]) {
            11 -> imagen.setImageResource(finn)
            12 -> imagen.setImageResource(jake)
            13 -> imagen.setImageResource(bmo)
            14 -> imagen.setImageResource(marceline)
            15 -> imagen.setImageResource(reyhielo)
            16 -> imagen.setImageResource(bultos)
            17 -> imagen.setImageResource(chicle)
            18 -> imagen.setImageResource(llama)
            21 -> imagen.setImageResource(finn)
            22 -> imagen.setImageResource(jake)
            23 -> imagen.setImageResource(bmo)
            24 -> imagen.setImageResource(marceline)
            25 -> imagen.setImageResource(reyhielo)
            26 -> imagen.setImageResource(bultos)
            27 -> imagen.setImageResource(chicle)
            28 -> imagen.setImageResource(llama)
        }

        if (numeroImagen==1){
            imagen1 = imagen
            numeroImagen = 2
            imagen.isEnabled = false
        } else if (numeroImagen==2){
            imagen2 = imagen
            numeroImagen = 1
            imagen.isEnabled = false

            deshabilitarImagenes()
            val h = Handler(Looper.getMainLooper())
            h.postDelayed({sonIguales()},700)
        }

    }

    private fun sonIguales() {
        if (imagen1.drawable.constantState == imagen2.drawable.constantState){
            sonido("correct")
            if (turno==1){
                puntosJ1++
                textJ1.text = "J1: $puntosJ1"
            } else if (turno==2) {
                puntosJ2++
                textJ2.text = "J2: $puntosJ2"
            }

            imagen1.isEnabled = false
            imagen2.isEnabled = false
            imagen1.tag = ""
            imagen2.tag = ""
        } else {
            sonido("wrong")
            imagen1.setImageResource(R.drawable.enchiridion)
            imagen2.setImageResource(R.drawable.enchiridion)
            if(turno==1){
                turno=2
                textJ1.setTextColor(ContextCompat.getColor(textJ1.context, R.color.gray))
                textJ2.setTextColor(ContextCompat.getColor(textJ2.context, R.color.white))
            } else if(turno==2){
                turno=1
                textJ2.setTextColor(ContextCompat.getColor(textJ2.context, R.color.gray))
                textJ1.setTextColor(ContextCompat.getColor(textJ1.context, R.color.white))
            }
        }

        img01.isEnabled = img01.tag.toString().isNotEmpty()
        img02.isEnabled = img02.tag.toString().isNotEmpty()
        img03.isEnabled = img03.tag.toString().isNotEmpty()
        img04.isEnabled = img04.tag.toString().isNotEmpty()
        img11.isEnabled = img11.tag.toString().isNotEmpty()
        img12.isEnabled = img12.tag.toString().isNotEmpty()
        img13.isEnabled = img13.tag.toString().isNotEmpty()
        img14.isEnabled = img14.tag.toString().isNotEmpty()
        img21.isEnabled = img21.tag.toString().isNotEmpty()
        img22.isEnabled = img22.tag.toString().isNotEmpty()
        img23.isEnabled = img23.tag.toString().isNotEmpty()
        img24.isEnabled = img24.tag.toString().isNotEmpty()
        img31.isEnabled = img31.tag.toString().isNotEmpty()
        img32.isEnabled = img32.tag.toString().isNotEmpty()
        img33.isEnabled = img33.tag.toString().isNotEmpty()
        img34.isEnabled = img34.tag.toString().isNotEmpty()

        verificarFinJuego()
    }
    private fun deshabilitarImagenes() {
        img01.isEnabled = false
        img02.isEnabled = false
        img03.isEnabled = false
        img04.isEnabled = false
        img11.isEnabled = false
        img12.isEnabled = false
        img13.isEnabled = false
        img14.isEnabled = false
        img21.isEnabled = false
        img22.isEnabled = false
        img23.isEnabled = false
        img24.isEnabled = false
        img31.isEnabled = false
        img32.isEnabled = false
        img33.isEnabled = false
        img34.isEnabled = false
    }

    private fun verificarFinJuego() {
        if(
            img01.tag.toString().isEmpty() &&
            img02.tag.toString().isEmpty() &&
            img03.tag.toString().isEmpty() &&
            img04.tag.toString().isEmpty() &&
            img11.tag.toString().isEmpty() &&
            img12.tag.toString().isEmpty() &&
            img13.tag.toString().isEmpty() &&
            img14.tag.toString().isEmpty() &&
            img21.tag.toString().isEmpty() &&
            img22.tag.toString().isEmpty() &&
            img23.tag.toString().isEmpty() &&
            img24.tag.toString().isEmpty() &&
            img31.tag.toString().isEmpty() &&
            img32.tag.toString().isEmpty() &&
            img33.tag.toString().isEmpty() &&
            img34.tag.toString().isEmpty()
            ){
            media.stop()
            media.release()
            sonido("win")
            val builder = AlertDialog.Builder(this)
            builder
                .setTitle("Fin del juego")
                .setMessage("Puntuacion:\nJ1: " + puntosJ1 + "J2: " + puntosJ2).setCancelable(false)
                .setPositiveButton("Jugar de nuevo",
                    DialogInterface.OnClickListener{dialogInterface, i ->
                        val intent =Intent(this,MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    })

                .setNegativeButton("Salir",
                    DialogInterface.OnClickListener{dialogInterface, i ->
                        finish()
                    })
            val ad = builder.create()
            ad.show()
        }
    }

    //override fun on

    override fun onDestroy() {
        super.onDestroy()
        if (mediaFondo.isPlaying) {
            mediaFondo.stop()
            mediaFondo.release()
        }
    }

}