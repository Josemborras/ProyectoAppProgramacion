package com.example.tabajo_finalt3.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tabajo_finalt3.R
import com.example.tabajo_finalt3.databinding.FragmentHomeBinding
import com.example.tabajo_finalt3.ui.MainActivity

/**
 * @author Sandra Martinez
 * [Fragment] HomeFragment
 * Pagina de inicio. Ademas de la navegacion inferior, proporciona tres botones al usuario para navegar por las pantallas
 */
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeToolbar(binding.toolbarHome, false, findNavController())
        (requireActivity() as MainActivity).changeToolbarTitle("HOME")
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSearch.setOnClickListener {
            //findNavController().navigate()
        }
        binding.buttonRandom.setOnClickListener {
            //findNavController().navigate()
        }
        binding.buttonShoppingList.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}