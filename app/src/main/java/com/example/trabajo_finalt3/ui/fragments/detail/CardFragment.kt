package com.example.trabajo_finalt3.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.trabajo_finalT3.databinding.FragmentCardBinding
import com.example.trabajo_finalt3.MainActivity
import com.example.trabajo_finalt3.MyViewModel
import com.example.trabajo_finalt3.model.data.ListRecipe.RecipeItem
import com.example.trabajo_finalt3.ui.adapters.SimilarRecipeAdapter

class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private val viewModel by activityViewModels<MyViewModel>()
    private lateinit var adapter: SimilarRecipeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = 632583
        viewModel.getCardImage(id).observe(viewLifecycleOwner){cardImage ->
            Glide.with(this).load(cardImage.url).into(binding.imageView)

        }

        viewModel.getSimilars(id).observe(viewLifecycleOwner){
            configRecyclerSimilar(it)
        }
    }

    private fun configRecyclerSimilar(list: ArrayList<RecipeItem>) {
        adapter = SimilarRecipeAdapter(viewModel, viewLifecycleOwner)
        adapter.setRecipes(list)

        binding.rvSimilar3.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvSimilar3.adapter = adapter
    }
}
