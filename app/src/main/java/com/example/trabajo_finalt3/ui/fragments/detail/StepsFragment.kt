package com.example.trabajo_finalt3.ui.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo_finalT3.databinding.FragmentStepsBinding
import com.example.trabajo_finalt3.MyViewModel
import com.example.trabajo_finalt3.model.data.Steps.Step
import com.example.trabajo_finalt3.model.data.Steps.StepsResponseItem
import com.example.trabajo_finalt3.ui.adapters.ElabAdapter
import com.example.trabajo_finalt3.ui.adapters.StepAdapter

class StepsFragment : Fragment() {

    private lateinit var binding: FragmentStepsBinding
    private val viewModel by activityViewModels<MyViewModel>()
    private lateinit var adapterElab: ElabAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStepsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = 324694
        viewModel.getInstructions(id).observe(viewLifecycleOwner) {
            configRecyclerElabs(it)
        }
    }

    private fun configRecyclerElabs(list: List<StepsResponseItem>) {
        adapterElab = ElabAdapter()
        adapterElab.setElabs(list)

        binding.rvElab.layoutManager = LinearLayoutManager(requireContext())
        binding.rvElab.adapter = adapterElab
    }


}


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val id = 632583
//        viewModel.getInstructions(id).observe(viewLifecycleOwner) { response ->
//            configRecyclerElabs(response)
//        }
//    }
//
//    private fun configRecyclerElabs(list: List<StepsResponseItem>) {
//        adapter = ElabAdapter()
//        adapter.setElabs(list)
//
//        binding.rvSteps.layoutManager = LinearLayoutManager(requireContext())
//        binding.rvSteps.adapter = adapter
//    }
//}

//package com.example.trabajo_finalt3.ui.fragments.detail
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.activityViewModels
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.trabajo_finalT3.R
//import com.example.trabajo_finalT3.databinding.FragmentStepsBinding
//import com.example.trabajo_finalt3.MyViewModel
//import com.example.trabajo_finalt3.model.data.Steps.Step
//import com.example.trabajo_finalt3.model.data.Steps.StepsResponseItem
//import com.example.trabajo_finalt3.ui.adapters.StepAdapter
//
//class StepsFragment : Fragment() {
//
//    private lateinit var binding: FragmentStepsBinding
//    private val viewModel by activityViewModels<MyViewModel>()
//    private lateinit var adapter: StepAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = FragmentStepsBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val id = 324694
//        viewModel.getInstructions(id).observe(viewLifecycleOwner) {
//            configRecyclerSteps(it.first().steps)  // Asuming steps are in the first item of the list
//        }
//    }
//
//    private fun configRecyclerSteps(list: List<Step>) {
//        adapter = StepAdapter()
//        adapter.setSteps(list)
//
//        binding.rvSteps.layoutManager = LinearLayoutManager(requireContext())
//        binding.rvSteps.adapter = adapter
//    }
//}



