package com.example.trabajo_finalt3.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.trabajo_finalt3.data.models.PostItem
import com.example.trabajo_finalt3.databinding.ModalBottomSheetBinding
import com.example.trabajo_finalt3.viewmodel.MyViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author Sandra Martinez
 * [BottomSheetDialogFragment]
 * ModalBottomSheet que contiene los TextInputLayout necesarios para añadir un elemento nuevo a la lista de la compra
 */
class BottomSheet(listener: BottomSheetListener) : BottomSheetDialogFragment() {
    private lateinit var binding: ModalBottomSheetBinding
    private val viewModel by activityViewModels<MyViewModel>()

    //como BottomSheetDialogFragment es un elemento nuevo, no sabia como implementar su listener, asi que lo busque en internet
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
        (requireDialog() as BottomSheetDialog).dismissWithAnimation = true

        binding.buttonAddItem.setOnClickListener {
            var item = binding.inputItem.text.toString()
            val quantity = binding.inputQuantity.text.toString()
            val list = binding.inputList.text.toString()

            if(!quantity.isNullOrEmpty()){
                item = "$quantity $item"
            }

            if(!item.isNullOrEmpty()){
                val postItem = if(!list.isNullOrEmpty()){
                    PostItem(list, item, true)
                } else {
                    PostItem(null, item, true)
                }

                viewModel.addItemShoppingList(postItem)
                dismiss()
                bottomSheetListener?.reloadViewmodel()
            } else {
                Toast.makeText(requireContext(), "No ingredient introduced", Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet list item form"
    }

    interface BottomSheetListener {
        fun reloadViewmodel()
    }
}