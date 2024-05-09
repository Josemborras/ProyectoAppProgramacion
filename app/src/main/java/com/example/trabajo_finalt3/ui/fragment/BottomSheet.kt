package com.example.trabajo_finalt3.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.trabajo_finalt3.databinding.MockBottomSheetLayoutBinding
import com.example.trabajo_finalt3.viewmodel.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author Sandra Martinez
 * [BottomSheetDialogFragment]
 * ModalBottomSheet que contiene los TextInputLayout necesarios para añadir un elemento nuevo a la lista de la compra
 */
class BottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: MockBottomSheetLayoutBinding
    private val viewModel by activityViewModels<ViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MockBottomSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireDialog() as BottomSheetDialog).dismissWithAnimation = true
    }

    companion object {
        const val TAG = "ModalBottomSheet list item form"
    }
}