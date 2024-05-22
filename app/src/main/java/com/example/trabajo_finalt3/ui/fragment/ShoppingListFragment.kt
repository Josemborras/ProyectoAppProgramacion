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
import com.example.trabajo_finalt3.databinding.FragmentShoppingListBinding
import com.example.trabajo_finalt3.R
import com.example.trabajo_finalt3.data.models.ResponseGetShoppingList
import com.example.trabajo_finalt3.databinding.DialogBinding
import com.example.trabajo_finalt3.ui.MainActivity
import com.example.trabajo_finalt3.ui.adapter.AdapterAisles
import com.example.trabajo_finalt3.viewmodel.MyViewModel

/**
 * @author Sandra Martinez
 *  * [Fragment] ShoppingListFragment
 *  En este fragment se van a mostrar los productos guardados en la lista de la compra.Tambien se podran borrar, marcar como ya comprados y agregar otros nuevos a la lista
 */
class ShoppingListFragment : Fragment(), BottomSheet.BottomSheetListener {
    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapterAisles: AdapterAisles
    private val viewModel by activityViewModels<MyViewModel>()

    //inicializar el bottomSheet y su listener
    lateinit var bottomSheet: BottomSheet
    lateinit var listener: BottomSheet.BottomSheetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = this //declarar el valor del listener
    }

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
            bottomSheet = BottomSheet(listener)
            bottomSheet.show((activity as MainActivity).supportFragmentManager, BottomSheet.TAG)
        }
    }

    private val observer = Observer<ResponseGetShoppingList>{
        it.aisles?.let {list ->
            adapterAisles.newList(list)
        }

        if(adapterAisles.itemCount > 0){
            binding.IVnoList.visibility = View.GONE
            binding.TVnoList.visibility = View.GONE
        } else {
            binding.IVnoList.visibility = View.VISIBLE
            binding.TVnoList.visibility = View.VISIBLE
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
            viewModel.getShoppingList().observe(viewLifecycleOwner, observer)
            dialog.dismiss()
        }
        dialogBinding.buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    //override de la funcion del listener del bottomsheet
    override fun reloadViewmodel() {
        binding.swipeShoppingList.isRefreshing = true
        viewModel.getShoppingList().observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}