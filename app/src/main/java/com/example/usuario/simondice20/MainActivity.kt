package com.example.usuario.simondice20

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvNivel.setText("")

        bempe.setOnClickListener {
            nivel=1
            dificultad=4
            comprobante=4
            tvNivel.setText(nivel.toString())
            verCiclo(dificultad)
        }

        bRojo.setOnClickListener{
            comprobacionC("Rojo")
        }

        bAzul.setOnClickListener{
            comprobacionC("Azul")
        }

        bVerde.setOnClickListener{
            comprobacionC("Verde")
        }

        bAmarillo.setOnClickListener{
            comprobacionC("Amarillo")
        }
    }

    var nivel = 0
    var dificultad = 4
    var comprobante =0
    val ciclo= mutableListOf<String>()

    fun comprobacionC(color: String){

        if(ciclo.isEmpty()==true){
            toast("SUUU, lo has conseguido")
            nivel=1
            dificultad=4
            comprobante=0
            ciclo.clear()
        }else{
            if(ciclo.get(0).equals(color)){
                ciclo.removeAt(0)
                comprobante--
                if(ciclo.isEmpty()==true){
                    dificultad++
                    nivel++
                    tvNivel.setText(nivel.toString())
                    runBlocking {
                        toast("SUUU, lo has conseguido, BIEN HECHO"+"Nivel: $nivel")
                        delay(500L)

                    }
                    verCiclo(comprobante)
                }
            }else{
                nivel=1
                dificultad=4
                comprobante=0
                toast("JAJA...Manco, repite si quieres")
                ciclo.clear()
            }
        }

    }

    fun parpadear(on: Long, color: String,maximo: Int,actual: Int){
        GlobalScope.launch(Dispatchers.Main) {
            delay(on)
            if (color=="Rojo"){

                bRojo.setBackgroundColor(Color.parseColor("#C40101"))
                delay(300L)
                bRojo.setBackgroundColor(Color.parseColor("#F69681"))

            };if(color=="Azul"){

                bAzul.setBackgroundColor(Color.parseColor("#0D7ADA"))
                delay(300L)
                bAzul.setBackgroundColor(Color.parseColor("#ACF6FC"))

            };if(color=="Amarillo"){

                bAmarillo.setBackgroundColor(Color.parseColor("#EEFD00"))
                delay(300L)
                bAmarillo.setBackgroundColor(Color.parseColor("#F8FCAC"))

            };if(color=="Verde"){

                bVerde.setBackgroundColor(Color.parseColor("#00BD00"))
                delay(300L)
                bVerde.setBackgroundColor(Color.parseColor("#ACFCB8"))

            }; if(actual>=maximo){


            toast("Intentalo ahora")
            comprobante=dificultad


        }
        }
    }

    fun verCiclo(dificultad: Int) {

        val random = Random()
        var aleatorio = 0
        var on = 0L


        for (i in 1..dificultad) {
            aleatorio = random.nextInt(4)
            on+=500L
            if(aleatorio==0) {
                parpadear(on, "Rojo", dificultad, i)
                ciclo.add("Rojo")
            };if(aleatorio==1) {
                parpadear(on, "Azul", dificultad, i)
                ciclo.add("Azul")
            };if(aleatorio==2) {
                parpadear(on, "Verde", dificultad, i)
                ciclo.add("Verde")
            };if(aleatorio==3){
                parpadear(on,"Amarillo",dificultad,i)
                ciclo.add("Amarillo")

                }
            }
        }



}
