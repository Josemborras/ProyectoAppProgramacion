package com.example.trabajo_final_t3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.databinding.HolderRecipeListBinding
import com.example.trabajo_final_t3.data.models.ListRecipe.RecipeItem
import com.example.trabajo_final_t3.data.models.Recipe.Recipe
import com.example.trabajo_final_t3.viewModel.ViewModel


class SimilarRecipeAdapter(private val viewModel: ViewModel, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<SimilarRecipeAdapter.RecipeListHolder>() {


    private var currentLiveData: MutableLiveData<Recipe>? = null
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


        viewModel.getRecipeInfo(recipes.id).observe(lifecycleOwner){similar ->
            Glide.with(holder.binding.imageView5.context)
                .load(similar.image)
                .into(holder.binding.imageView5)
        }
    }
    override fun getItemCount() = lista.size

    fun setRecipes(recipes: ArrayList<RecipeItem>) {
        this.lista.clear()
        this.lista.addAll(recipes)
        notifyDataSetChanged()
    }
}

