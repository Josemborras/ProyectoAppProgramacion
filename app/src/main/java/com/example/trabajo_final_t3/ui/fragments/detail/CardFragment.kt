package com.example.trabajo_final_t3.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.RecipesResponseItem
import com.example.trabajo_final_t3.databinding.FragmentCardBinding
import com.example.trabajo_final_t3.ui.adapters.SimilarRecipeAdapter
import com.example.trabajo_final_t3.viewModel.MyViewModel

class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private val myViewModel by activityViewModels<MyViewModel>()
    private lateinit var adapterSimilar: SimilarRecipeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.getRecipeFragment().observe(viewLifecycleOwner){recipe ->
            recipe.id?.let { myViewModel.recipeCardAddvm(it).observe(viewLifecycleOwner){recipeCard ->
                Glide.with(this).load(recipeCard?.url).into(binding.imageView)
            } }

            myViewModel.getListSimilar().observe(viewLifecycleOwner){listSimilars ->
                configRecyclerSimilar(listSimilars)
            }
        }
    }

    private fun configRecyclerSimilar(list: ArrayList<RecipesResponseItem>) {
        adapterSimilar = SimilarRecipeAdapter(myViewModel, viewLifecycleOwner, object : SimilarRecipeAdapter.MyClick{
            override fun onClick(receta: RecipesResponseItem) {
                myViewModel.setBoolean(true)
                myViewModel.setRecipeSearch(receta)
                findNavController().navigate(R.id.action_detailRecipeFragment_self)
            }
        })
        adapterSimilar.setRecipes(list)
        binding.rvSimilar3.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSimilar3.adapter = adapterSimilar
    }
}
