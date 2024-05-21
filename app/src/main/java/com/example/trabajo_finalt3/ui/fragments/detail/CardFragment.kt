package com.example.trabajo_finalt3.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.trabajo_finalT3.databinding.FragmentCardBinding
import com.example.trabajo_finalt3.MyViewModel

class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private val viewModel by activityViewModels<MyViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRecipe().observe(viewLifecycleOwner){recipeItem ->
            viewModel.getCardImage(recipeItem.id).observe(viewLifecycleOwner){cardImage ->
                Glide.with(this).load(cardImage.url).into(binding.imageView)
            }
        }

    }
}