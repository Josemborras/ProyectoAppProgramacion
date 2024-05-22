package com.example.trabajo_finalt3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_finalT3.databinding.HolderIngredientBinding
import com.example.trabajo_finalt3.model.data.Recipe.ExtendedIngredient

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientHolder>() {

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

