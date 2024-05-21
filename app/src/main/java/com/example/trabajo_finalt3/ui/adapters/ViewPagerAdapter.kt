package com.example.trabajo_finalt3.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.trabajo_finalt3.ui.fragments.detail.CardFragment
import com.example.trabajo_finalt3.ui.fragments.detail.IngredientsFragment
import com.example.trabajo_finalt3.ui.fragments.detail.StepsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> IngredientsFragment()
            1 -> StepsFragment()
            2 -> CardFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }
}