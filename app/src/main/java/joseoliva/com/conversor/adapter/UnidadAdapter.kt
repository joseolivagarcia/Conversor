package joseoliva.com.conversor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import joseoliva.com.conversor.R
import joseoliva.com.conversor.model.Unidad

/*
Esta clase es el adaptador para pintar cada item en el recyclerview
Necesitara de una clase ViewHolder que hemos llamado UnidadViewHolder
Recibira por parametro la lista necesaria y una funcion lambda para cuando
hagamos click en cada item de la lista
Extendera de RecyclerView.Adapter con su ViewHolder
 */
class UnidadAdapter(
    private var listaMedidas: List<Unidad>,
    private val onClickListener: (Unidad) -> Unit
) : RecyclerView.Adapter<UnidadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnidadViewHolder {
        /*
        en este metodo usamos un LayoutInflater para inflar la vista de nuestro
        item y devolverselo al ViewHolder
         */
        val layoutInflater = LayoutInflater.from(parent.context)
        return UnidadViewHolder(layoutInflater.inflate(R.layout.item_unidad,parent,false))
    }

    override fun onBindViewHolder(holder: UnidadViewHolder, position: Int) {
        /*
        en este metodo vamos a recorrer todos los items que tenga la lista
        y vamos a mandarlo a la funcion render del viewHolder que los "pinta"
        con los datos necesarios
         */
        val item = listaMedidas[position]
        holder.render(item,onClickListener)

    }

    override fun getItemCount(): Int {
        return listaMedidas.size
    }

}