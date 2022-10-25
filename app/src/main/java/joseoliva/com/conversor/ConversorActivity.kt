package joseoliva.com.conversor

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import joseoliva.com.conversor.databinding.ActivityConversorBinding

class ConversorActivity : AppCompatActivity() {

    lateinit var binding: ActivityConversorBinding
    var numarray: Int = 0 //necesito esta var para indicarle que array de unidades usare en el spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val medidarecibida = intent.extras!!.getString("medida")
        val medidaimagenrecibida = intent.extras!!.getInt("imagen")

        binding.ivimagenmedida.setImageResource(medidaimagenrecibida)
        /*
        creo un condicional para segun que medida reciba asignarle un entero a la var numarray
         */
        when(medidarecibida){
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
        val adapspinner = ArrayAdapter.createFromResource(this, numarray, android.R.layout.simple_spinner_item)
        adapspinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sporigen.adapter = adapspinner
        binding.spdestino.adapter = adapspinner

        /*
        doy funcionalidad al boton de convertir. Segun la medida que hubiese seleccionado
        ire a una funcion u otra para convertir.
         */
        binding.btnconversion.setOnClickListener {

            when(medidarecibida){
                "Peso" -> {
                    Toast.makeText(this,"voy a convertir $medidarecibida",Toast.LENGTH_SHORT).show()
                }
                "Temperatura" -> {
                    Toast.makeText(this,"voy a convertir $medidarecibida",Toast.LENGTH_SHORT).show()
                }
                "Capacidad HDD" -> {
                    Toast.makeText(this,"voy a convertir $medidarecibida",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}