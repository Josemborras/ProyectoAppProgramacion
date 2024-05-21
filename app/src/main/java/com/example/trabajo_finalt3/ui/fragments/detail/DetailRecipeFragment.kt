package com.example.trabajo_finalt3.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.trabajo_finalT3.R
import com.example.trabajo_finalT3.databinding.FragmentDetailRecipeBinding
import com.example.trabajo_finalt3.MainActivity
import com.example.trabajo_finalt3.MyViewModel
import com.google.android.material.tabs.TabLayoutMediator


class DetailRecipeFragment : Fragment() {

    private lateinit var binding: FragmentDetailRecipeBinding
    private val viewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRecipe().observe(viewLifecycleOwner){recipeListItem ->
            (requireActivity() as MainActivity).supportActionBar?.title = recipeListItem.title

            viewModel.getRecipeInfo(recipeListItem.id).observe(viewLifecycleOwner){recipeComplete ->
                Glide.with(this).load(recipeComplete.image).into(binding.ivRecipe)
            }

        }
    }
}