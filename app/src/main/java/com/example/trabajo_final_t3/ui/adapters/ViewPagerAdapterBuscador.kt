package com.example.trabajo_final_t3.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.trabajo_final_t3.ui.fragments.BuscadorIngredientes
import com.example.trabajo_final_t3.ui.fragments.BuscadorNutrientes

class ViewPagerAdapterBuscador(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BuscadorIngredientes()
            1 -> BuscadorNutrientes()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}