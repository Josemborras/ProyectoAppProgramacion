package com.example.trabajo_final_t3.ui

import com.example.trabajo_final_t3.viewModel.ViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel by activityViewModels<ViewModel>()


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

        viewModel.triviaRandomAddVw().observe(viewLifecycleOwner){
            if (it != null) {
                binding.txTrivia.text = """
                    " ${it.text}"
                """.trimIndent()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}