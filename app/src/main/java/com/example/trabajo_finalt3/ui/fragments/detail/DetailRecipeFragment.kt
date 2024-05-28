package com.example.trabajo_finalt3.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.trabajo_finalT3.R
import com.example.trabajo_finalT3.databinding.FragmentDetailRecipeBinding
import com.example.trabajo_finalt3.MainActivity
import com.example.trabajo_finalt3.MyViewModel
import com.example.trabajo_finalt3.model.data.ListRecipe.RecipeItem
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
        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab, position ->
            tab.customView = createTabView(position)
        }.attach()

        val id = 632583
        viewModel.getRecipeInfo(id).observe(viewLifecycleOwner){
            (requireActivity() as MainActivity).supportActionBar?.title = it.title
            binding.collapsingToolbar.title = it.title
            Glide.with(this).load(it.image).into(binding.ivRecipe)

        }

    }
    private fun createTabView(position: Int): View {
        val tabView = LayoutInflater.from(requireContext()).inflate(R.layout.tab_layout, null)
        val tabText = tabView.findViewById<TextView>(R.id.tab_text)
        tabText.text = when (position) {
            0 -> "Ingredients"
            1 -> "Steps"
            2 -> "Card"
            else -> null
        }
        return tabView
    }
}