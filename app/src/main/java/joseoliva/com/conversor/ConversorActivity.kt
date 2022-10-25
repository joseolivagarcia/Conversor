package joseoliva.com.conversor

import android.os.Bundle
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
            when (medidarecibida) {
                "Peso" -> {
                    conviertePeso()
                }
                "Temperatura" -> {

                }
                "Capacidad HDD" -> {

                }
            }
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
            binding.tvresultado.setText("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")

        }
        if (resta < 0) {
            resultadofinal = valoraconvertir / Math.pow(1000.0, resta.toDouble())
            binding.tvresultado.setText("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
        }
        if (resta == 0) {
            resultadofinal = valoraconvertir
            binding.tvresultado.setText("$valorescrito ${sporigen.selectedItem.toString()} = $resultadofinal ${spdestino.selectedItem.toString()}")
        }

    }
}