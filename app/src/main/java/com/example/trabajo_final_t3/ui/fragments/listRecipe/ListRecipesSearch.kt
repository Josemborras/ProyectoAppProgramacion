package com.example.trabajo_final_t3.ui.fragments.listRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.ListRecipeResponse
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.RecipesResponseItem
import com.example.trabajo_final_t3.databinding.FragmentListSearchBinding
import com.example.trabajo_final_t3.ui.MainActivity
import com.example.trabajo_final_t3.ui.adapters.SearchListAdapter

class ListRecipesSearch : Fragment()  {
    private var _binding: FragmentListSearchBinding? = null
    private val binding get() = _binding!!

    private val myViewModel by activityViewModels<com.example.trabajo_final_t3.viewModel.MyViewModel>()
    private lateinit var adaptador2: SearchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListSearchBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeToolbar(binding.toolbarSearch, true, findNavController())
            (requireActivity() as MainActivity).changeToolbarTitle("Search Results")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //esta funcion la investige por google para que cuando bajes y te de pereza tener que subir que te suba automaticamente
        binding.flSubirSearch.setOnClickListener {
            binding.recyclerViewSearch.smoothScrollToPosition(0)
        }

        myViewModel.getRecipeIngredientResponse().observe(viewLifecycleOwner){
            configRecicler2(it) // juntar las data class
        }
    }

    //el onclick es para cuando pinches en la receta te lleve a la otra pantalla donde salgan los datos de la receta
    //se mete la lista dentro para pasarselo al adaptador
    private fun configRecicler2(lista : ListRecipeResponse){

        val recyclerView = binding.recyclerViewSearch
        //aqui se le esta pasando la lista
        adaptador2 = SearchListAdapter(lista, object : SearchListAdapter.MyClick{
            override fun onClick(receta: RecipesResponseItem) {
                myViewModel.setBoolean(true)
                myViewModel.setRecipeSearch(receta)
                findNavController().navigate(R.id.action_listRecipesSearch_to_detailRecipeFragment)
            }

        })
        //disenio de como se vera el recilcer view cuando los ejecutemos
        val disenio = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerViewSearch.layoutManager = disenio
        recyclerView.adapter = adaptador2

    }
}