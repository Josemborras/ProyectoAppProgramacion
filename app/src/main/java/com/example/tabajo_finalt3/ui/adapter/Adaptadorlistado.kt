package com.example.tabajo_finalt3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tabajo_finalt3.data.models.RecipesRandom
import com.example.tabajo_finalt3.databinding.FragmentceldarecetaBinding

class Adaptadorlistado( val listener: MyClick): RecyclerView.Adapter<Adaptadorlistado.disenioPlantilla>(){

    private val recetas = ArrayList<RecipesRandom?>()

    inner class disenioPlantilla(val binding: FragmentceldarecetaBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface MyClick {
        fun onClick(receta: RecipesRandom)
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

        holder.binding.txNombrereceta.text = listRecetas?.recipes.toString()
        Glide.with(holder.itemView).load(listRecetas?.recipes?.get(position)?.image).into(holder.binding.imgReceta)
        holder.itemView.setOnClickListener {
            if (listRecetas != null) {
                listener.onClick(listRecetas)
            }
        }


    }

    fun refrescarListado(lista: List<RecipesRandom?>?){
        recetas.clear()
        if(lista != null){
            recetas.addAll(lista)
        }
        notifyItemRangeChanged(0, itemCount)
    }

}
