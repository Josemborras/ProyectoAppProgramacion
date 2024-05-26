package com.example.trabajo_finalt3.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trabajo_finalT3.databinding.HolderRecipeListBinding
import com.example.trabajo_finalt3.MyViewModel
import com.example.trabajo_finalt3.model.data.ListRecipe.RecipeItem
import com.example.trabajo_finalt3.model.data.Recipe.RecipeResponse

class SimilarRecipeAdapter(private val viewModel: MyViewModel, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<SimilarRecipeAdapter.RecipeListHolder>() {


    private var currentLiveData: MutableLiveData<RecipeResponse>? = null
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

