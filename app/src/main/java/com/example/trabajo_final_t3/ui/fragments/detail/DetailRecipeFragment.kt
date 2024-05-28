package com.example.trabajo_final_t3.ui.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.databinding.FragmentDetailRecipeBinding
import com.example.trabajo_final_t3.ui.MainActivity
import com.example.trabajo_final_t3.viewModel.ViewModel
import com.google.android.material.tabs.TabLayout

class DetailRecipeFragment : Fragment() {

    private lateinit var binding: FragmentDetailRecipeBinding
    private val viewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        setupTabs(savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = 632583
        viewModel.getRecipeInfo(id).observe(viewLifecycleOwner) {
            (requireActivity() as MainActivity).supportActionBar?.title = it.title
            binding.collapsingToolbar.title = it.title
            Glide.with(this).load(it.image).into(binding.ivRecipe)
        }
    }

    private fun setupTabs(savedInstanceState: Bundle?) {
        val tabLayout = binding.tablayout
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView(0)))
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView(1)))
        tabLayout.addTab(tabLayout.newTab().setCustomView(createTabView(2)))

        // Load the first fragment by default
        if (savedInstanceState == null) {
            replaceFragment(IngredientsFragment())
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (it.position) {
                        0 -> replaceFragment(IngredientsFragment())
                        1 -> replaceFragment(StepsFragment())
                        2 -> replaceFragment(CardFragment())
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle unselected state if needed
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle reselected state if needed
            }
        })
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

    private fun replaceFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

