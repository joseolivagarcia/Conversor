package joseoliva.com.conversor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.conversor.adapter.UnidadAdapter
import joseoliva.com.conversor.databinding.ActivityMainBinding
import joseoliva.com.conversor.model.Unidad
import joseoliva.com.conversor.provider.UnidadProvider

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val medidasLista: List<Unidad> = UnidadProvider.unidadList
    lateinit var adapter: UnidadAdapter
    var linearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

    }

    private fun initRecyclerView() {
        //inicializo el adapter y se lo paso al recyclerview del xml
        //al adapter le paso los dos parametros que necesita, la lista y una lambda
        adapter = UnidadAdapter(
            listaMedidas = medidasLista,
            onClickListener = {medidaitem -> onItemSelected(medidaitem)}
        )
        binding.rvmedidas.layoutManager = linearLayoutManager
        binding.rvmedidas.adapter = adapter

    }

    private fun onItemSelected(medidaitem: Unidad) {
        val intent = Intent(this,ConversorActivity::class.java)
        intent.putExtra("medida", medidaitem.medida)
        intent.putExtra("imagen", medidaitem.imagen)
        startActivity(intent)
    }

    @Override
    override fun onBackPressed() {

    }
}