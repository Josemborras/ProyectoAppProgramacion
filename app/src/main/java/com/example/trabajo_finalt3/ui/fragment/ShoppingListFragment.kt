package com.example.trabajo_finalt3.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo_finalt3.R
import com.example.trabajo_finalt3.data.models.ResponseGetShoppingList
import com.example.trabajo_finalt3.databinding.DialogBinding
import com.example.trabajo_finalt3.databinding.FragmentShoppingListBinding
import com.example.trabajo_finalt3.ui.MainActivity
import com.example.trabajo_finalt3.ui.adapter.AdapterAisles
import com.example.trabajo_finalt3.viewmodel.ViewModel

/**
 * @author Sandra Martinez
 *  * [Fragment] ShoppingListFragment
 *  En este fragment se van a mostrar los productos guardados en la lista de la compra.Tambien se podran borrar, marcar como ya comprados y agregar otros nuevos a la lista
 */
class ShoppingListFragment : Fragment() {
    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterAisles: AdapterAisles
    private val viewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).changeToolbar(binding.toolbarShoppingList, false, findNavController())
        (requireActivity() as MainActivity).changeToolbarTitle("Shopping List")
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeShoppingList.setColorSchemeResources(R.color.green)

        configRecycler()

        binding.swipeShoppingList.isRefreshing = true
        viewModel.getShoppingList().observe(viewLifecycleOwner, observer)

        binding.swipeShoppingList.setOnRefreshListener {
            binding.swipeShoppingList.isRefreshing = true
            viewModel.getShoppingList().observe(viewLifecycleOwner, observer)
        }

        binding.buttonForm.setOnClickListener {
            BottomSheet().show((activity as MainActivity).supportFragmentManager, BottomSheet.TAG)
        }
    }

    private val observer = Observer<ResponseGetShoppingList>{
        it.aisles?.let {list ->
            adapterAisles.newList(list)
        }

        if(adapterAisles.itemCount > 0){
            binding.constraintNoList.visibility = View.GONE
            binding.constraintList.visibility = View.VISIBLE
        } else {
            binding.constraintNoList.visibility = View.VISIBLE
            binding.constraintList.visibility = View.GONE
        }

        binding.swipeShoppingList.isRefreshing = false
    }

    private fun configRecycler(){
        adapterAisles = AdapterAisles(object: AdapterAisles.Listener{
            override fun onClickListener(itemId: Int) {
                createDialogDelete(itemId)
            }
        })

        binding.recyclerAisles.adapter = adapterAisles
        binding.recyclerAisles.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun createDialogDelete(itemId: Int){
        val builder = AlertDialog.Builder(requireContext())
        val dialogBinding = DialogBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)

        val dialog = builder.create()

        dialogBinding.buttonDelete.setOnClickListener {
            viewModel.deleteItemShoppingList(itemId)
            dialog.dismiss()
            viewModel.getShoppingList().observe(viewLifecycleOwner, observer)
        }
        dialogBinding.buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}