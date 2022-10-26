package joseoliva.com.conversor

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import joseoliva.com.conversor.databinding.ActivityConversorBinding

class ConversorActivity : AppCompatActivity() {

    lateinit var binding: ActivityConversorBinding
    var numarray: Int =
        0 //necesito esta var para indicarle que array de unidades usare en el spinner
    lateinit var sporigen: Spinner
    lateinit var spdestino: Spinner
    lateinit var valorescrito: String
    var resultadofinal: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val medidarecibida = intent.extras!!.getString("medida")
        val medidaimagenrecibida = intent.extras!!.getInt("imagen")

        sporigen = binding.sporigen
        spdestino = binding.spdestino

        binding.ivimagenmedida.setImageResource(medidaimagenrecibida)
        /*
        creo un condicional para segun que medida reciba asignarle un entero a la var numarray
         */
        when (medidarecibida) {
            "Peso" -> {
                numarray = R.array.arrayPeso
            }
            "Temperatura" -> {
                numarray = R.array.arrayTemperatura
            }
            "Capacidad HDD" -> {
                numarray = R.array.arrayHdd
            }
            "Longitud" -> {
                numarray = R.array.arrayLongitud
            }
            "Longitud (Extraña)" -> {
                numarray = R.array.arrayraro
            }

        }

        //relleno los spinners
        val adapspinner =
            ArrayAdapter.createFromResource(this, numarray, android.R.layout.simple_spinner_item)
        adapspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sporigen.adapter = adapspinner
        spdestino.adapter = adapspinner

        /*
        doy funcionalidad al boton de convertir. Segun la medida que hubiese seleccionado
        ire a una funcion u otra para convertir.
         */
        binding.btnconversion.setOnClickListener {
            var valorescrito = binding.etcantidad.text.toString()
            if (valorescrito.isEmpty()) {
                Toast.makeText(this, "Debes introducir un valor", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(sporigen.selectedItem == spdestino.selectedItem){
                Toast.makeText(this, "Has seleccionado las mismas unidades", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            when (medidarecibida) {
                "Peso" -> {
                    conviertePeso()
                }
                "Temperatura" -> {
                    convierteTemperatura()
                }
                "Capacidad HDD" -> {
                    convierteHdd()
                }
                "Longitud" -> {
                    convierteLong()
                }
                "Longitud (Extraña)" -> {
                    convierteRaro()
                }
            }
        }
    }

    private fun convierteRaro() {
        //obtengo la unidad seleccionada en cada spinner
        val unidadOrigen: Int = sporigen.getSelectedItemPosition()
        val unidadDestino: Int = spdestino.getSelectedItemPosition()
        valorescrito = binding.etcantidad.text.toString()

        //paso a Double el valor que el usuario a escrito para convertir
        val valoraconvertir = binding.etcantidad.text.toString().toDouble()
        //resto la pos origen y destino para saber cuanta diferencia hay entre una y otra
        val resta = unidadOrigen - unidadDestino
        if (resta > 0) {
            resultadofinal = valoraconvertir * Math.pow(200000000.0, resta.toDouble())
            /*
            Para cambiar el color del texto a partir del = cuento las letras que hay hasta el propio =
            y a partir de ahi pinto hasta el final.
             */
            val mensaje: SpannableString = SpannableString("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
            val colorspan: ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF018786"))
            val letras = contarletras(mensaje.toString())
            mensaje.setSpan(colorspan,letras +1,mensaje.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            binding.tvresultado.setText(mensaje)

        }
        if (resta < 0) {
            resultadofinal = valoraconvertir / Math.pow(200000000.0, -resta.toDouble())
            /*
            Para cambiar el color del texto a partir del = cuento las letras que hay hasta el propio =
            y a partir de ahi pinto hasta el final.
             */
            val mensaje: SpannableString = SpannableString("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
            val colorspan: ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF018786"))
            val letras = contarletras(mensaje.toString())
            mensaje.setSpan(colorspan,letras +1,mensaje.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            binding.tvresultado.setText(mensaje)
        }
    }

    private fun convierteLong() {
        //obtengo la unidad seleccionada en cada spinner
        val unidadOrigen: Int = sporigen.getSelectedItemPosition()
        val unidadDestino: Int = spdestino.getSelectedItemPosition()
        valorescrito = binding.etcantidad.text.toString()

        //paso a Double el valor que el usuario a escrito para convertir
        val valoraconvertir = binding.etcantidad.text.toString().toDouble()
        //resto la pos origen y destino para saber cuanta diferencia hay entre una y otra
        val resta = unidadOrigen - unidadDestino
        if (resta > 0) {
            resultadofinal = valoraconvertir * Math.pow(10.0, resta.toDouble())
            /*
            Para cambiar el color del texto a partir del = cuento las letras que hay hasta el propio =
            y a partir de ahi pinto hasta el final.
             */
            val mensaje: SpannableString = SpannableString("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
            val colorspan: ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF018786"))
            val letras = contarletras(mensaje.toString())
            mensaje.setSpan(colorspan,letras +1,mensaje.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            binding.tvresultado.setText(mensaje)

        }
        if (resta < 0) {
            resultadofinal = valoraconvertir / Math.pow(10.0, -resta.toDouble())
            /*
            Para cambiar el color del texto a partir del = cuento las letras que hay hasta el propio =
            y a partir de ahi pinto hasta el final.
             */
            val mensaje: SpannableString = SpannableString("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
            val colorspan: ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF018786"))
            val letras = contarletras(mensaje.toString())
            mensaje.setSpan(colorspan,letras +1,mensaje.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            binding.tvresultado.setText(mensaje)
        }
    }

    private fun convierteHdd() {
        //obtengo la unidad seleccionada en cada spinner
        val unidadOrigen: Int = sporigen.getSelectedItemPosition()
        val unidadDestino: Int = spdestino.getSelectedItemPosition()
        valorescrito = binding.etcantidad.text.toString()

        //paso a Double el valor que el usuario a escrito para convertir
        val valoraconvertir = binding.etcantidad.text.toString().toDouble()
        //resto la pos origen y destino para saber cuanta diferencia hay entre una y otra
        val resta = unidadOrigen - unidadDestino
        if (resta > 0) {
            resultadofinal = valoraconvertir * Math.pow(1024.0, -resta.toDouble())
            /*
            Para cambiar el color del texto a partir del = cuento las letras que hay hasta el propio =
            y a partir de ahi pinto hasta el final.
             */
            val mensaje: SpannableString = SpannableString("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
            val colorspan: ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF018786"))
            val letras = contarletras(mensaje.toString())
            mensaje.setSpan(colorspan,letras +1,mensaje.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            binding.tvresultado.setText(mensaje)

        }
        if (resta < 0) {
            resultadofinal = valoraconvertir / Math.pow(1024.0, resta.toDouble())
            /*
            Para cambiar el color del texto a partir del = cuento las letras que hay hasta el propio =
            y a partir de ahi pinto hasta el final.
             */
            val mensaje: SpannableString = SpannableString("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
            val colorspan: ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF018786"))
            val letras = contarletras(mensaje.toString())
            mensaje.setSpan(colorspan,letras +1,mensaje.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            binding.tvresultado.setText(mensaje)
        }
    }

    private fun convierteTemperatura() {
        val medidaOrigen: Int = sporigen.getSelectedItemPosition()
        val medidaDestino: Int = spdestino.getSelectedItemPosition()
        valorescrito = binding.etcantidad.text.toString()
        val valoraconvertir = binding.etcantidad.text.toString().toDouble()
        if (medidaOrigen == 0 && medidaDestino == 1) {
            resultadofinal = valoraconvertir + 273.15
            /*
            Para cambiar el color del texto a partir del = cuento las letras que hay hasta el propio =
            y a partir de ahi pinto hasta el final.
             */
            val mensaje: SpannableString = SpannableString("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
            val colorspan: ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF018786"))
            val letras = contarletras(mensaje.toString())
            mensaje.setSpan(colorspan,letras +1,mensaje.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            binding.tvresultado.setText(mensaje)
        } else if (medidaOrigen == 1 && medidaDestino == 0) {
            resultadofinal = valoraconvertir - 273.15
            /*
            Para cambiar el color del texto a partir del = cuento las letras que hay hasta el propio =
            y a partir de ahi pinto hasta el final.
             */
            val mensaje: SpannableString = SpannableString("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
            val colorspan: ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF018786"))
            val letras = contarletras(mensaje.toString())
            mensaje.setSpan(colorspan,letras +1,mensaje.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            binding.tvresultado.setText(mensaje)
        }
    }

    private fun conviertePeso() {
        //obtengo la unidad seleccionada en cada spinner
        val unidadOrigen: Int = sporigen.getSelectedItemPosition()
        val unidadDestino: Int = spdestino.getSelectedItemPosition()
        valorescrito = binding.etcantidad.text.toString()

        //paso a Double el valor que el usuario a escrito para convertir
        val valoraconvertir = binding.etcantidad.text.toString().toDouble()
        //resto la pos origen y destino para saber cuanta diferencia hay entre una y otra
        val resta = unidadOrigen - unidadDestino
        if (resta > 0) {
            resultadofinal = valoraconvertir * Math.pow(1000.0, -resta.toDouble())
            /*
            Para cambiar el color del texto a partir del = cuento las letras que hay hasta el propio =
            y a partir de ahi pinto hasta el final.
             */
            val mensaje: SpannableString = SpannableString("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
            val colorspan: ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF018786"))
            val letras = contarletras(mensaje.toString())
            mensaje.setSpan(colorspan,letras +1,mensaje.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            binding.tvresultado.setText(mensaje)
        }
        if (resta < 0) {
            resultadofinal = valoraconvertir / Math.pow(1000.0, resta.toDouble())
            /*
            Para cambiar el color del texto a partir del = cuento las letras que hay hasta el propio =
            y a partir de ahi pinto hasta el final.
             */
            val mensaje: SpannableString = SpannableString("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
            val colorspan: ForegroundColorSpan = ForegroundColorSpan(Color.parseColor("#FF018786"))
            val letras = contarletras(mensaje.toString())
            mensaje.setSpan(colorspan,letras +1,mensaje.length,Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
            binding.tvresultado.setText(mensaje)
        }
    }

    fun contarletras(texto: String): Int{
        var numletras: Int = 0
        for (l in texto){
            if(l != '='){
                numletras++
            }else{
                break
            }
        }
        return numletras
    }
}