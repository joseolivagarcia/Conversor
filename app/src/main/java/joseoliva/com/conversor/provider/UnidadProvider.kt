package joseoliva.com.conversor.provider

import joseoliva.com.conversor.R
import joseoliva.com.conversor.model.Unidad

/*
esta clase hace de provider(porveedor) de datos.
uso este provider porque voy a pasar unos datos preestablecidos por mi,
es decir que no los obtengo de ninguna bbdd ni de ningun otro sitio
*/
class UnidadProvider {
    companion object{
        val unidadList = listOf<Unidad>(
            Unidad(R.drawable.peso,"Peso","Ton, Kg, g"),
            Unidad(R.drawable.temperatura,"Temperatura","Celsius, Kelvin"),
            Unidad(R.drawable.hdd,"Capacidad HDD","Tb, Gb, Mb"),
            Unidad(R.drawable.longitud,"Longitud","mm, cm, dm, m, dam, hm, km "),
            Unidad(R.drawable.icobarba,"Longitud (Extraña)","Segundos-Barba, metros")
        )
    }
}