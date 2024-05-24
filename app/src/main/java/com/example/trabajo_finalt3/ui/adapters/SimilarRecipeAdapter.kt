package com.example.trabajo_finalt3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo_finalT3.databinding.HolderRecipeListBinding
import com.example.trabajo_finalt3.model.data.ListRecipe.RecipeItem
import com.example.trabajo_finalt3.model.data.Recipe.ListRecipe

class SimilarRecipeAdapter : RecyclerView.Adapter<SimilarRecipeAdapter.RecipeListHolder>() {

    private var lista = ArrayList<RecipeItem>()

    inner class RecipeListHolder(val binding: HolderRecipeListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderRecipeListBinding.inflate(layoutInflater, parent, false)
        return RecipeListHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeListHolder, position: Int) {
        val recipes = lista[position]
        holder.binding.tvTrecipe.text = recipes.title

    }
    override fun getItemCount() = lista.size

    fun setRecipes(recipes: ArrayList<RecipeItem>) {
        this.lista.clear()
        this.lista.addAll(recipes)
        notifyDataSetChanged()
    }
}

