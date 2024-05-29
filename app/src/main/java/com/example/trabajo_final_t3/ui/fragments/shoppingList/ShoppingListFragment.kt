package com.example.trabajo_final_t3.ui.fragments.shoppingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabajo_final_t3.R
import com.example.trabajo_final_t3.databinding.DialogBinding
import com.example.trabajo_final_t3.databinding.FragmentShoppingListBinding
import com.example.trabajo_final_t3.ui.MainActivity
import com.example.trabajo_final_t3.viewModel.MyViewModel
import com.example.trabajo_final_t3.data.models.shoppingList.ResponseGetShoppingList
import com.example.trabajo_finalt3.ui.adapter.AislesAdapter

/**
 * @author Sandra Martinez
 *  * [Fragment] ShoppingListFragment
 *  En este fragment se van a mostrar los productos guardados en la lista de la compra.
 *  Tambien se podran borrar de la lista
 */
class ShoppingListFragment : Fragment(), BottomSheet.BottomSheetListener {
    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!
    private lateinit var aislesAdapter: AislesAdapter
    private val viewModel by activityViewModels<MyViewModel>()

    //inicializar el bottomSheet y su listener
    private lateinit var bottomSheet: BottomSheet
    private lateinit var listener: BottomSheet.BottomSheetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = this //indicar que el listener funcione en este fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        //usar las funciones creadas en MainActivity para cargar el toolbar y cambiar su titulo
        (requireActivity() as MainActivity).changeToolbar(binding.toolbarShoppingList, true, findNavController())
        (requireActivity() as MainActivity).changeToolbarTitle("Shopping List")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //cambiar el color del swipe
        binding.swipeShoppingList.setColorSchemeResources(R.color.green)

        configRecycler()

        //cargar el listado
        binding.swipeShoppingList.isRefreshing = true
        viewModel.getShoppingList().observe(viewLifecycleOwner, observer)

        //recargar el listado al hacer el gesto ed swipe
        binding.swipeShoppingList.setOnRefreshListener {
            binding.swipeShoppingList.isRefreshing = true
            viewModel.getShoppingList().observe(viewLifecycleOwner, observer)
        }

        //activar el ModalBottomSheet al pulsar el boton
        binding.buttonForm.setOnClickListener {
            bottomSheet = BottomSheet(listener)
            bottomSheet.show((activity as MainActivity).supportFragmentManager, BottomSheet.TAG)
        }
    }

    //observador para cuando se llama a la peticion de mostrar el listado
    private val observer = Observer<ResponseGetShoppingList>{
        it.aisles?.let {list ->
            aislesAdapter.newList(list)
        }

        //si el listado esta vacio, se muestra la imagen y un subtitulo. Si hay listado, desaparecen
        if(aislesAdapter.itemCount > 0){
            binding.IVnoList.visibility = View.GONE
            binding.TVnoList.visibility = View.GONE
        } else {
            binding.IVnoList.visibility = View.VISIBLE
            binding.TVnoList.visibility = View.VISIBLE
        }

        binding.swipeShoppingList.isRefreshing = false
    }

    //configuracion del recyclerview
    private fun configRecycler(){
        aislesAdapter = AislesAdapter(object: AislesAdapter.Listener{
            override fun onClickListener(itemId: Int) {
                //al pulsar sobre la celda del ingrediente se activa un AlertDialog para confirmar si se quiere borrar
                createDialogDelete(itemId)
            }
        })

        binding.recyclerAisles.adapter = aislesAdapter
        binding.recyclerAisles.layoutManager = LinearLayoutManager(requireContext())
    }

    //crear la alerta
    private fun createDialogDelete(itemId: Int){
        val builder = AlertDialog.Builder(requireContext())
        val dialogBinding = DialogBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)

        val dialog = builder.create()

        dialogBinding.buttonDelete.setOnClickListener {
            //cuando se pulsa el boton eliminar, se llama al metodo del viewmodel que borra un elemento de la lista
            //una vez se ha completado esa peticion, se refesca el listado y se cierra el AlertDialog
            viewModel.deleteItemShoppingList(itemId).observe(viewLifecycleOwner){
                viewModel.getShoppingList().observe(viewLifecycleOwner, observer)
                dialog.dismiss()
            }
        }
        dialogBinding.buttonCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    //override de la funcion del listener del ModalBottomSheet
    //Se le indica que recargue el listado tras haber creado un elemento nuevo desde el bottomSheet
    override fun reloadViewmodel() {
        binding.swipeShoppingList.isRefreshing = true
        viewModel.getShoppingList().observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}