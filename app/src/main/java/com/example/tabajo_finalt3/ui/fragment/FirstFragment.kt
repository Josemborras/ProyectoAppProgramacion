package com.example.tabajo_finalt3.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tabajo_finalt3.databinding.FragmentListadoBinding
import com.example.tabajo_finalt3.ui.MyModel
import com.example.tabajo_finalt3.ui.adapter.Adaptadorlistado

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentListadoBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<MyModel>()
    private var currentPage = 1
    private  var totalPage = 0
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

//        configRecicler()

        //binding.swipe.setColorSchemeColors(R.color.green, R.color.error_color, R.color.white)
//        binding.swipe.setProgressBackgroundColorSchemeResource(R.color.black)
//
//
//        binding.swipe.setOnRefreshListener {
//            binding.swipe.isRefreshing = true
//            currentPage = 1
//            viewModel.getCharacterByPage(currentPage).observe(viewLifecycleOwner, observer)
//        }
//
//        viewModel.getCharacterByPage(currentPage).observe(viewLifecycleOwner, observer)
//
//        binding.arrowLeft.setOnClickListener {
//            currentPage--
//            viewModel.getCharacterByPage(currentPage).observe(viewLifecycleOwner, observer)
//            configRecicler()
//        }
//
//
//        binding.arrowRight.setOnClickListener {
//            currentPage++
//            viewModel.getCharacterByPage(currentPage).observe(viewLifecycleOwner, observer)   //aqui tendrias que ponerle el observer, a cada uno de los setonclik, para no pnerselos uno a uno haces uno para todos
//            configRecicler()
//        }
//
//    }
//
//    fun configRecicler(){
//
//        val recyclerView = binding.recyclerView
//        adaptador = Adaptadorlistado( object : Adaptadorlistado.MyClick{
//            override fun onClick(character: Character) {
//              viewModel.guardarPersonje(character)
//                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//            }
//
//        })
//        val disenio = GridLayoutManager(requireContext(), 2)
//        binding.recyclerView.layoutManager = disenio
//        recyclerView.adapter = adaptador
//
//    }
//
//
//    val observer = Observer<CharacterResponse?> {
//        binding.swipe.isRefreshing = false
//        val info = it?.info
//        info?.pages?.let { totalPage = it }
//
//
//        if(currentPage == 1){
//            binding.arrowLeft.isVisible = false          //binding.arrowLeft.visibility = View.VISIBLE
//        }else if(currentPage == totalPage){
//            binding.arrowRight.isVisible = false
//        }else{
//            binding.arrowLeft.isVisible = true
//            binding.arrowRight.isVisible = true
//
//        }
//
//        val personajes = it?.results
//        adaptador.refrescarListado(personajes)
//
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
    }
}