package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.RecipesResponseItem
import com.example.trabajo_final_t3.databinding.HolderRecipeListBinding
import com.example.trabajo_final_t3.viewModel.MyViewModel


class SimilarRecipeAdapter(private val myViewModel: MyViewModel, private val lifecycleOwner: LifecycleOwner, val listener: SimilarRecipeAdapter.MyClick) : RecyclerView.Adapter<SimilarRecipeAdapter.RecipeListHolder>() {

    /*
     * Adaptador para el listado de recetas similares a
     * la receta seleccionada
     * */

    private var lista = ArrayList<RecipesResponseItem>()

    inner class RecipeListHolder(val binding: HolderRecipeListBinding) : RecyclerView.ViewHolder(binding.root)

    interface MyClick {
        fun onClick(receta: RecipesResponseItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderRecipeListBinding.inflate(layoutInflater, parent, false)
        return RecipeListHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeListHolder, position: Int) {
        val recipes = lista[position]
        holder.binding.tvTrecipe.text = recipes.title


        recipes.id?.let {
            myViewModel.getRecipeInfo(it).observe(lifecycleOwner){ similar ->
                Glide.with(holder.binding.imageView5.context)
                    .load(similar.image)
                    .into(holder.binding.imageView5)
            }
        }

        holder.itemView.setOnClickListener {
            listener.onClick(recipes)
        }
    }
    override fun getItemCount() = lista.size

    fun setRecipes(recipes: ArrayList<RecipesResponseItem>) {
        this.lista.clear()
        this.lista.addAll(recipes)
        notifyDataSetChanged()
    }
}

