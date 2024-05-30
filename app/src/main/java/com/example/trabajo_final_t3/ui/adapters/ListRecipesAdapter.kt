package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.data.models.AllRecipeInfo.Recipe
import com.example.trabajo_final_t3.databinding.FragmentceldarecetaBinding

// el listado lo metemos dentro como parametro para cuando llamemos al adaptador pasar el listado
class ListRecipesAdapter(val recetas: ArrayList<Recipe>, val listener: MyClick): RecyclerView.Adapter<ListRecipesAdapter.disenioPlantilla>(){

    /*
     * Adaptador para el listado de recetas que se obtienen del buscador
     * */

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
}
