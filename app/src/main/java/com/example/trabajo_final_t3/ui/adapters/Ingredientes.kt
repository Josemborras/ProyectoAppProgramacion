package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.data.models.ingredients.IngredientsResponse
import com.example.trabajo_final_t3.data.models.ingredients.Result
import com.example.trabajo_final_t3.databinding.IngredientesBinding

class Ingredientes(val ingrediente: Result): RecyclerView.Adapter<Ingredientes.IngredientesCelda>() {

    private val lista = ArrayList<IngredientsResponse>()

    inner class IngredientesCelda(val binding: IngredientesBinding): ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientesCelda {
        return IngredientesCelda(IngredientesBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: IngredientesCelda, position: Int) {
        val item = lista[position].results[position]

        // val item = ingrediente

        /*
         * hacer la petición de la imagen cogiendo el string de aquí: item.image
         * para hacerla mira en: https://spoonacular.com/food-api/docs#Show-Images
         *
         * */
        Glide.with(holder.itemView)
            .load("")
            .into(holder.binding.imvImagenIngredienteEnRV)

        holder.binding.tvIngredientName.text = item.name
    }


}