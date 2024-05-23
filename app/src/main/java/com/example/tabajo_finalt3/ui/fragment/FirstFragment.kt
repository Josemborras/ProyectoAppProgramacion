package com.example.tabajo_finalt3.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.currentComposer
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
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

        //aqui se pone para que si tarda mucho en cargar el viewmodel se muestre y el usuario sepa que se esta cargando la pagina
        binding.swipe.isRefreshing = true
        //para cambiar el color del circulo
//        binding.swipe

        //esta funcion la investige por google para que cuando bajes y te de pereza tener que subir que te suba automaticamente
        binding.flSubir.setOnClickListener {
            binding.recyclerView.smoothScrollToPosition(0)
        }

        //este es el listener para que cuando tires hacia arriba te recarge el viewmodel
        binding.swipe.setOnRefreshListener {
            binding.swipe.isRefreshing = true
            viewModel.recipesRandomAddVw(50).observe(viewLifecycleOwner){
                if (it != null) {
                    configRecicler(it.recipes)
                }

                binding.swipe.isRefreshing = false
            }
        }


        //esto lo hemos hecho aqui para comprobar que la llamada de la peticion estuviese bien, la peticion viene de otra clase, y si lo
        //haciamos desde aqui directamente es mejor, porque asi no nos aria falta llamar al viewmodel desde el adaptador


//        viewModel.recipeCardAddvw(4632).observe(viewLifecycleOwner){
//            var cardimage = it?.url
//            Glide.with(this).load(cardimage).into(binding.ivPrueba)
//        }

        //se le pasa recipes(RecipesRandom) al configrecicler(configuracion del adaptador)
        //la funcion que viene del viewModel es para pasarle el listado que devuelve al config recicle
        viewModel.recipesRandomAddVw(50).observe(viewLifecycleOwner){
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
        adaptador = Adaptadorlistado(lista, object : Adaptadorlistado.MyClick{
            override fun onClick(receta: Recipe) {

            }

        })
        //disenio de como se vera el recilcer view cuando los ejecutemos
        val disenio = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = disenio
        recyclerView.adapter = adaptador

    }

//    val observer = Observer<CharacterResponse?> {
//        binding.swipe.isRefreshing = false
//        val info = it?.info
//        info?.pages?.let { totalPage = it }
//
//
//        val personajes = it?.results
//        adaptador.refrescarListado(personajes)
//
//    }
}