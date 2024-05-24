package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.data.models.ingredients.Resultado
import com.example.trabajo_final_t3.databinding.IngredientesBinding
import kotlin.time.Duration.Companion.milliseconds

class Ingredientes(): RecyclerView.Adapter<Ingredientes.IngredientesCelda>() {

    private val lista = ArrayList<Resultado>()

    inner class IngredientesCelda(val binding: IngredientesBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientesCelda {
        return IngredientesCelda(IngredientesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: IngredientesCelda, position: Int) {
        var item = lista[position] // para inicializar la variable

        Glide.with(holder.itemView)
            .load("https://img.spoonacular.com/ingredients_100x100/" + item.image)
            .into(holder.binding.imvImagenIngredienteEnRV)

        holder.binding.tvIngredientName.text = item.name
    }

    fun addIngredient(ingredient: Resultado){
        lista.add(ingredient)
        notifyDataSetChanged()
    }

    fun clearIngredients(){
        lista.clear()
        notifyDataSetChanged()
    }

}