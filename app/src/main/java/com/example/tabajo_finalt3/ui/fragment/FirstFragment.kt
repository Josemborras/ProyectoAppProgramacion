package com.example.tabajo_finalt3.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tabajo_finalt3.R
import com.example.tabajo_finalt3.data.models.Recipe
import com.example.tabajo_finalt3.data.models.RecipesRandom
import com.example.tabajo_finalt3.databinding.FragmentListadoBinding
import com.example.tabajo_finalt3.ui.adapter.Adaptadorlistado

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentListadoBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<com.example.tabajo_finalt3.viewmodel.ViewModel>()
    private lateinit var adaptador: Adaptadorlistado

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListadoBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //se le pasa recipes(RecipesRandom) al configrecicler(configuracion del adaptador)
        //la funcion que viene del viewModel es para pasarle el listado que devuelve al config recicle
        viewModel.recipesRandomAddVw(50).observe(viewLifecycleOwner){
            if (it != null) {
                configRecicler(it.recipes)
            }
        }

    }

    //el onclick es para cuando pinches en la receta te lleve a la otra pantalla donde salgan los datos de la receta
    //se mete la lista dentro para pasarselo al adaptador
    fun configRecicler(lista : ArrayList<Recipe>){

        val recyclerView = binding.recyclerView
        //aqui se le esta pasando la lista
        adaptador = Adaptadorlistado(lista, object : Adaptadorlistado.MyClick{
            override fun onClick(receta: Recipe) {

            }

        })
        //disenio de como se vera el recilcer view cuando los ejecutemos
        val disenio = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = disenio
        recyclerView.adapter = adaptador

    }
}