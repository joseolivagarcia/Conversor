package joseoliva.com.conversor.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.conversor.R
import joseoliva.com.conversor.model.Unidad

/*
Este ViewHolder debe recibir una vista y extender de RecyclerView.ViewHolder que recibe esa vista
Aqui obtenemos los datos que necesitemos para rellenar cada item
 */
class UnidadViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val card = view.findViewById<ConstraintLayout>(R.id.cardview)

    val imagenUnidad = view.findViewById<ImageView>(R.id.ivunidad)
    val nombreMedida = view.findViewById<TextView>(R.id.tvnombremedida)
    val unidades = view.findViewById<TextView>(R.id.tvunidades)

    fun render(unidadmodel: Unidad, onClickListener: (Unidad) -> Unit) {

        imagenUnidad.setImageResource(unidadmodel.imagen)
        nombreMedida.text = unidadmodel.medida
        unidades.text = unidadmodel.unidades

        //hago clicable el item entero
        itemView.setOnClickListener {
            onClickListener(unidadmodel)
        }

    }

}