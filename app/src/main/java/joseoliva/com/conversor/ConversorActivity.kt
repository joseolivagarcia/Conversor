package joseoliva.com.conversor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import joseoliva.com.conversor.databinding.ActivityConversorBinding

class ConversorActivity : AppCompatActivity() {

    lateinit var binding: ActivityConversorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConversorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val medidarecibida = intent.extras!!.getString("medida")
        Toast.makeText(this,"recibo: $medidarecibida",Toast.LENGTH_SHORT).show()
    }
}