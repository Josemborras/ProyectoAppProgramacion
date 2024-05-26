package com.example.trabajo_final_t3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trabajo_final_t3.databinding.FragmentTablayoutBuscadorBinding
import com.example.trabajo_final_t3.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutBuscador : Fragment() {

    private lateinit var binding: FragmentTablayoutBuscadorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTablayoutBuscadorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.toolbar.setTitle("Search")

        val viewPagerAdapter = ViewPagerAdapter(requireActivity())
        binding.viewpager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tablayout, binding.viewpager) { tab, position ->
            tab.text = when (position) {
                0 -> "Ingredients"
                1 -> "Nutrients"
                else -> null
            }
        }.attach()
    }

}