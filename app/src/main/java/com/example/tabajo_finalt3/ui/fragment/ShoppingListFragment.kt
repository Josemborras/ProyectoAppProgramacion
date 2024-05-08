package com.example.tabajo_finalt3.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.tabajo_finalt3.data.models.Item
import com.example.tabajo_finalt3.databinding.FragmentShoppingListBinding
import com.example.tabajo_finalt3.ui.MainActivity
import com.example.tabajo_finalt3.ui.adapter.AdapterShoppingList
import com.example.tabajo_finalt3.viewmodel.ViewModel

/**
 * @author Sandra Martinez
 *  * [Fragment] ShoppingListFragment
 *  En este fragment se van a mostrar los productos guardados en la lista de la compra.Tambien se podran borrar, marcar como ya comprados y agregar otros nuevos a la lista
 */
class ShoppingListFragment : Fragment() {
    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterToBuy: AdapterShoppingList
    private lateinit var adapterBought: AdapterShoppingList
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

        configRecycler()

        viewModel.getShoppingList().observe(viewLifecycleOwner){
            val aisleList = it.aisles

            if (aisleList != null) {
                for(i in 0 until aisleList.size){
                    if(i == 0){
                        aisleList[i].items?.let { itemList -> adapterToBuy.newList(itemList) }
                    } else {
                        aisleList[i].items?.let { itemList -> adapterToBuy.addList(itemList) }
                    }
                }
            }
        }

        if(adapterToBuy.itemCount > 0 && adapterBought.itemCount > 0){
            binding.constraintLayout.visibility = View.GONE
            binding.scrollView.visibility = View.VISIBLE
            binding.cardToBuy.visibility = View.VISIBLE
            binding.cardToBuy.visibility = View.VISIBLE
        } else if(adapterToBuy.itemCount > 0){
            binding.constraintLayout.visibility = View.GONE
            binding.scrollView.visibility = View.VISIBLE
            binding.cardToBuy.visibility = View.VISIBLE
            binding.cardToBuy.visibility = View.GONE
        }else{
            binding.constraintLayout.visibility = View.VISIBLE
            binding.scrollView.visibility = View.GONE
            binding.cardToBuy.visibility = View.GONE
            binding.cardToBuy.visibility = View.GONE
        }
    }
    private fun configRecycler(){
        adapterToBuy = AdapterShoppingList(object: AdapterShoppingList.Listener{
            override fun onCheckListener(isChecked: Boolean, position: Int, item: Item) {
                if(!isChecked){
                    adapterToBuy.removeItem(position)
                    adapterBought.addItem(item)
                }
            }
        })

        binding.recyclerToBuy.adapter = adapterToBuy
        binding.recyclerToBuy.layoutManager = LinearLayoutManager(requireContext())

        adapterBought = AdapterShoppingList(object : AdapterShoppingList.Listener{
            override fun onCheckListener(isChecked: Boolean, position: Int, item: Item) {
                if(isChecked){
                    adapterBought.removeItem(position)
                    adapterToBuy.addItem(item)
                }
            }
        })

        binding.recyclerBought.adapter = adapterBought
        binding.recyclerBought.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}