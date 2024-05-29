package com.example.trabajo_final_t3.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.data.models.ListRecipe.RecipeItem
import com.example.trabajo_final_t3.data.models.AllRecipeInfo.ExtendedIngredient
import com.example.trabajo_final_t3.databinding.FragmentIngredientsBinding
import com.example.trabajo_final_t3.ui.MainActivity
import com.example.trabajo_final_t3.ui.adapters.IngredientsAdapter
import com.example.trabajo_final_t3.ui.adapters.SimilarRecipeAdapter
import com.example.trabajo_final_t3.viewModel.MyViewModel

class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding
    private val myViewModel by activityViewModels<MyViewModel>()
    private lateinit var adapter: IngredientsAdapter
    private lateinit var adapterSimilar: SimilarRecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.getBoolean().observe(viewLifecycleOwner){boolean ->
            if (boolean == true){
                myViewModel.getRecipeSearch().observe(viewLifecycleOwner){recipeSearch ->
                    recipeSearch.id?.let {recipeId ->
                        myViewModel.getRecipeInfo(recipeId).observe(viewLifecycleOwner) { recipeComplete ->
                            recipeComplete?.let {
                                configRecyclerIngredients(it.extendedIngredients)
                            }
                        }
                    }
                }
            }else{
                myViewModel.getRecipeRandom().observe(viewLifecycleOwner){recipe ->
                    recipe.id?.let {
                        myViewModel.getRecipeRandom().observe(viewLifecycleOwner){recipeSearch ->
                            recipeSearch.id?.let {recipeId ->
                                myViewModel.getRecipeInfo(recipeId).observe(viewLifecycleOwner) { recipeComplete ->
                                    recipeComplete?.let {
                                        configRecyclerIngredients(it.extendedIngredients)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun configRecyclerSimilar(list: ArrayList<RecipeItem>) {
        adapterSimilar = SimilarRecipeAdapter(myViewModel, viewLifecycleOwner)
        adapterSimilar.setRecipes(list)

        binding.rvSimilar2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSimilar2.adapter = adapterSimilar
    }

    private fun configRecyclerIngredients(list: List<ExtendedIngredient>) {
        adapter = IngredientsAdapter()
        adapter.setIngredients(list)

        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter
    }
}

