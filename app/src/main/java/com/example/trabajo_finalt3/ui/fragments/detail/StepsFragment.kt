package com.example.trabajo_finalt3.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo_finalT3.databinding.FragmentStepsBinding
import com.example.trabajo_finalt3.MyViewModel
import com.example.trabajo_finalt3.model.data.ListRecipe.RecipeItem
import com.example.trabajo_finalt3.model.data.Steps.Step
import com.example.trabajo_finalt3.model.data.Steps.StepsResponseItem
import com.example.trabajo_finalt3.ui.adapters.ElabAdapter
import com.example.trabajo_finalt3.ui.adapters.SimilarRecipeAdapter
import com.example.trabajo_finalt3.ui.adapters.StepAdapter

class StepsFragment : Fragment() {

    private lateinit var binding: FragmentStepsBinding
    private val viewModel by activityViewModels<MyViewModel>()
    private lateinit var adapterElab: ElabAdapter
    private lateinit var adapterSimilar: SimilarRecipeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStepsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = 324694
        viewModel.getInstructions(id).observe(viewLifecycleOwner) {
            configRecyclerElabs(it)
        }

        viewModel.getSimilars(id).observe(viewLifecycleOwner){
            configRecyclerSimilar(it)
        }
    }

    private fun configRecyclerSimilar(list: ArrayList<RecipeItem>) {
        adapterSimilar = SimilarRecipeAdapter(viewModel, viewLifecycleOwner)
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
