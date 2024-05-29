package com.example.trabajo_final_t3.ui.fragments.navigationMenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.trabajo_final_t3.databinding.ModalBottomSheetBinding
import com.example.trabajo_final_t3.viewModel.MyViewModel
import com.example.trabajo_final_t3.data.models.shoppingList.PostItem
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Timer
import kotlin.concurrent.schedule

/**
 * @author Sandra Martinez
 * [BottomSheetDialogFragment]
 * ModalBottomSheet que contiene los TextInputLayout necesarios para añadir un elemento nuevo a la lista de la compra
 */
class BottomSheet(listener: BottomSheetListener) : BottomSheetDialogFragment() {
    private lateinit var binding: ModalBottomSheetBinding
    private val viewModel by activityViewModels<MyViewModel>()

    //como BottomSheetDialogFragment es un elemento nuevo, busque como implementar su listener
    //esta forma de iniciar el listener es la misma que utilizaron en la documentacion que encontre
    private var bottomSheetListener: BottomSheetListener?=null
    init{
        this.bottomSheetListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ModalBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireDialog() as BottomSheetDialog).dismissWithAnimation = true //muestra una animacion al cerrar el bottomSheet

        //al pulsar el boton se recoge la informacion del formulario y se crea un elemento nuevo
        binding.buttonAddItem.setOnClickListener {
            var item = binding.inputItem.text.toString()
            val quantity = binding.inputQuantity.text.toString()
            val list = binding.inputList.text.toString()

            if(!quantity.isNullOrEmpty()){
                item = "$quantity $item"
            }

            if(!item.isNullOrEmpty()){
                val postItem = if(!list.isNullOrEmpty()){
                    PostItem(list, item, true) //si la variable de la lista no esta vacia
                } else {
                    PostItem(null, item, true) //si la variable de la lista esta vacia
                }

                //se llama al metodo del viewmodel para guardar un elemento nuevo
                // Cuando se termine de ejecutar, se ejecutara el resto del codigo
                viewModel.addItemShoppingList(postItem).observe(viewLifecycleOwner){
                    //este listener ejecuta el codigo que se le indica en ShoppingListFragment
                    //(refresca la lista de la compra para mostrar el producto nuevo)
                    bottomSheetListener?.reloadViewmodel()

                    //para hacer un delay en el dismiss y dar tiempo de que se ejecute el listener correctamente
                    Timer().schedule(400){
                        dismiss()
                    }
                }
            } else {
                Toast.makeText(requireContext(), "No food item introduced", Toast.LENGTH_LONG).show()
            }
        }
    }

    //tag del dialog
    companion object {
        const val TAG = "ModalBottomSheet list item form"
    }

    interface BottomSheetListener {
        fun reloadViewmodel()
    }
}