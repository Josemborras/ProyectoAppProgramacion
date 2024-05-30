package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_final_t3.data.models.AllRecipeInfo.ExtendedIngredient
import com.example.trabajo_final_t3.databinding.HolderIngredientBinding

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientHolder>() {

    /*
     * Adaptador para el listado de ingredientes
     * para las recetas
     * */

    private var lista = ArrayList<ExtendedIngredient>()

    inner class IngredientHolder(val binding: HolderIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderIngredientBinding.inflate(layoutInflater, parent, false)
        return IngredientHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        val ingredient = lista[position]
        holder.binding.tvName.text = ingredient.name
        holder.binding.tvUnit.text = ingredient.unit
        holder.binding.tvCantidad.text = ingredient.amount.toString()
    }

    override fun getItemCount() = lista.size

    fun setIngredients(ingredients: List<ExtendedIngredient>) {
        this.lista.clear()
        this.lista.addAll(ingredients)
        notifyDataSetChanged()
    }
}

