package com.example.trabajo_final_t3.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo_final_t3.data.models.Recipe.ExtendedIngredient
import com.example.trabajo_final_t3.data.models.ListRecipe.RecipeItem
import com.example.trabajo_final_t3.databinding.FragmentIngredientsBinding
import com.example.trabajo_final_t3.ui.adapters.IngredientsAdapter
import com.example.trabajo_final_t3.ui.adapters.SimilarRecipeAdapter
import com.example.trabajo_final_t3.viewModel.ViewModel

class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding
    private val viewModel by activityViewModels<ViewModel>()
    private lateinit var adapter: IngredientsAdapter
    private lateinit var adapterSimilar: SimilarRecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = 632583
        viewModel.getRecipeInfo(id).observe(viewLifecycleOwner) { recipeComplete ->
            recipeComplete?.let {
                configRecyclerIngredients(it.extendedIngredients)
            }
        }

        viewModel.getSimilars(id).observe(viewLifecycleOwner){
            configRecyclerSimilar(it)
        }
    }

    private fun configRecyclerSimilar(list: ArrayList<RecipeItem>) {
        adapterSimilar = SimilarRecipeAdapter(viewModel, viewLifecycleOwner)
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

