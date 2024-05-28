package com.example.trabajo_final_t3.ui.fragments

import com.example.trabajo_final_t3.viewModel.MyViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val myViewModel by activityViewModels<MyViewModel>()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonRandom.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_firstFragment)
        }

        myViewModel.triviaRandomAddVw().observe(viewLifecycleOwner){
            if (it != null) {
                binding.txTrivia.text = """
                    " ${it.text}"
                """.trimIndent()
            }
        }

        binding.buttonSearch.setOnClickListener{
            findNavController().navigate(R.id.action_HomeFragment_to_tabLayoutBuscador)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}