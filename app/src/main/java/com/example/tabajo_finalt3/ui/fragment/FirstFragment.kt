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
import com.example.tabajo_finalt3.R
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

        configRecicler()

        viewModel.recipesRandomAddVw(10).observe(viewLifecycleOwner){



        }

    }

    fun configRecicler(){

        val recyclerView = binding.recyclerView
        adaptador = Adaptadorlistado( object : Adaptadorlistado.MyClick{
            override fun onClick(receta: RecipesRandom) {
               viewModel.recipesRandomAddVw(10)
//                findNavController().navigate(R.id.)
            }

        })
        val disenio = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = disenio
        recyclerView.adapter = adaptador

    }
}