package com.example.trabajo_finalt3.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.trabajo_finalT3.databinding.FragmentDetailRecipeBinding
import com.example.trabajo_finalt3.MainActivity
import com.example.trabajo_finalt3.MyViewModel
import com.example.trabajo_finalt3.model.data.ListRecipe.RecipeItem
import com.example.trabajo_finalt3.model.data.Recipe.ListRecipe
import com.example.trabajo_finalt3.ui.adapters.SimilarRecipeAdapter
import com.example.trabajo_finalt3.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class DetailRecipeFragment : Fragment() {

    private lateinit var binding: FragmentDetailRecipeBinding
    private val viewModel by activityViewModels<MyViewModel>()
    private lateinit var adapter: SimilarRecipeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewpager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Ingredients"
                1 -> "Steps"
                2 -> "Card"
                else -> null
            }
        }.attach()

        val id = 632583
        viewModel.getRecipeInfo(id).observe(viewLifecycleOwner){
            (requireActivity() as MainActivity).supportActionBar?.title = it.title
            Glide.with(this).load(it.image).into(binding.ivRecipe)
            viewModel.getSimilars(id).observe(viewLifecycleOwner){
                configRecycler(it)
            }
        }
    }

    private fun configRecycler(list: ArrayList<RecipeItem>) {
        adapter = SimilarRecipeAdapter()
        adapter.setRecipes(list)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerView.adapter = adapter
    }
}