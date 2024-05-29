package com.example.trabajo_final_t3.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.RecipesResponseItem
import com.example.trabajo_final_t3.data.models.StepsRecipe.StepsResponseItem
import com.example.trabajo_final_t3.databinding.FragmentStepsBinding
import com.example.trabajo_final_t3.ui.adapters.ElabAdapter
import com.example.trabajo_final_t3.ui.adapters.SimilarRecipeAdapter
import com.example.trabajo_final_t3.viewModel.MyViewModel

class StepsFragment : Fragment() {

    private lateinit var binding: FragmentStepsBinding
    private val myViewModel by activityViewModels<MyViewModel>()
    private lateinit var adapterElab: ElabAdapter
    private lateinit var adapterSimilar: SimilarRecipeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStepsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myViewModel.getBoolean().observe(viewLifecycleOwner){boolean ->
            if (boolean == true){
                myViewModel.getRecipeSearch().observe(viewLifecycleOwner){recipeSearch ->
                    recipeSearch.id?.let {recipeId ->
                        myViewModel.getInstructions(recipeId).observe(viewLifecycleOwner) {
                            configRecyclerElabs(it)
                        }
                    }
                    recipeSearch.id?.let {recipeId ->
                        myViewModel.getSimilars(recipeId).observe(viewLifecycleOwner){
                            configRecyclerSimilar(it)
                        }
                    }
                }
            }else{
                myViewModel.getRecipeRandom().observe(viewLifecycleOwner){recipe ->
                    recipe.id?.let {
                        myViewModel.getRecipeRandom().observe(viewLifecycleOwner){recipe ->
                            recipe.id?.let {recipeId ->
                                myViewModel.getInstructions(recipeId).observe(viewLifecycleOwner) {
                                    configRecyclerElabs(it)
                                }
                            }
                            recipe.id?.let {
                                myViewModel.getSimilars(it).observe(viewLifecycleOwner){
                                    configRecyclerSimilar(it)
                                }
                            }
                        }
                    }
                }
            }
        }

//        myViewModel.getRecipeRandom().observe(viewLifecycleOwner){recipe ->
//            recipe.id?.let {
//                myViewModel.getInstructions(it).observe(viewLifecycleOwner) {
//                    configRecyclerElabs(it)
//                }
//            }
//
//            recipe.id?.let {
//                myViewModel.getSimilars(it).observe(viewLifecycleOwner){
//                    configRecyclerSimilar(it)
//                }
//            }
//
//        }

    }

    private fun configRecyclerSimilar(list: ArrayList<RecipesResponseItem>) {
        adapterSimilar = SimilarRecipeAdapter(myViewModel, viewLifecycleOwner, object : SimilarRecipeAdapter.MyClick{
            override fun onClick(receta: RecipesResponseItem) {

            }
        })
        adapterSimilar.setRecipes(list)

        binding.rvSimilar1.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSimilar1.adapter = adapterSimilar
    }
    private fun configRecyclerElabs(list: List<StepsResponseItem>) {
        adapterElab = ElabAdapter()
        adapterElab.setElabs(list)

        binding.rvElab.layoutManager = LinearLayoutManager(requireContext())
        binding.rvElab.adapter = adapterElab
    }
}
