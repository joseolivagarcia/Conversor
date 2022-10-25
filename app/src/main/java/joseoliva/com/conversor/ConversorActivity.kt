package joseoliva.com.conversor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import joseoliva.com.conversor.databinding.ActivityConversorBinding

class ConversorActivity : AppCompatActivity() {

    lateinit var binding: ActivityConversorBinding
    var numarray = 0 //necesito esta var para indicarle que array de unidades usare en el spinner

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
                return
            }
        }
        //relleno los spinners


    }
}