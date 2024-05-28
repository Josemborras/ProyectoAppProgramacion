package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.data.models.Recipe.Recipe
import com.example.trabajo_final_t3.databinding.FragmentceldarecetaBinding

//el listado lo metemos dentro como parametro para cuando llamemos al adaptador pasar el listado
class Adaptadorlistado(val recetas: ArrayList<Recipe>, val listener: MyClick): RecyclerView.Adapter<Adaptadorlistado.disenioPlantilla>(){


    inner class disenioPlantilla(val binding: FragmentceldarecetaBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface MyClick {
        fun onClick(receta: Recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): disenioPlantilla {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentceldarecetaBinding.inflate(layoutInflater, parent, false)
        return disenioPlantilla(binding)
    }

    override fun getItemCount(): Int {
        return recetas.size
    }

    override fun onBindViewHolder(holder: disenioPlantilla, position: Int) {
        val listRecetas = recetas[position]

        holder.binding.txNombrereceta.text = listRecetas.title
        Glide.with(holder.itemView).load(listRecetas.image).into(holder.binding.imgReceta)
        holder.itemView.setOnClickListener {
            listener.onClick(listRecetas)
        }


    }


//    fun refrescarListado(lista: List<RecipesRandom?>?){
//        recetas.clear()
//        if(lista != null){
//            recetas.addAll(lista)
//        }
//        notifyItemRangeChanged(0, itemCount)
//    }

}
