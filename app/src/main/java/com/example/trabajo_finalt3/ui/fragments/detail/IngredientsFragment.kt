package com.example.trabajo_finalt3.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo_finalT3.databinding.FragmentIngredientsBinding
import com.example.trabajo_finalt3.MyViewModel
import com.example.trabajo_finalt3.model.data.Recipe.ExtendedIngredient
import com.example.trabajo_finalt3.ui.adapters.IngredientsAdapter

class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding
    private val viewModel by activityViewModels<MyViewModel>()
    private lateinit var adapter: IngredientsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = 324694
        viewModel.getRecipeInfo(id).observe(viewLifecycleOwner) { recipeComplete ->
            recipeComplete?.let {
                configRecyclerIngredients(it.extendedIngredients)
            }
        }
    }

    private fun configRecyclerIngredients(list: List<ExtendedIngredient>) {
        adapter = IngredientsAdapter()
        adapter.setIngredients(list)

        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter
    }
}

