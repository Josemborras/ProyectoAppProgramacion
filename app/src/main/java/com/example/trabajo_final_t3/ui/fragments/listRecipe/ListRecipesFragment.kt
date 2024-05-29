package com.example.trabajo_final_t3.ui.fragments.listRecipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.data.models.AllRecipeInfo.Recipe
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.ListRecipeResponse
import com.example.trabajo_final_t3.data.models.SearchRecipesByIngredients.RecipesResponseItem
import com.example.trabajo_final_t3.databinding.FragmentListadoBinding
import com.example.trabajo_final_t3.ui.adapters.ListRecipesAdapter
import com.example.trabajo_final_t3.ui.adapters.SearchListAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListRecipesFragment : Fragment() {

    private var _binding: FragmentListadoBinding? = null
    private val binding get() = _binding!!

    private val myViewModel by activityViewModels<com.example.trabajo_final_t3.viewModel.MyViewModel>()
    private lateinit var adaptador: ListRecipesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListadoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //aqui se pone para que si tarda mucho en cargar el viewmodel se muestre y el usuario sepa que se esta cargando la pagina
        binding.swipe.isRefreshing = true
        //cambiar el color del swipe
        binding.swipe.setColorSchemeResources(R.color.green)

        //esta funcion la investigue por google para que cuando bajes y te de pereza tener que subir que te suba automaticamente
        binding.flSubir.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }

        //este es el listener para que cuando tires hacia arriba te recargue el viewmodel
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            myViewModel.recipesRandomAddVw(50).observe(viewLifecycleOwner){
                if (it != null) {
                    configRecicler(it.recipes)
                }

                binding.swipe.isRefreshing = false
            }
        }

        //se le pasa recipes(RecipesRandom) al configrecicler(configuracion del adaptador)
        //la funcion que viene del viewModel es para pasarle el listado que devuelve al config recicler
        myViewModel.recipesRandomAddVw(50).observe(viewLifecycleOwner){
            if (it != null) {
                configRecicler(it.recipes)
            }
            binding.swipe.isRefreshing = false
        }
    }

    //el onclick es para cuando pinches en la receta te lleve a la otra pantalla donde salgan los datos de la receta
    //se mete la lista dentro para pasarselo al adaptador
    fun configRecicler(lista : ArrayList<Recipe>){

        val recyclerView = binding.recyclerView
        //aqui se le esta pasando la lista
        adaptador = ListRecipesAdapter(lista, object : ListRecipesAdapter.MyClick{
            override fun onClick(receta: Recipe) {

            }

        })
        //disenio de como se vera el recilcer view cuando los ejecutemos
        val disenio = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = disenio
        recyclerView.adapter = adaptador

    }
}